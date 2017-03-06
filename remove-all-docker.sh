docker-compose down
docker rm -f $(docker ps -a -q)
docker rmi -f $(docker images -q)
docker volume rm $(docker volume ls -qf dangling=true)
docker volume ls
docker images