#!/usr/bin/env bash

docker-machine rm -f test-machine
docker-machine create \
    --driver virtualbox \
    --engine-insecure-registry 192.168.99.100:5000 \
    test-machine

#docker exec -it ubuntu_bash bash

#docker run -d -v /Users/denis/Documents/Java/Idea_Projects/HelloWorld/dockerlogs:/usr/local/tomcat/logs -p 8080:8080 192.168.99.100:5000/hello-world:1.0