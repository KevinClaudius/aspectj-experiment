#!/usr/bin/env bash
set -euxo pipefail

export CATALINA_HOME="/usr/local/tomcat"

# Clean up existing containers/images to ensure we are starting fresh
docker stop aspectj-experiment_webapp_1 || true
docker stop aspectj-experiment_h2_1 || true
docker rm aspectj-experiment_webapp_1 || true
docker rm aspectj-experiment_h2_1 || true
docker rmi tomcat-base || true

# Use separate build dir so docker context is not polluted with megabytes of expanded tomcat binaries
pushd docker-build
docker build -f Dockerfile-tomcat-base . -t tomcat-base
popd

# Build sample java webapp.  Uses host machine's maven repo cache for speed.
docker run -it --rm -v "$PWD":/target:delegated -v ~/.m2:/root/.m2:delegated -w /target tomcat-base mvn clean install

docker-compose up