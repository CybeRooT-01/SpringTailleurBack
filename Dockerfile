FROM eclipse-temurin:17.0.8.1_1-jdk-focal
WORKDIR /app
COPY target/tailleur-0.0.1-SNAPSHOT.jar tailleur.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "tailleur.jar"]