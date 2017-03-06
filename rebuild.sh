docker-compose down
cd api
docker build -t fanaticaltest/ft-test-log-php .
cd ..
cd test
docker build -t fanaticaltest/ft-test-log-php-test .
cd ..
docker-compose build
docker-compose up -d
docker-compose ps
echo Database not builded !!!
#echo Building your database... please wait!
#sleep 15s
#curl http://$1:8080/setupdb.php