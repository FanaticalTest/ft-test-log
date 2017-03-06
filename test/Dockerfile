FROM php:7.0-apache
ENV API_SERVER=127.0.0.1
ENV API_PORT=8080
COPY config/php.ini /usr/local/etc/php/
COPY src/ /var/www/html/
EXPOSE 80