FROM openjdk:23-jdk
WORKDIR /app
COPY target/restful-0.0.1-SNAPSHOT.jar restful-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java","-jar","restful-0.0.1-SNAPSHOT.jar"]