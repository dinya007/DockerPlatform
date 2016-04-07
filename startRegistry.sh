#!/usr/bin/env bash

eval "$(docker-machine env default)"

docker run -d -p 5000:5000 --restart=always --name registry -e REGISTRY_STORAGE_DELETE_ENABLED=true registry:2

docker push localhost:5000/tomcat:8-jre8
docker push localhost:5000/registry:2
docker push localhost:5000/hello-world:1.0
docker push localhost:5000/hello-world:1.1
docker push localhost:5000/hyper/docker-registry-web