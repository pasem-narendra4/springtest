FROM openjdk:17-alpine
EXPOSE 8080
ADD target/Backend-0.0.1-SNAPSHOT.jar Backend-0.0.1-SNAPSHOT.jar
ENTRYPOINT [ "java","-jar","/Backend-0.0.1-SNAPSHOT.jar" ]