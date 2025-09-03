# Core Service

Proyecto Spring Boot con H2 en memoria y documentación OpenAPI/Swagger.

---

## 🚀 Requisitos

- Docker ≥ 20.x
- Docker Compose ≥ 1.29.x
- Java 17 (solo si quieres correr local sin Docker)
- Maven (solo si quieres construir el JAR localmente)

---

## 🐳 Docker Compose

1. Construir JAR

```bash
./mvnw clean package
````
2. Levantar contenedor
```bash
docker-compose up --build
```
---

## 🌐 URLs útiles

- **Swagger UI:** [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
##
- **H2 Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
```bash
url: jdbc:h2:mem:core
user: sa
password:
```
---

## 💡 Notas

El proyecto está listo para desarrollo local y entorno Docker.

**Swagger UI:** te permite explorar y probar los endpoints de la API fácilmente.

**H2 Console:** es útil para inspeccionar la base de datos en memoria durante pruebas.