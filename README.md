# bank-app-spring-cloud-microservices
## Stage 1 - Monolithic application structure 
With this it will be a monolithic structure of application with a single jar containing whole application.

## Stage 2 - Splitted monolithic application into multiple unique functionality applicaiton `Microservice`
In this stage whole applicaiton is splitted into different micorservices on the basis of unique funationality using Spring-cloud.

## Stage 3 - Multiple microservices with central configuration source and Configuration Server using Spring Cloud Config Server
In this stage multiple microservices has a central configuration source as github repository where all the configuration is stored and properties are served to mciroservices using `Spring cloud Config Server` with all the microservices act as `Config Client`.

## Stage 4 - Using Ribbon Client Side Load Balancer
In this Stage, load will be balanced between multiple instances of same microservice using Spring cloud netflix ribbon as client side load balancer.
