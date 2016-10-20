docker-machine start default
docker-machine regenerate-certs default

docker-machine start mh-keystore
docker-machine regenerate-certs mh-keystore

docker-machine start master-node
docker-machine regenerate-certs master-node

docker-machine start slave-node-1
docker-machine regenerate-certs slave-node-1

#docker-machine start test-machine
#docker-machine regenerate-certs test-machine