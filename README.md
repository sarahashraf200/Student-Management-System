# Student Management System

A simple Student Management System built with Spring Boot and JPA, allowing CRUD operations for students, courses, and quizzes.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies](#technologies)
- [Getting Started](#getting-started)
- [Testing API with Swagger](#testing-api-with-swagger)
## Introduction

This project serves as a basic Student Management System where you can manage students, courses, and quizzes. It provides RESTful APIs for various CRUD operations.

## Features

- Create, read, update, and delete students
- Create, read, update, and delete courses
- Create, read, update, and delete quizzes
- Assign quizzes to students in a course
- Update quiz scores

## Technologies

- Spring Boot
- Spring Data JPA
- H2 Database (for simplicity, you can replace it with your preferred database)
- Maven

## Getting Started

1. **Clone the repository:**

   ```bash
   git clone https://github.com/sarahashraf200/Student-Management-System.git
   cd Student-Managment-System
## Build the project   
mvn clean install

## Testing API with Swagger
Access the Swagger UI to test the API interactively by navigating to http://localhost:8080/swagger-ui.html.

