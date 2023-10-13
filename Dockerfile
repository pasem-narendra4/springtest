FROM openjdk:17-alpine
EXPOSE 8080
ADD target/spring-boot-docker.jar app.jar
CMD [ "java","-jar","app.jar" ]