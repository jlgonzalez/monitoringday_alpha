FROM openjdk:8-jdk-alpine
MAINTAINER GSD
LABEL description="monitoringday-productcatalog"

RUN ["mkdir", "-p", "/opt/app"]
WORKDIR /opt/app

COPY ["target/productcatalog*.jar", "productcatalog.jar"]

EXPOSE 8080

#ENTRYPOINT ["java", "-Xmx200m", "-jar", "productcatalog.jar", "-Dprofile=$profile"]
ENTRYPOINT ["java", "-Xmx200m", "-jar", "productcatalog.jar"]