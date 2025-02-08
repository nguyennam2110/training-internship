# Student Management System

This is a simple Java-based Student Management System that allows users to create and manage student records. The project uses JDBC for database connectivity and Maven for dependency management.

## Features

- Create new student records
- Store student information including name, gender, and age

## Technologies Used

- Java
- JDBC
- Maven
- Docker (https://docs.docker.com/desktop/setup/install/windows-install/)

## Prerequisites

- Java 8 or higher
- Maven 3.6 or higher
- Docker
- Docker Compose

## Setup

1. **Clone the repository:**
   ```sh
   git clone https://github.com/nguyennam2110/training-intership.git
   cd training-intership
2. **Build the project:**
   ```sh
   mvn clean package
3. **Start the database:**
   ```sh
   mvn spring-boot:run
   