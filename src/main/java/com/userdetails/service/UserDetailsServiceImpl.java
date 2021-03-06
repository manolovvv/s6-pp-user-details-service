package com.userdetails.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.userdetails.dto.UserDTO;
import com.userdetails.model.UserDetails;
import com.userdetails.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDetailsRepository repository;


    private final KafkaTemplate<String, String> template;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Autowired
    JwtUtil jwtUtil;

    public UserDetailsServiceImpl(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @Override
    public UserDetails getUserDetailsById(Long id) {
        return repository.getOne(id);

    }

    private Long getIdFromJwtToken(String token){
        return jwtUtil.getIdFromToken(token);
    }




    private String registerUserInAuthService(UserDTO user) throws JsonProcessingException {
        String url = String.format("http://34.91.3.123:8081/register");
        String response = webClientBuilder
                .build()
                .post()

                .uri(url)
                .body(Mono.just(user), UserDTO.class)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        System.out.println(response);
        return response;

    }

    private String deleteUserInAuthService(Long id){

        try {
            template.send("deleteUser", id.toString());
            return "success";
        }
        catch (Exception ex){
            System.out.println(ex.toString());
        }
        return "error";
    }

    @Override
    public String createNewUser(UserDTO user) throws JsonProcessingException {
        String response = registerUserInAuthService(user);
        try {
                Long id = Long.valueOf(response);

                UserDetails userDetails = new UserDetails();

                userDetails.setId(id);
                userDetails.setFirstName(user.getFirstName());
                userDetails.setFamilyName(user.getFamilyName());
                userDetails.setGender(user.getGender());
                userDetails.setTelephoneNumber(user.getTelephoneNumber());
                repository.save(userDetails);
                return "success";
            }
        catch (Exception ex){
            return response;
        }
    }

    @Override
    public String deleteUserById(Long id, String token) {
        String jwtToken = token.substring(7);
        Long userId = getIdFromJwtToken(jwtToken);
        if(userId.equals(id)) {
            if (deleteUserInAuthService(id).equals("success")) {
                repository.deleteById(id);
                return "success";
            }
            return "No permission";
        }
        return "error";
    }

}
