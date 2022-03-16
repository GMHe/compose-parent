#!/bin/sh
##cnf
APP_PMS="-Xms512m -Xmx5g"
APP_MAIN=cn.compose.admin.ComposeAdminApp

##run
cur=$(dirname $(readlink -f "$0")) && cd $cur/..

APP_CP=$(echo lib/*.jar |tr ' ' ':')
exec java -Dfile.encoding=UTF-8 ${APP_PMS} -DMonitorAgent.service.name=compose-admin -server -cp "runtime:${APP_CP}" ${APP_MAIN}
#exec java -Dfile.encoding=UTF-8 ${APP_PMS} -server -cp "runtime:${APP_CP}" ${APP_MAIN}
