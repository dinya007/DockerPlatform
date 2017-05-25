#!/usr/bin/env bash

docker-machine rm -f worker1
docker-machine create \
    --driver virtualbox \
    --engine-insecure-registry 192.168.99.100:5000 \
    swarm2

eval $(docker-machine env swarm2)
docker swarm init --advertise-addr 192.168.99.102
docker swarm join-token worker

docker swarm join \
    --token SWMTKN-1-5itl0czy0e9r5gzwxm4u0w411u8kxc771833o2mza3elb8qisj-2atiml1y2kieccx5e8mu8pee6 \
    192.168.99.102:2377

docker-machine regenerate-certs worker1