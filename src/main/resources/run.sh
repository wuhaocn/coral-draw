#!/bin/bash

ulimit -c unlimited
ulimit -n 32768

source /etc/profile

cp -rf /etc/hosts /tmp/hoststmp
sed -i '$a\HB_HOST urcs_hbase' /tmp/hoststmp
sed -i '$a\REDIS_HOST urcs_redis' /tmp/hoststmp
sed -i '$a\KAFKA_HOST urcs_kafka' /tmp/hoststmp
sed -i "s/HB_HOST/$HB_HOST/g" /tmp/hoststmp
sed -i "s/REDIS_HOST/$REDIS_HOST/g" /tmp/hoststmp
sed -i "s/KAFKA_HOST/$KAFKA_HOST/g" /tmp/hoststmp
echo $DB_HOST
echo $REDIS_HOST
cat /tmp/hoststmp
cat /tmp/hoststmp > /etc/hosts

basePath=$(cd "$(dirname "$0")";pwd)
SERVICE_HOME=$basePath

SERVICE_LIBS="$SERVICE_HOME/"

SERVICE_MAIN="com.mxgraph.CoralDrawApplication"
declare -a JAVA_ARGS
JAVA_ARGS[0]="-Xmx256m"
JAVA_ARGS[1]="-Xms256m"

exec $JAVA_HOME/bin/java -Duser.dir=$SERVICE_HOME ${JAVA_ARGS[@]} -classpath $SERVICE_HOME:$SERVICE_LIBS/* $SERVICE_MAIN