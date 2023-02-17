### RBC_Stock_Assessment -  Spring Boot Application
The repository is a prototype of spring boot application which serves the purpose of maintaining the weekly stock prediction data of [Dow Jones Industrial Index.](http://archive.ics.uci.edu/ml/datasets/Dow+Jones+Index#)

### Application Flow Diagram
<img src="https://github.com/manimayan/RBC_Stock_Assessment/blob/main/src/main/resources/assets/Application_FlowDiagram.png" width="640" height="230" />

### Project Resources
➲ &nbsp; [run_instructions.txt](https://github.com/manimayan/RBC_Stock_Assessment/blob/main/src/main/resources/assets/run_instructions.txt) <br /> ➲ &nbsp; [SQL Schema](RBC_Stock_Service.sql) <br /> ➲ &nbsp; [Postman Collection](https://github.com/manimayan/RBC_Stock_Assessment/blob/main/src/main/resources/assets/RBC_Stock_API's.postman_collection.json) <br /> ➲ &nbsp; [Stock Upload Sample File](https://github.com/manimayan/RBC_Stock_Assessment/blob/main/src/main/resources/assets/dow_jones_index.data)

#### The repository leverages various tools and stack such as, 
➲ &nbsp; Language : &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  [Java 11](https://www.oracle.com/ca-en/java/technologies/downloads/#java11)<br />
➲ &nbsp; Framework : &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  [Spring Boot 2.7.8](https://spring.io/projects/spring-boot), &nbsp;    [JPA with Hibernate 5.x ORM](https://spring.io/projects/spring-data-jpa),&nbsp;   [JUnit & Mockito](https://junit.org/junit4/)<br />
➲ &nbsp; Integration framework : &nbsp;  &nbsp;  [RESTful Web services](https://en.wikipedia.org/wiki/Representational_state_transfer),&nbsp;   [Swagger](https://swagger.io/)<br />
➲ &nbsp; Webserver :&nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  [Apache tomcat 9.0](https://tomcat.apache.org/download-90.cgi)<br />
➲ &nbsp; Database :  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp; &nbsp;  &nbsp;[MySQL](https://downloads.mysql.com/archives/community/) <br />
➲ &nbsp; Build & Dependency :  &nbsp;  &nbsp;  &nbsp;  &nbsp;[Gradle](https://gradle.org/)<br />
➲ &nbsp; Tools used :&nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;  [Sonar Lint-Code Review](https://www.sonarlint.org/),&nbsp;   [Postman](https://www.postman.com/),  &nbsp;[IntelliJ](https://www.jetbrains.com/idea/)

#### Key Features 
➲ &nbsp; Implementaion of [BeanReader API]() to unmarshal CSV, delimited and fixed length stream formats. <br /> ➲ &nbsp; 
Supports&nbsp;  .txt,&nbsp;  .data,&nbsp;  .csv&nbsp;  file formats for uploading stocks. <br /> ➲ &nbsp; Exposes custom exception codes for better API readability. <br /> ➲ &nbsp;  Implemented Spring boot validation / javax validation. <br /> ➲ &nbsp; Supports Cross-origin resource sharing (CORS). <br /> ➲ &nbsp;  Implemented Global exception handling. <br /> ➲ &nbsp; Swagger API documentation.

#### Future Enhancements
➲ &nbsp; Spring WebFlux (supports asynchronous and event-driven processing for Stock Service API's). <br /> ➲ &nbsp; 
Spring Batch can be implemented for Stock Service upload API (batch processing to handle huge volume of input). <br /> ➲ &nbsp; Code coverage can be improved by writing more unit test cases . <br /> ➲ &nbsp;  Can support Containerization (Docker) . <br /> ➲ &nbsp; Spring Profiles can be implemented to make the application support multi environment. <br /> ➲ &nbsp;  JSON Web Token(JWT) standard Can be implemented to improve Stock Service API security
