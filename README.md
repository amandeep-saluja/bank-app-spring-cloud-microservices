# bank-app-spring-cloud-microservices
## Stage 1 - Monolithic application structure 
With this it will be a monolithic structure of application with a single jar containing whole application.

## Stage 2 - Splitted monolithic application into multiple unique functionality applicaiton `Microservice`
In this stage whole applicaiton is splitted into different micorservices on the basis of unique funationality using Spring-cloud.

## Stage 3 - Multiple microservices with central configuration source and Configuration Server using Spring Cloud Config Server
In this stage multiple microservices has a central configuration source as github repository where all the configuration is stored and properties are served to mciroservices using `Spring cloud Config Server` with all the microservices act as `Config Client`.

## Stage 4 - Using Ribbon Client Side Load Balancer/ Spring cloud LoadBalancer
In this Stage, load will be balanced between multiple instances of same microservice using Spring cloud netflix ribbon as client side load balancer.

## Stage 5 - Discovery Server used to fetch the services details using Netflix Eureka and inbuild netflix-ribbon used for load balancing
In this Stage, discovery server is used to get the registered services and used to register new services for getting hostname and port details dynamically.

## Stage 6 - Circuit Breaker pattern and Fallback pattern for resilience or fault tolerant microservice
In this Stage, hystrix is used as Circuit breaker pattern to break circuit and fallback pattern to salvage with alternative path using fallback method.

## Stage 7 - Using single point of contact for UI - ZUUL API Gateway
Here we are using single point of contact to communicate with different microservices using a API Gateway Server. It used ribbon as load balancer and hystrix for circuit breaker pattern.

## Stage 8 - Open Feign as Declarative client for communicating other microservices
We have replaced the Rest Template with Open Feign Declarative client for fetching data from different microservices.

## Stage 9 - Tracing requests using Sleuth and Zipkin
In this Stage, we use Sleuth for creating the logs with extra information like microservice name, trace id, span id and boolean flag for zipkin. 
Using these info zipkin creates tracing system for tracking the request among microservices. 
For zipkin, we can use a self-contained jar file for running zipkin server using command `curl -sSL https://zipkin.io/quickstart.sh | bash -s` and then `java -jar zipkin.jar`.
