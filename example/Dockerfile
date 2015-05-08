FROM java:7-jdk

RUN apt-get update && apt-get install -y curl

WORKDIR /opt

ADD *.java ./
RUN javac NanoHTTPD.java && \
      javac Example.java && \
      echo Main-Class: Example > manifest.txt && \
      echo Class-Path: mysql-connector-java-5.1.35-bin.jar >> manifest.txt && \
      jar cvfm Example.jar manifest.txt *.class && \ 
      curl -vL \
        http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.35.tar.gz \
        | tar --strip=1 -xz mysql-connector-java-5.1.35/mysql-connector-java-5.1.35-bin.jar

EXPOSE 8089
