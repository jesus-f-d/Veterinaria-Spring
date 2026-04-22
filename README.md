# Veterinary Clinic — REST API

A REST API for managing a veterinary clinic, developed with Spring Boot. Allows management of owners, pets, and vaccines, with integration to the RENIEC API for owner data validation.

## 🛠️ Technologies

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat&logo=postgresql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-red?style=flat)
![OpenFeign](https://img.shields.io/badge/OpenFeign-6DB33F?style=flat)

## 📋 Features

- **Owners** — Registration with DNI validation via RENIEC API, search, and listing
- **Pets** — Registration associated with an owner, management by species and breed
- **Vaccines** — Recording of vaccines per pet
- **Error Handling** — Standardized responses with `ApiResponse` and global exception handling

## 🗂️ Project Structure

```
src/main/java/com/clinica/spring/
├── controller/      # REST endpoints
├── service/         # Business logic
├── repository/      # Data access (JPA)
├── entity/          # Database entities
├── dto/             # Request and response DTOs
├── exception/       # Global exception handling
├── client/          # Feign client for RENIEC
└── config/          # Configurations (ModelMapper)
```

## ⚙️ Configuration

1. Clone the repository
2. Create a PostgreSQL database named `clinica_veterinaria`
3. Copy `application.yaml` and replace the values:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/clinica_veterinaria
    username: YOUR_USERNAME
    password: YOUR_PASSWORD
api:
  token: Bearer YOUR_RENIEC_TOKEN
```

4. Run with Maven:

```bash
./mvnw spring-boot:run
```

## 📡 Main Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/owners` | Register owner |
| GET | `/owners` | List owners |
| POST | `/pets` | Register pet |
| GET | `/pets` | List pets |

## 👤 Author

**Jesus Fuster** — [github.com/jesus-f-d](https://github.com/jesus-f-d)
