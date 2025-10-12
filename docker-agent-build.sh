#!/usr/bin/zsh

export ROOT_PASSWORD=1234
export JENKINS_USER_PASSWORD=1234


# cmd to build the image using docker build.

# $ docker build --build-arg ROOT_PASSWORD=${ROOT_PASSWORD} --build-arg JENKINS_USER_PASSWORD=${JENKINS_USER_PASSWORD} -t "jenkins-ums-builder:latest" -f ums-builder.Dockerfile .

# cmd to build the image using docker compose.

docker compose -f docker-compose-jenkins-env.yml up -d

