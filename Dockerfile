# Use a slim OpenJDK image for Java 11
FROM openjdk:11

# Set the working directory in the container
WORKDIR /app

# Copy the compiled JAR file from the host to the container
COPY target/todolist2-1.0-SNAPSHOT-shaded.jar app.jar

# Expose the port your application runs on
EXPOSE 7777

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
