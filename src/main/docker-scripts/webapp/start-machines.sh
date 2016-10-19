docker-machine start default
docker-machine regenerate-certs default

docker-machine start mh-keystore
docker-machine regenerate-certs mh-keystore

docker-machine start mhs-demo0
docker-machine regenerate-certs mhs-demo0

docker-machine start mhs-demo1
docker-machine regenerate-certs mhs-demo1

#docker-machine start test-machine
#docker-machine regenerate-certs test-machine