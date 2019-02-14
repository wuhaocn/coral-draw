docker stop drawio1.0

docker rm `docker ps -a -q`

docker run --privileged=true \
           -p 8081:8081 \
           -d --name drawio1.0 \
           10.10.208.193:5000/drawio:1.0

docker logs drawio1.0

#docker exec -it fce8d10b5c31 bash