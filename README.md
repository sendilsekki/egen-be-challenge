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

DB Details
1. A MongoDB instance should be running your local machine.
2. Create a DB name "egen-be-challenge" in the MongoDB. (Note: The DB name is also configured in application.properties file, if you create a DB with different name this configuration should be changed).
3. Create a collection named "Metrics"
4. Create a collection named "Alerts"
 
Properties Configured in application.properties file.
1. User Base Weight: user.baseWeight=150
2. MongoDB DB Name: db.mongodb.dbname=egen-be-challenge

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

User Metrics Read Service Sample Output
[
    {
        "timeStamp": 1462169722759,
        "value": 150,
        "baseWeight": 150
    },
    {
        "timeStamp": 1462169729184,
        "value": 151,
        "baseWeight": 150
    },
    {
        "timeStamp": 1462169734276,
        "value": 152,
        "baseWeight": 150
    }
]

User Alert Read Service Sample Output 
[
    {
        "timeStamp": 1462169805495,
        "alertMessage": "User Is Over Weight"
    },
    {
        "timeStamp": 1462169810610,
        "alertMessage": "User Is Over Weight"
    },
    {
        "timeStamp": 1462169815695,
        "alertMessage": "User Is Over Weight"
    }
]