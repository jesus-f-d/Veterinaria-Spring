# 🐾 Clínica Veterinaria — API REST

API REST para la gestión de una clínica veterinaria, desarrollada con Spring Boot. Permite administrar propietarios, mascotas y vacunas, con integración a la API de RENIEC para validación de datos del propietario.

## 🛠️ Tecnologías

![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=springboot&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=flat&logo=postgresql&logoColor=white)
![Lombok](https://img.shields.io/badge/Lombok-red?style=flat)
![OpenFeign](https://img.shields.io/badge/OpenFeign-6DB33F?style=flat)

## 📋 Funcionalidades

- **Propietarios** — registro con validación de DNI vía API RENIEC, búsqueda, listado
- **Mascotas** — registro asociado a un propietario, gestión por especie y raza
- **Vacunas** — registro de vacunas por mascota
- **Manejo de errores** — respuestas estandarizadas con `ApiResponse` y excepciones globales

## 🗂️ Estructura del proyecto

```
src/main/java/com/clinica/spring/
├── controller/      # Endpoints REST
├── service/         # Lógica de negocio
├── repository/      # Acceso a datos (JPA)
├── entity/          # Entidades de BD
├── dto/             # DTOs de request y response
├── exception/       # Manejo global de excepciones
├── client/          # Feign client para RENIEC
└── config/          # Configuraciones (ModelMapper)
```

## ⚙️ Configuración

1. Clona el repositorio
2. Crea una base de datos PostgreSQL llamada `clinica_veterinaria`
3. Copia `application.yaml` y reemplaza los valores:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/clinica_veterinaria
    username: TU_USUARIO
    password: TU_PASSWORD

api:
  token: Bearer TU_TOKEN_RENIEC
```

4. Ejecuta con Maven:

```bash
./mvnw spring-boot:run
```

## 📡 Endpoints principales

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | `/propietarios` | Registrar propietario |
| GET | `/propietarios` | Listar propietarios |
| POST | `/mascotas` | Registrar mascota |
| GET | `/mascotas` | Listar mascotas |

## 👤 Autor

**Jesus F.** — [github.com/jesus-f-d](https://github.com/jesus-f-d)
