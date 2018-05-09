FROM openjdk:8-jdk-alpine
MAINTAINER GSD
LABEL description="monitoringday-inventory"

ENV profile none

RUN ["mkdir", "-p", "/opt/app"]
WORKDIR /opt/app

COPY ["target/inventory*.jar", "inventory.jar"]

EXPOSE 8080

#profile = local / k8s
ENTRYPOINT ["java", "-Xmx200m", "-jar", "inventory.jar", "-Dspring.profiles.active=local"]
#ENTRYPOINT ["java", "-Xmx200m", "-jar", "inventory.jar"]