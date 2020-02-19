#! /bin/sh

CATALINA_OPTS="$CATALINA_OPTS -javaagent:/usr/local/tomcat/aspectjweaver-1.9.5.jar"
CATALINA_OPTS="$CATALINA_OPTS -Dorg.aspectj.weaver.loadtime.configuration=file:/usr/local/tomcat/aop.xml"
CATALINA_OPTS="$CATALINA_OPTS -Daj.weaving.verbose=true"
CATALINA_OPTS="$CATALINA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
# CATALINA_OPTS="$CATALINA_OPTS -verbose:class"
