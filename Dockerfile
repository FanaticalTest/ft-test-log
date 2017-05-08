FROM ubuntu:16.04
MAINTAINER Samy Kacem

VOLUME /tmp
EXPOSE 8080

RUN apt-get update && apt-get install -y default-jre && apt-get clean

RUN mkdir /home/ft-test-log/ && mkdir /home/ft-test-log/logs/

WORKDIR /home/ft-test-log/

COPY src/main/resources/application.properties application.properties
COPY src/main/resources/logback.xml logback.xml
ADD target/ft-test-log-2.0.0.jar ft-test-log-2.0.0.jar

ENV FTTESTLOG_LOG_FOLDER="/home/ft-test-log/logs/"
ENV FTTESTLOG_APP_PROPERTIES="/home/ft-test-log/application.properties"

ENTRYPOINT [ "sh", "-c", "java -jar /home/ft-test-log/ft-test-log-2.0.0.jar" ]