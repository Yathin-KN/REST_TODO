FROM openjdk:11
WORKDIR /app
COPY ./target/todo-app-1.0-SNAPSHOT.jar /app/server.jar
COPY ./config.yml /app/config.yml
EXPOSE 8080
CMD ["java","-jar","server.jar","server","config.yml"]