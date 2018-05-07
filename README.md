# monitoringday_alpha
Temporary git for monitoring day

To execute
mvn spring-boot:run

To generate jar file : 
mvn clean install

To create docker file: 
docker build -t myrepo/inventory:alpha .


To run it
docker run -t myrepo/inventory:alpha -p 8080:8080

Go to localhost:8080