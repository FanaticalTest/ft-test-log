FROM frolvlad/alpine-oraclejdk8:slim

VOLUME /tmp

ENV FTTESTLOG_LOG_FOLDER=/var/ft-test-log/logs/
ENV TTESTLOG_APP_PROPERTIES=/var/ft-test-log/application.properties

RUN cd /var && mkdir ft-test-log && cd /var/ft-test-log && mkdir logs
RUN chmod 777 /var/ft-test-log
COPY src/main/resources/application.properties /var/ft-test-log/

ADD target/ft-test-log-2.0.0.jar app.jar
RUN sh -c 'touch /app.jar'
ENV JAVA_OPTS=""

EXPOSE 8080

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]