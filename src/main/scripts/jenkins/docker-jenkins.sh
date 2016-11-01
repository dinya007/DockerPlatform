#https://hub.docker.com/_/jenkins/
docker run -p 8080:8080 -p 50000:50000 -v /Users/denis/.docker/machine/machines/default:/var/jenkins_home/.docker/machine/machines/default -v /Users/denis/Documents/Study/MIPT/science_work/Jenkins:/var/jenkins_home localhost:5000/jenkins
