# 🧑🏼‍🏫 QS99

<img src="https://github.com/MadLadsTechnology/QS99/blob/d9e478b8a805a6a5919528d3ae20d25480e5f66c/src/frontend/src/assets/logo.png" alt="Logo" width=130/>

Last build: ![example workflow](https://github.com/MadLadsTechnology/QS99/actions/workflows/maven.yml/badge.svg)

Voluntary project in the subject IDATT2105 to improve our grade.

## Content
1. [Introduction](##Introduction)
2. [Functionality](##Functionality)
3. [Introduction](##Introduction)
4. [ER-diagram](##ER-diagram)
5. [REST-API](##REST-API)
6. [Security](##Security)
7. [CI/CD](##CI/CD)
8. [Dependencies](##Dependencies)
9. [Future Work](##Future Work)
10. [Installation Manual](##Installation Manual)
11. [Running Tests](##Running Tests)

## Introduction
### Project description:

Create an alternative to QS, the queue system of subjects at NTNU. It's preferred that the queue is
compatible with a mobile.
## 🛠️ Technologies & Languages
* Vue 3
* Spring Data JPA
* H2 database

## Functionality
### Student
* Log in
* Change Password
* View all (and one) users from a subject
* View all subjects participating in (or by ID)
* Add and delete entry from queue
* View all entries in a queue
* View all exercises in their subjects (and by specific subject)
### Student assistant
* All functionality of a student
* Set a queue active/inactive
* Approve exercise for student
* Set status of an entry
### Professor
* All functionality of students and student assistants
* Register and delete students and professors
* Create and delete subjects
* Add and remove students and professors from a subject
* Add and remove student assistants from a subject
* Create a new queue !!!!Skal denne stå her?!!!!
* Add and remove exercises from a subject
### Admin
* All functionality of students, student assistants and professors
* Register admin users
* View all users and subjects in the database


## ER-diagram
<img src="https://github.com/MadLadsTechnology/QS99/blob/main/src/main/resources/QS99.jpeg"/>

## REST-API
## Security
## CI/CD
## Dependencies
- Maven
  - Build automation tool used for building the Java backend.
- Spring Boot
  - Used to create stand-alone, production grade Spring based applications with Java.
- Spring Security
  - Powerful and highly customizable authentication and access-control framework.
- Spring Boot JPA
  - Makes it easier to implement JPA based repositories. Deals with enhanced support for JPA based data access layers.
- JUnit 
  - Testing framework for Java.
- H2database
  - H2 is a relational database management system written in Java. It can be embedded in Java applications or run in client-server mode.
- JWT
  - 
## Future Work
## Installation Manual
## Running Tests


##


🦕 🦑 Heyo letsgo - //TODO: write this readme
