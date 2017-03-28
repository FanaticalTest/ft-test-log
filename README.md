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
* Run test `mvn test -DLOG_FOLDER="C:\dev\github\ft-test-log\logfile"`
* Run your service without building it : `mvn spring-boot:run -DLOG_FOLDER="C:\dev\github\ft-test-log\logfile"`

## Docker
### Start docker
mysql and phpmyadmin are in containers. Building or running the service ensure to have the mysql up and running.
`sh rebuild.sh`
### Reset docker
To reset all docker image run the following command
`remove-all-docker.sh`