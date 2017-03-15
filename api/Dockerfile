FROM php:7.0-apache
ENV SQL_SERVER=127.0.0.1
ENV SQL_DB=fttestlogphp
ENV SQL_USER=fttestlogphp
ENV SQL_PASSWORD=fttestlogphp
ENV SQL_PORT=3306
ENV APP_VERSION=1.0.0
COPY config/php.ini /usr/local/etc/php/
COPY src/ /var/www/html/
RUN docker-php-ext-install mysqli
RUN chown -R www-data /var/www/html/config/
RUN chown -R www-data /var/www/html/logs/
EXPOSE 80