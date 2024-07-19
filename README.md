# Functionality (Central monitoring service)
This repo contains a spring-boot which listens to NATS messages at 2 dedicated topics/subjects and process them to judge
if they are beyond a certain threhold.


# Technical details and Pre-requisites
- Written using Spring Boot framework and comprising multiple modules
- Needs to have the Docker desktop running with Nats server running as a docker container
- Also the other project warehouse-service should be running



# How to run locally
- First run the nats server on docker using this command : <If the nats server is already running, skip this command>
  ```kotlin
  docker run -p 4222:4222 -ti nats:latest
  ```
- Then clone the master branch. Ensure that you have at least JDK17 and Maven3.X available. Build the project using the command :
  ```kotlin
  mvn clean package -DskipTests
  ```
- Now run the app using the below command :
  ```kotlin
  java  -jar target/central-monitoring-service-0.0.1-SNAPSHOT.jar
  ```
- Now send a UDP message using the command :
  ```kotlin
    nc -u localhost 3344
  ```
  and the message should be
  ```kotlin
  {"sensor_id" : "1", "value": "30"}
  ```
- You can see the below message in the log
  ```kotlin
  Temp is 60 which is beyond threshold
  ```

