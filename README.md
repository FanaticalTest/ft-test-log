# ft-test-log

## Component prerequisite
* MySql 5.7
* Java 8.111

## Migration to Java
Migrating the php service into java micro service.

## Functional specification
Customer : The customer is defined by an id in string format and a name. The id must be unique.

## Non-functional specification
TBD

## Run & Debug
* Create an environment variable FTTESTLOG_LOG_FOLDER with the path you want to have to log file
* Run test : `mvn test > log.txt`
* Run test and bypass env LOG_FOLDER : `mvn test -DFTTESTLOG_LOG_FOLDER="C:\dev\github\ft-test-log\logfile" > log.txt`
* Run your service without building it  : `mvn spring-boot:run`
* Run your service without building it and bypass env LOG_FOLDER : `mvn spring-boot:run -DFTTESTLOG_LOG_FOLDER="C:\dev\github\ft-test-log\logfile"`

## Build
* Create an environment variable FTTESTLOG_LOG_FOLDER with the path you want to have to log file
* Build your service : `mvn clean package`
* Run your service : `java -jar target/ft-test-log-2.0.0.jar`

## Docker
### Start docker
mysql and phpmyadmin are in containers. Building or running the service ensure to have the mysql up and running.
`sh rebuild.sh`
### Reset docker
To reset all docker image run the following command
`remove-all-docker.sh`