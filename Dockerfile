FROM openjdk:8-jdk-slim
COPY "./build/libs/goty-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8083
ENTRYPOINT ["java", "-jar", "app.jar"]