# Spring Boot E-Commerce API

This is a Spring Boot application that provides a RESTful API for managing an e-commerce platform. The application integrates with a PostgreSQL database and allows users to perform CRUD operations on products, orders, and order items. The application also uses Docker to containerize the app for easy deployment.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Building the Application](#building-the-application)
- [Running the Application](#running-the-application)
- [Testing the Application](#testing-the-application)
- [API Endpoints](#api-endpoints)
  - [Products](#products)
  - [Orders](#orders)
  - [Order Items](#order-items)
- [Docker Support](#docker-support)
- [Unit Tests](#unit-tests)

## Prerequisites

Before you begin, ensure that you have the following installed on your machine:

- **Java 21** or above (for building the application)
- **Maven** or **Gradle** (for building the project)
- **PostgreSQL** database (or Dockerized PostgreSQL)
- **Docker** (optional, for containerized deployment)
- **Postman** (for testing the API)

### PostgreSQL Configuration

If you are running PostgreSQL locally, ensure that you have a database configured with the following settings:

- **Database name**: `ecommerce_db`
- **Username**: `postgres`
- **Password**: `password`

Update the application properties (`src/main/resources/application.properties`) accordingly if you are using custom settings.

---

## Building the Application

You can build the application using Maven or Gradle.

### With Maven

To build the application using Maven, run the following command in the root directory of the project:

```bash
./mvnw clean install
```

This will compile the code and package the application into a .jar file in the target/ directory.

---

## Running the Application

You can run the application in several ways:

1. Run from IDE:
If you're using an IDE like IntelliJ IDEA or Eclipse, simply run the main method in EcommerceApplication.java.

2. Run via Command Line:
To run the application using the packaged .jar file:

```bash
java -jar target/ecommerce-api-1.0.0.jar
```
3. Run with Docker (recommended for deployment):
Build the Docker Image:
Make sure your Dockerfile is correctly set up.
Run the following command to build the Docker image:

```bash
docker build -t ecommerce-api .
```
Run the Application with Docker:
To run the application inside a Docker container:

```bash
docker run -p 8080:8080 ecommerce-api
```
This will start the application and expose it on port 8080.

---

## Testing the Application
You can test the application using Postman by making HTTP requests to the REST API endpoints.

1. Start the Application:
Ensure the application is running before testing. If running locally, use http://localhost:8080.

2. Test the Endpoints in Postman:
Follow these steps to test your API in Postman:

- GET /api/products - Get a list of all products.
- GET /api/products/{id} - Get a product by ID.
- POST /api/products - Create a new product.
- PUT /api/products/{id} - Update an existing product.
- DELETE /api/products/{id} - Delete a product by ID.
Example POST /api/products Request Body:

```json
{
  "name": "Laptop",
  "price": 999.99
}
```

Refer to the API Endpoints section for full details on all available endpoints.

API Endpoints
Below are the key API endpoints provided by this application.

### Products

- GET /api/products - Get a list of all products.
- GET /api/products/{id} - Get a product by ID.
- POST /api/products - Create a new product.
- PUT /api/products/{id} - Update an existing product.
- DELETE /api/products/{id} - Delete a product by ID.

### Orders
- GET /api/orders - Get a list of all orders.
- GET /api/orders/{id} - Get an order by ID.
- POST /api/orders - Create a new order.
- PUT /api/orders/{id} - Update an existing order.
- DELETE /api/orders/{id} - Delete an order by ID.

### Order Items

- GET /api/order-items - Get a list of all order items.
- GET /api/order-items/{id} - Get an order item by ID.
- POST /api/order-items - Create a new order item.
- PUT /api/order-items/{id} - Update an existing order item.
- DELETE /api/order-items/{id} - Delete an order item by ID.

# Docker Support

The application can be containerized using Docker for easier deployment and scaling.

Docker Setup
Ensure that you have a Dockerfile in the root of your project. It should look something like this:

```Dockerfile

# Use the official Java image from Docker Hub
FROM openjdk:21-jdk

# Copy the built JAR file into the Docker container
COPY target/ecommerce-api-1.0.0.jar demo.jar

# Expose port 8080
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "demo.jar"]
```

You can also use Docker Compose to set up both the application and a PostgreSQL database. An example docker-compose.yml might look like this:

```yaml

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/ecommerce
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
    depends_on:
      - db
  db:
    image: postgres:latest
    environment:
      POSTGRES_DB: ecommerce
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    ports:
      - "5432:5432"
```

To run both services (app and db), execute:

```bash

docker-compose up --build
```

This will build and start both the Spring Boot application and the PostgreSQL database in separate containers.

# Unit Tests
This application is fully tested using JUnit and Mockito. To run the tests, use the following command:

```bash

./mvnw test
```

Alternatively, if you are using an IDE, you can run the tests directly from the IDE's test suite.

---

# Additional Notes
Ensure that your PostgreSQL database is running and accessible. You can use Docker to run PostgreSQL as part of the Docker Compose setup.
The application uses Spring Data JPA for database interactions and OpenAPI for API documentation.
JUnit and Mockito are used for unit testing the application logic.
For more detailed documentation on specific components, please refer to the src/main/resources/application.properties for configuration, or check out the Spring Boot and PostgreSQL official documentation.
