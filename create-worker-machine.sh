#!/usr/bin/env bash

docker-machine rm -f worker1
docker-machine create \
    --driver virtualbox \
    --engine-insecure-registry 192.168.99.100:5000 \
    swarm2

eval $(docker-machine env swarm2)
docker swarm init --advertise-addr 192.168.99.103
docker swarm join-token worker