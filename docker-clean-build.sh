docker rm `docker ps -a -q`
docker rmi --force `docker images | grep coral-drawhub | awk '{print $3}'`
./gradlew clean
./gradlew buildDocker -x test --refresh-dependencies