FROM java:7-jdk

RUN apt-get update && apt-get install -y make
ADD *.java /opt/
ADD Makefile /opt/

WORKDIR /opt

RUN make build

EXPOSE 8089
