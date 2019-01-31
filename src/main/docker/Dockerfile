#基础镜像
FROM 10.10.208.193:5000/urcs/java:8

VOLUME ["/tmp"]
ADD ./ /home/drawio/

#指定时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ENTRYPOINT ["/home/drawio/run.sh"]


#ENTRYPOINT ["java","-Djava.ext.dirs=/","-jar","/mp-starter-1.0.0.jar"]
#EXPOSE 80
