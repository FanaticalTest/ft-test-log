# ft-test-log

## Component prerequisite
* MySql 5.7
* Java 8.111
* Spring boot 4.1.3

## Migration to Java
Migrating the php service into java micro service.

## Hosts
Ensure to have `http://docker.sak` set to your external IP not localhost.

## Run & Debug
* Create an environment variable FTTESTLOG_LOG_FOLDER with the path you want to have to log file
* Create an environment variable FTTESTLOG_APP_PROPERTIES for the application.properties file
* Run test : `mvn test > log.txt`
* Run test and bypass env FTTESTLOG_LOG_FOLDER : `mvn test -DFTTESTLOG_LOG_FOLDER="C:\path\logfile" > log.txt`
* Run your service without building it  : `mvn spring-boot:run`
* Run your service without building it and bypass env FTTESTLOG_LOG_FOLDER : `mvn spring-boot:run -DFTTESTLOG_LOG_FOLDER="C:\path\logfile"`

## Build
* Create an environment variable FTTESTLOG_LOG_FOLDER with the path you want to have to log file
* Create an environment variable FTTESTLOG_APP_PROPERTIES for the application.properties file
* Build your service : `mvn clean package`  => Read carefully the next section "Build DEV vs PROD"
* Run your service : `java -jar target/ft-test-log-x.x.x.jar`

### Build DEV vs PROD
#### DEV
When you are in DEV you should have in `application.properties`:
* `spring.jpa.hibernate.ddl-auto=create` => Be sure to use a blank db!
* `ft_load_test_data = 1` => It will create test data

#### PROD
When you are in PROD you should have in `application.properties`:
* `spring.jpa.hibernate.ddl-auto=validate` => Does not change anything in the db. If you need to update use `spring.jpa.hibernate.ddl-auto=update` (without guaranty)
* `ft_load_test_data = 0` => It will skip test data

## Test
* For test purpose you can set in the `application.properties` the constant `ft_load_test_data = 0` to not load test data.
* There are 28 automated integration tests implemented.
* For manual test, there is also a json file for postman available on the root of the project `ft-test-log.postman_collection.json`

## Docker
### Start docker
mysql and phpmyadmin are in containers. Building or running the service ensure to have the mysql up and running.
`sh rebuild.sh`
### Reset docker
To reset all docker image run the following command
`remove-all-docker.sh`

## Log
To see the log `http://docker.sak:8080/admin/log/`
