FROM eclipse-temurin:17-jre-alpine
LABEL authors="giuli"
WORKDIR /app-cadastro
COPY target/Cadastro-0.0.1-SNAPSHOT.jar /app-cadastro/cadastro-docker.jar
ENTRYPOINT ["java", "-jar", "cadastro-docker.jar"]