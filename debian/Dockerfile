#FROM debian:wheezy
FROM odise/busybox-java

ADD JDBCExample.class /
ADD mysql-connector-java-5.1.35-bin.jar /

CMD ["java", "-cp", ".:mysql-connector-java-5.1.35-bin.jar","JDBCExample"]
