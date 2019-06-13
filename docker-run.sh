docker stop coral-draw

docker rm `docker ps -a -q`

docker run --privileged=true \
           -p 8081:8082 \
           -d --name coral-draw \
           10.10.208.193:5000/coral-draw:3.0.0-1906132057

docker logs -f coral-draw

#docker exec -it fce8d10b5c31 bash