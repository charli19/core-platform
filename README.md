# Core Platform

Spring Boot project with Hexagonal Architecture, in-memory H2 database and OpenAPI/Swagger documentation.

---

## 🛠️ Requirements
#### Recommended

- Docker ≥ 28.3.x
- Docker Compose ≥ 2.39.x

---

## 🐳 Deploy container

```bash
docker-compose up --build
```
---

## ⚙️ API definition

- src/main/resources/api/openapi.yaml

## 🌐 URLs
🚀 **CI/CD:** [https://github.com/charli19/core-platform/actions](https://github.com/charli19/core-platform/actions)
- Set up
- Compile
- Unit test
- E2E test and generate report
- Package and generate artifact
##
📄 **Swagger UI:** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
##
🗄️ **H2 Console:** [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
```bash
url: jdbc:h2:mem:core
user: sa
password:
```