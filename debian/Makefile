clean:
	rm -rf jar mysql-connector-java-5.1.35

mysql-connector-java-5.1.35:
	curl -vL http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.35.tar.gz | tar -xz  

SimpleWebServer.jar:
	curl -vL http://www.jibble.org/files/SimpleWebServer.jar > SimpleWebServer.jar

nanohttpd:
	curl -vL https://github.com/NanoHttpd/nanohttpd/archive/Release-2.1.0.tar.gz | tar -zx 

build: mysql-connector-java-5.1.35
	mkdir -p jar
	javac -d jar JDBCExample.java
	echo Main-Class: JDBCExample > jar/manifest.txt
	echo Class-Path: mysql-connector-java-5.1.35-bin.jar >> jar/manifest.txt
	cp mysql-connector-java-5.1.35/mysql-connector-java-5.1.35-bin.jar jar/mysql-connector-java-5.1.35-bin.jar
	cp SimpleWebServer.jar jar
	(cd jar; jar cvfm JDBCExample.jar manifest.txt *.class mysql-connector-java-5.1.35-bin.jar)

run:
	java -jar jar/JDBCExample.jar
