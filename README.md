# Driving School Management System

Java client-server application for managing a driving school.

## Overview

This application was developed as a university software engineering project. The system enables driving school instructors to manage students, lesson plans, driving records, and vehicle categories through a desktop client connected to a server application.

## Technologies Used

* Java
* Java Swing
* NetBeans
* MySQL
* JDBC
* Client-Server Architecture

## Project Structure

### AutoSkolaKlijent

Desktop client application used by instructors.

### AutoSkolaServer

Server application responsible for business logic and communication with the database.

### AutoSkolaZajednicki

Shared module containing domain classes, transfer objects, and common functionality used by both client and server.

## Main Features

* User login
* Student management
* Lesson plan management
* Driving record management
* Vehicle category management
* Database persistence using MySQL

## Database

The SQL script required to create and populate the database is included in:

```text
autoskola.sql
```

## Documentation

The repository contains:

* Project documentation
* UML diagrams
* Database schema
* Source code

## Setup Instructions

1. Start MySQL (XAMPP).
2. Import the provided `autoskola.sql` file.
3. Start AutoSkolaServer.
4. Start AutoSkolaKlijent.
5. Login using:

Username: goca
Password: goca

## Author

Gordana Rakic
