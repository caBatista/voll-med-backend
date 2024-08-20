
# VollMed - Alura Project

## Overview
This project involves developing a secure and well-structured REST API using Spring Boot, with a focus on applying best practices and ensuring robust security. It features a RESTful design with clear separation of concerns through layered architecture, centralized exception handling, and comprehensive input validation. The API is fortified with JWT-based authentication, role-based authorization, and protections against common vulnerabilities. Additionally, it includes interactive API documentation, thorough testing, and deployment strategies for various environments, exemplifying high standards in API development and security.

## Technologies Used
- **Java**
- **Spring Boot**
- **Kotlin**
- **Gradle**
- **SQL**
- **PostgreSQL**
- **JWT (JSON Web Tokens)**
- **Swagger/OpenAPI**

## Project Structure
- `src/main/java/med/voll/api/config`: Configuration files for security, Swagger, etc.
- `src/main/java/med/voll/api/security`: Security-related classes and filters.
- `src/main/resources`: Application properties and other resources.
- `build.gradle.kts`: Gradle build configuration.

## Setup and Installation
1. **Clone the repository:**
   ```sh
   git clone <repository-url>
   cd <repository-directory>
   ```

2. **Build the project:**
   ```sh
   ./gradlew build
   ```

3. **Run the application:**
   ```sh
   ./gradlew bootRun
   ```

## Database Configuration
Ensure PostgreSQL is installed and running. Update the `application.properties` file with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/your-database
spring.datasource.username=your-username
spring.datasource.password=your-password
```

## API Documentation
Swagger UI is available at:
```
http://localhost:8080/swagger-ui.html
```

## Security
- **Authentication:** JWT-based authentication.
- **Authorization:** Role-based authorization.
- **Password Encoding:** BCryptPasswordEncoder.

## Testing
Run tests using:
```sh
./gradlew test
```

## License
This project is licensed under the MIT License.

## Contact
For any inquiries, please contact [caio.araujo@edu.pucrs.br].
