docker stop coral-draw

docker rm coral-draw

docker run --privileged=true \
           -p 8081:8082 \
           -d --name coral-draw \
           --env DB_HOST='192.168.3.10' \
           10.10.208.193:5000/coral-draw:3.0.0-2006261540

docker logs -f coral-draw

#docker exec -it fce8d10b5c31 bash