# Measure server

### Get data from HT2000 CO2, temperature, humidity from message broker, get weather from openweathermap.org

###Tech stack
- JDK 8+;
- Maven;
- Spring boot;
- PostgreSQL
- Rabbit MQ;

###Configuration File

Set properties in application.properties: 
- DB connection
- URL telemetry station
- Connection properties openweathermap.org
- RabbitMQ properties
- Scheduler properties

###API Documentation

Measure controller

- GET /rest - Get station status
- GET /rest/page - Get current measure
- GET /rest/today - Get measure by current date
- GET /rest/last - Get last measure
- GET /measures - Get all measures
- GET /today - Get today measures
- GET /graph 

Weather controller

- GET /rest/weatherlast - Get weather
- GET /rest/weatherfilter - Get weather between dates
