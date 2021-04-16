#基础镜像
FROM java:8

VOLUME ["/tmp"]
ADD ./ /home/coral-draw/

#指定时区
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ENTRYPOINT ["/home/coral-draw/run.sh"]


#ENTRYPOINT ["java","-Djava.ext.dirs=/","-jar","/mp-starter-1.0.0.jar"]
#EXPOSE 80
