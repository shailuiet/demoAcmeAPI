# Use an OpenJDK base image
FROM openjdk:17-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/*.jar /app/acme-api.jar

# Expose the port on which the application will run
EXPOSE 8080

# Run the application when the container starts
CMD ["java", "-jar", "/app/acme-api.jar"]
