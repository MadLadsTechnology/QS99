# 🧑🏼‍🏫 QS99

<img src="https://github.com/MadLadsTechnology/QS99/blob/d9e478b8a805a6a5919528d3ae20d25480e5f66c/src/frontend/src/assets/logo.png" alt="Logo" width=130/>

Last build: ![example workflow](https://github.com/MadLadsTechnology/QS99/actions/workflows/maven.yml/badge.svg)

An instance of this project is running here [qs.eivindharboe.no](http://qs.eivindharboe.no)

Voluntary project in the subject IDATT2105 to improve our grade.

## Content
1. [Introduction](#introduction)
2. [Technology](#%EF%B8%8F-technologies--languages)
3. [Functionality](#functionality)
5. [ER-diagram](#er-diagram)
6. [REST-API](#rest-api)
7. [Security](#security)
8. [CI/CD](#cicd)
9. [Dependencies](#dependencies)
10. [Future Work](#future-work)
11. [Installation Manual](#installation-manual)
12. [Running Tests](#running-tests)

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
Link to api: [QS99API](qs.eivindharboe.no:8001/swagger-ui/index.html)
Alternatively if you are running your own instance [QS99API localhost](http://localhost:8001/swagger-ui/index.html)

## Security
QS99 is a safe and secure app //FYLL UT MER HER

## CI/CD

### CI
Continous integration is performed with Github Actions. On each push to the main branch a test suite is ran to ensure that new features work properly
Currently we have only implemented CI on the backend. Frontend CI was deemed unnecessary. 

### CD
Continuous deployment is also performed via github actions. Every time there is a push to the main branch a deploy action is run. A Github runner checksout the repository and ssh'es into our own Raspberry Pi and then copies the repository and builds the entire project with the help of a makefile and docker containers. We chose this approach because we had an available Raspberry Pi and we could save some money on server hosting.
Both the backend and the frontend are hosted on the Pi. The backend is a SpringBoot Rest API and the frontend is hosted on a basic nginx server

## Dependencies

### Backend
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
  - JSON Web token is used for security purposes and ensuring only users with required roles can access specific endpoints

### Frontend
- Vue.Js
  - Javascript framework for making web applications
- Babel
  - Javascript trans-compiler that converts our modern ECMAScript code to older versions of JS so that older JSengines can run it
- Axios
  - Promise based HTTP client, performs our XMLHttpRequests
- Core-Js
  - Standard JS library, supports up to ECMAScript 2021
- EsLint/Prettier
  - Used for formatting in development
- Yup
  - Used for form validation
- Vuex
  - Used for state management
- Router 
  - Used for routing         

## Future Work

### Better admin page
The admin page is currently not supported on mobile, and is the most underdeveloped page of all

### Refresh Tokens

### Performance


## Installation Manual

### Prerequisites

- Docker
- Maven

### Instructions 

To build and run the entire project you can use a Docker container(Easiest in a linux environment)

You will need to have docker installed!

Clone the entire project:

```
git clone https://github.com/MadLadsTechnology/QS99.git
```

Run the deploy script in the makefile

```
sudo make -C QS99 deploy
```

After the commands are ran, you will have two docker containers running, frontend on port 80 and backend running on port 8001
You can access the frontend by going to the address: ```http://localhost```

## Running Tests
Clone the repo 

```
git clone https://github.com/MadLadsTechnology/QS99.git
```

Cd into directory and run

```
mvn clean test
```

This will run all tests on the backend


##


🦕 🦑 Heyo letsgo - //TODO: write this readme
