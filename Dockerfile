FROM ubuntu:22.04
RUN apt-get -y update
RUN apt-get -y install openjdk-21-jdk wget
RUN wget https://dlcdn.apache.org/tomcat/tomcat-9/v9.0.89/bin/apache-tomcat-9.0.89.tar.gz
RUN tar xzvf apache-tomcat-*tar.gz
COPY OnlineBankingWebsite.war /apache-tomcat-9.0.89/webapps/
COPY robots.txt /apache-tomcat-9.0.89/webapps/ROOT/
COPY sitemap.xml /apache-tomcat-9.0.89/webapps/ROOT/
EXPOSE 8080
CMD /apache-tomcat-9.0.89/bin/catalina.sh run