# egen-be-challenge (personal weight tracker)
A system that works like an IoT platform – a personal weight tracker. This is developed with Spring Boot. This system is responsible for,
a) Consuming data sent from different sensors through Rest Service
b) Storing the received data in MongoDB
c) Running the data through different rules to make basic predictions

Sample JSON input for creating a user metric data

```json
{
  "timeStamp": "1458062848734", 
  "value": "153"
}
``` 

where timeStamp indicates when this data was sent and the value indicates a metric.

## How to build it?
```
mvn clean package
```

This creates a jar file in target directory 'egen-be-challenge-0.0.1-SNAPSHOT.jar'

## How to run it?
```
mvn package && java -jar target/egen-be-challenge-0.0.1-SNAPSHOT.jar
```

The Rest service URL are.
1. To add a User Metrics data (json input data should be sent by post): http://localhost:8080/userMetrics/create
2. To get all the User Metrics data: http://localhost:8080/userMetrics/read
3. To get all the User Metrics data for a timestamp range: http://localhost:8080/userMetrics/readByTimeRange?fromTimeStamp=FROM_TIMESTAMP_INPUT&toTimeStamp=TO_TIMESTAMP_INPUT
4. To get all the User Alert data: http://localhost:8080/userAlert/read
5. To get all the User Alert data for a timestamp range: http://localhost:8080/userAlert/readByTimeRange?fromTimeStamp=FROM_TIMESTAMP_INPUT&toTimeStamp=TO_TIMESTAMP_INPUT

