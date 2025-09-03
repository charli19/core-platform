# Imagen base con Java 17
FROM eclipse-temurin:17-jdk-jammy

# Directorio de trabajo
WORKDIR /app

# Copiamos el JAR construido
COPY target/core-platform-0.0.1-SNAPSHOT.jar app.jar

# Puerto de Spring Boot
EXPOSE 8080

# Arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]
