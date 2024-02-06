FROM  maven:3.9.6-eclipse-temurin-17-alpine AS build
WORKDIR /app
COPY ./pom.xml ./pom.xml
COPY ./src ./src
RUN mvn clean package  -DskipTests
FROM openjdk:17-jdk-slim
# Install netcat (nc)
RUN apt-get update && apt-get install -y netcat && rm -rf /var/lib/apt/lists/*
# Add a startup script
COPY ./startup.sh /app/startup.sh
RUN chmod +x /app/startup.sh

WORKDIR /app
COPY --from=build /app/target/*.jar /app/app.jar
EXPOSE 8080
#CMD ["java", "-jar", "app.jar"]
# Use the startup script to launch the application
CMD ["/app/startup.sh"]



## Stage 1: Build the application
## Start with a base image containing Java runtime (JDK 17)
#FROM openjdk:17-slim as build
#
## Set the Maven version
#ARG MAVEN_VERSION=3.9.6
#
## Install Maven
#RUN apt-get update && \
#    apt-get install -y curl && \
#    mkdir -p /usr/share/maven /usr/share/maven/ref && \
#    curl -fsSL https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
#    | tar -xzC /usr/share/maven --strip-components=1 && \
#    ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
#
## Creates a directory named 'workspace' in the container's filesystem.
#RUN mkdir -p /workspace
#
## Sets the 'workspace' directory as the working directory for subsequent Dockerfile instructions.
#WORKDIR /workspace
#
## Copies the 'pom.xml' file from your host machine into the 'workspace' directory inside the container.
#COPY pom.xml /workspace
#
## Copies the 'src' directory (which contains your application's source code) from your host machine into the 'workspace/src' directory inside the container.
#COPY src /workspace/src
#
## Compile and package the application, skipping tests to speed up the build process.
#RUN mvn clean package -DskipTests
#
## Stage 2: Create the image to run the application
## Use JDK 17 slim image for a smaller final image
#FROM openjdk:17-slim
#
## Set the working directory
#WORKDIR /app
#
## Copy the built jar file from the build stage
#COPY --from=build /workspace/target/*.jar /app/app.jar
#
## Expose the port the application runs on
#EXPOSE 8081
#
## Command to run the application
#CMD ["java", "-jar", "/app/app.jar"]
