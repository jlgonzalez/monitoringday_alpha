# monitoringday_alpha
Temporary git for monitoring day

To execute
mvn spring-boot:run
mvn spring-boot:run -Drun.jvmArguments="-Dspring.profiles.active=local"

To generate jar file : 
mvn clean install

To create docker file: 
docker build -t myrepo/inventory:md_traced .


To run it
docker run -d -p 8080:8080 -t myrepo/inventory:md_traced
docker run -d -p 8080:8080 -t myrepo/inventory:md_traced -e profile=local


Go to localhost:8080