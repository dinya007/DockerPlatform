#!/usr/bin/env bash

eval "$(docker-machine env default)"

docker run -d -p 5000:5000 --restart=always --name registry registry:2

docker push localhost:5000/tomcat:8-jre8