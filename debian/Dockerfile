FROM java:7-jdk

RUN apt-get update && apt-get install -y make
ADD *.java /
ADD Makefile /

RUN make build run

EXPOSE 8089

CMD ["java", "-jar", "/jar/Example.jar"]
