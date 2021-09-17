## step1
### 安装docker环境


## step2
### 创建数据环境
docker stop coral-drawhubdb
docker rm coral-drawhubdb
docker run -d --name coral-drawhubdb -p 3333:3306 wuhaocn/mysqldrawdb:5.6.40
docker update coral-drawhubdb --restart=always

## step3
### 创建数据库 DrawDB [root coral@2018],[数据库表自动生成]【重要】

## step4
### 安装服务
docker stop coral-drawhub
docker rm coral-drawhub
docker run --privileged=true \
           -p 8888:8082 \
           -d --name coral-drawhub \
           --env DB_HOST='10.40.1.20:3333' \
           wuhaocn/coral-drawhub:3.0.0-2109141820
docker update coral-drawhub --restart=always

docker logs -f coral-drawhub


## step5
### 验证服务：http://10.3.4.111:8082/