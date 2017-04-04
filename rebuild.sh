#!/usr/bin/env bash

docker-compose down
docker build -t ft-test-log .
docker-compose build
docker-compose up -d
docker-compose ps