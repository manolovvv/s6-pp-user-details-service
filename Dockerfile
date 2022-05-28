FROM openjdk:11

EXPOSE 8081

ADD target/user-details-0.0.1-SNAPSHOT.jar user-details-0.0.1-SNAPSHOT.jar

ENTRYPOINT [ "java", "-jar" , "/user-details-0.0.1-SNAPSHOT.jar" ]