FROM tomcat:8.0
MAINTAINER tangsheng
# COPY path-to-your-application-war path-to-webapps-in-docker-tomcat
COPY target/MovieDB-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/


