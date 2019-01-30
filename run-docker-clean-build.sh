docker rm `docker ps -a -q`
docker rmi --force `docker images | grep drawio | awk '{print $3}'`
gradle clean
gradle buildDocker -x test --refresh-dependencies