#!/usr/bin/env bash

eval "$(docker-machine env default)"

docker run -i -t -p 8080:8080 -e REGISTRY_HOST=192.168.99.100 -e REGISTRY_PORT=5000 hyper/docker-registry-web

#docker exec -it ubuntu_bash bash
