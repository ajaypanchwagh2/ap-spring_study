# RestAPI-create-without-database-with-SpringBoot

This project demonstrates the implementation of a simple RESTful API using Spring Boot without a database, leveraging Project Lombok for reducing boilerplate code.

# Description
The REST API provides endpoints for creating and managing entities in memory without persisting them to a database. The project uses Spring Boot for handling HTTP requests and responses and integrates Lombok to minimize code verbosity.

# Features
Create Entity: Allows users to create entities via a POST request.
Retrieve Entity: Supports retrieving the created entities through a GET request.
Update Entity: Provides the functionality to update existing entities via PUT requests.
Delete Entity: Enables the deletion of entities using DELETE requests.
Installation
Clone the repository:


# Copy code
git clone https://github.com/ghoshabhijit0612/RestAPI-create-without-database-with-SpringBoot.git
Open the project in your preferred IDE or editor.

# Build the project using Maven or Gradle:


Copy code
mvn clean install
Usage
Run the application:


Copy code
mvn spring-boot:run
# Access the API endpoints using a tool like Postman or cURL:

POST student/create to create a new entity.
GET /student/get?id=id_num to retrieve a specific entity by ID.
UPDATE /student/update?id=id_num to update an existing entity.
DELETE /student/delete?id=id_num to delete an entity by ID.
# Technologies Used
Java
Spring Boot
Project Lombok
![Screenshot (110)](https://github.com/ghoshabhijit0612/RestAPI-create-without-database-with-SpringBoot/assets/81323850/63e6c48f-edb0-4f33-8b41-4269e466b95c)


![Screenshot (109)](https://github.com/ghoshabhijit0612/RestAPI-create-without-database-with-SpringBoot/assets/81323850/861ee9ee-0e63-42dd-b14d-3e0dfb78fbeb)

![Screenshot (108)](https://github.com/ghoshabhijit0612/RestAPI-create-without-database-with-SpringBoot/assets/81323850/69721074-b5da-42f1-87df-caf3423748c0)
![Screenshot (107)](https://github.com/ghoshabhijit0612/RestAPI-create-without-database-with-SpringBoot/assets/81323850/446ed564-64eb-42be-8c8d-06bae98af993)
