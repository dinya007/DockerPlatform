#!/usr/bin/env bash

docker-machine rm -f test-machine
docker-machine create \
    --driver virtualbox \
    --engine-insecure-registry 192.168.99.100:5000 \
    test-machine