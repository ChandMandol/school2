# Student Management System

A simple Spring Boot web application for managing students with Thymeleaf and PostgreSQL.

## Features

- Admin dashboard for managing students
- Add new students (name, ID, course, grade)
- Edit existing students
- Delete students
- View all students in a table

## Prerequisites

- Java 17 or higher
- Maven
- PostgreSQL database

## Database Configuration

The application expects a PostgreSQL database with the following configuration:
- Database name: `school2`
- Username: `postgres`
- Password: `chand157`

Make sure PostgreSQL is running and the database is created before starting the application.

## How to Run

1. Clone or download the project
2. Make sure PostgreSQL is running and the database `school2` is created
3. Navigate to the project directory
4. Build the project:
   ```
   mvn clean install
   ```
5. Run the application:
   ```
   mvn spring-boot:run
   ```
   Or alternatively:
   ```
   java -jar target/school2-0.0.1-SNAPSHOT.jar
   ```

## Access the Application

Open your browser and go to: http://localhost:8080

## Application Structure

- Home page (`/`) - Shows two sections: Admin and Student
- Students list (`/students`) - View all students and perform CRUD operations
- Add student (`/students/new`) - Form to add a new student
- Edit student (`/students/edit/{id}`) - Form to edit an existing student

## Technologies Used

- Spring Boot 3.1.0
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Java 17