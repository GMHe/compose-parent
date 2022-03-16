#!/bin/sh
##cnf
APP_PMS="-Xms512m -Xmx5g"
APP_MAIN=cn.ecpark.infrastructure.yangjian.custom.Application

##run
cur=$(dirname $(readlink -f "$0")) && cd $cur/..

APP_CP=$(echo lib/*.jar |tr ' ' ':')
exec java -Dfile.encoding=UTF-8 ${APP_PMS} -javaagent:/data/www/soft/ecpark-agent/lib/ecpark-agent-appid.jar -javaagent:/data/www/soft/ecpark-agent/lib/ecpark-agent.jar -DMonitorAgent.service.name=yangjian-custom -server -cp "runtime:${APP_CP}" ${APP_MAIN}
#exec java -Dfile.encoding=UTF-8 ${APP_PMS} -server -cp "runtime:${APP_CP}" ${APP_MAIN}
