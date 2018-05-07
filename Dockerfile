FROM openjdk:8-jdk-alpine
MAINTAINER GSD
LABEL description="monitoringday-inventory"

RUN ["mkdir", "-p", "/opt/app"]
WORKDIR /opt/app

COPY ["target/inventory*.jar", "inventory.jar"]

EXPOSE 8080

#profile = local / k8s
ENTRYPOINT ["java", "-Xmx200m", "-jar", "inventory.jar", "-Dprofile=$profile"]
#ENTRYPOINT ["java", "-Xmx200m", "-jar", "inventory.jar"]