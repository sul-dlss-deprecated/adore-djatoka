#!/bin/sh
cd /Users/jdeering/Downloads/adore-djatoka-1.1/bin
. ./env.sh
CATALINA_HOME=/Users/jdeering/tomcat
#echo $JAVA_OPTS
#echo $DYLD_LIBRARY_PATH
#echo $KAKADU_HOME

if [ "x" = "x$CATALINA_HOME" ]; then
   echo "CATALINA_HOME not defined, set system variable and try again"
   exit
fi

export JAVA_OPTS

rm -f $CATALINA_HOME/temp/*
$CATALINA_HOME/bin/catalina.sh $*

exit 0
