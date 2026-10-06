#!/bin/bash

echo "Stop running docker container: "
docker stop ctrsystem_ct-app_1
docker rm ctrsystem_ct-app_1

echo "Clean up docker image: registry.gitlab.com/nvsoft/ct-server:latest"

docker rmi registry.gitlab.com/nvsoft/ct-server:latest

echo "start ct server"
docker-compose -f docker-compose.yml -f docker-compose.override.yml up -d ct-app
