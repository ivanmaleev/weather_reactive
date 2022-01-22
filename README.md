Weather_reactive
====================================================
Project: Weather (Reactive programming)
----------------------------------------------------
The weather forecast app. It works in the reactive programming mode.
There are two requests in the application.

1. Show a list of all cities with weather.
2. Show detailed weather for the selected city.
The project uses Spring MVC, Spring security, Hibernate.

Configuration
----------------------------------------------------
The project should be configured the Spring Boot.

Usage
----------------------------------------------------
1. Get a list of all cities
Get to "/all".
2. Get city with ID
Get to "/get/{id}"
3. Get city with max temperature
Get to "/get/getmax"