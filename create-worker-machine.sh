#!/usr/bin/env bash

docker-machine rm -f worker1
docker-machine create \
    --driver virtualbox \
    --engine-insecure-registry 192.168.99.100:5000 \
    worker1

docker swarm join-token worker1