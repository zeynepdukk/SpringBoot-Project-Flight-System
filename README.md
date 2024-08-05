# Flight Management System : Springboot Application 
## Overview
FlightSpringBoot is a Spring Boot application designed to manage flight information. The application provides a RESTful API to create, update, retrieve, and delete flight records. It also supports pagination, sorting, and searching functionalities. 
You can find my detailed internship book in files containing restful API's, AWS and other technologies.
## Features
Create, update, and delete flight records
Retrieve all flight records or a specific flight by ID
Sort flight records by creation time
Search flights by current city
Pagination for flight records
## Technologies Used
#### Spring Boot
#### Spring Data JPA
#### H2 Database (in-memory for development)
#### Maven
#### AWS for deployment
## API Endpoints
GET /flights: Retrieve all flights
GET /flights/sortByCreatedTime: Retrieve all flights sorted by creation time
GET /flights/{id}: Retrieve a flight by ID
POST /flights: Create a new flight
PUT /flights/{id}: Update an existing flight
DELETE /flights/{id}: Delete a flight
GET /page/{pageNum}: View flights by page
GET /search: Search flights by current city
![image](https://github.com/user-attachments/assets/3d643492-6128-425c-9041-d875639e51a7)
## Usage 
### Create Flight
curl -X POST http://localhost:8080/flights \
-H "Content-Type: application/json" \
-d '{
  "current_city": "New York",
  "destination_city": "London",
  "capacity": 200
}'
### Get All Flights
curl -X GET http://localhost:8080/flights
### Get a Flight by ID
curl -X GET http://localhost:8080/flights/{id}
### Update a Flight
curl -X PUT http://localhost:8080/flights/{id} \
-H "Content-Type: application/json" \
-d '{
  "current_city": "Los Angeles",
  "destination_city": "Tokyo",
  "capacity": 300
}'
### Delete a Flight
curl -X DELETE http://localhost:8080/flights/{id}
### Search Flights by Current City
curl -X GET http://localhost:8080/search?query=New York
