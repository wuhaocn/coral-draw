docker stop coral-drawhubdb
docker rm coral-drawhubdb
docker run -d --name coral-drawhubdb -p 3306:3306 wuhaocn/mysqldrawdb:5.6.40
docker update coral-drawhubdb --restart=always


### 创建数据库 DrawDB [root coral@2018],[数据库表自动生成]

docker stop coral-drawhub
docker rm coral-drawhub
docker run --privileged=true \
           -p 8080:8082 \
           -d --name coral-drawhub \
           --env DB_HOST='10.1.0.35' \
           wuhaocn/coral-drawhub:3.0.0-2104151418
docker update coral-drawhub --restart=always
