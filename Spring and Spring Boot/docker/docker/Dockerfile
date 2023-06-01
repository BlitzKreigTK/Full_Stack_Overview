#From the maven image in docker hub AS build
FROM maven:3.8.6-openjdk-18-slim AS build
#In container make /home/app working directory where all operations will be done
WORKDIR /home/app
#Copy local pom.xml and DockerApplication.java to container /home/app location
COPY ./pom.xml /home/app
COPY ./src/main/java/com/learning/docker/DockerApplication.java /home/app/src/main/java/com/learning/docker/DockerApplication.java
#Command to Run maven from pom.xml and download required dependencies
RUN mvn -f /home/app/pom.xml clean package
#Copy dependencies to container in /home/app location
COPY . /home/app
#To a complete build
RUN mvn -f /home/app/pom.xml clean package
#From the jdk image in docker hub
FROM openjdk:18.0-slim
#Copy from build /home/app/target/*.jar jar file as app.jar
COPY --from=build /home/app/target/*.jar app.jar
#Expose port 5000
EXPOSE 5000
#Entrypoint for app.jar (The sh -c command is used to run a shell command.)
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]