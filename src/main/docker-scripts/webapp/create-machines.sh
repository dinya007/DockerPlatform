#!/usr/bin/env bash
docker-machine create \
 -d virtualbox \
 --swarm --swarm-master \
 --swarm-discovery="consul://$(docker-machine ip mh-keystore):8500" \
 --engine-opt="cluster-store=consul://$(docker-machine ip mh-keystore):8500" \
 --engine-opt="cluster-advertise=eth1:2376" \
 master-node

docker-machine create -d virtualbox \
     --swarm \
     --swarm-discovery="consul://$(docker-machine ip mh-keystore):8500" \
     --engine-opt="cluster-store=consul://$(docker-machine ip mh-keystore):8500" \
     --engine-opt="cluster-advertise=eth1:2376" \
   slave-node-1

eval $(docker-machine env --swarm master-node)

docker network create --driver overlay --subnet=10.0.9.0/24 dev-network