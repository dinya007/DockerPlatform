#!/usr/bin/env bash

docker-machine create -d virtualbox \
    --engine-insecure-registry 192.168.99.100:5000 \
    consul-node

eval "$(docker-machine env consul-node)"

docker run -d \
     -p "8500:8500" \
     -h "consul" \
     progrium/consul -server -bootstrap

docker-machine create \
 -d virtualbox \
 --swarm --swarm-master \
 --swarm-discovery="consul://$(docker-machine ip consul-node):8500" \
 --engine-opt="cluster-store=consul://$(docker-machine ip consul-node):8500" \
 --engine-opt="cluster-advertise=eth1:2376" \
 --engine-insecure-registry 192.168.99.100:5000 \
 master-node

docker-machine create -d virtualbox \
     --swarm \
     --swarm-discovery="consul://$(docker-machine ip consul-node):8500" \
     --engine-opt="cluster-store=consul://$(docker-machine ip consul-node):8500" \
     --engine-opt="cluster-advertise=eth1:2376" \
     --engine-insecure-registry 192.168.99.100:5000 \
   slave-node-1

eval $(docker-machine env slave-node-1)

docker network create --driver overlay --subnet=10.0.9.0/24 dev-network

#docker exec -i -t HelloContainer /bin/sh