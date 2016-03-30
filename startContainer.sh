#!/usr/bin/env bash

eval "$(docker-machine env test-machine)"

docker run -d -p 8081:8080 --name hello-world-2 192.168.99.100:5000/hello-world:1.4

#eval "$(docker-machine env default)"