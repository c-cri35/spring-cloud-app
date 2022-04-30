# Final Project for Spring Cloud Computing Course 🍃

There are two client services, _user-service_ and _appointment-service_, which are registered using **eureka**. They both use **spring cloud config** to handle different properties that can be found [here](https://github.com/c-cri35/spring-config-server). 

The two services communicate using a Rest Template and are routed by an **API Gateway**. 

To also show the usage of **CircuitBreaker**, a forced configurable delay was introduced to GET _localhost:8080/user/{id}_. If a user tries to create an appointment through the _appointment-service_ and the _user-service_ takes too long to respond to validate the car owner or the car owner is not registered, the appointment cannot be completed.

You can download the Postman Collection containing available requests from [here](API-calls.postman_collection.json)