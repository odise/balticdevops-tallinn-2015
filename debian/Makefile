clean:
	rm -rf jar mysql-connector-java-5.1.35 

mysql-connector-java-5.1.35:
	curl -vL http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.35.tar.gz | tar -xz  

build: mysql-connector-java-5.1.35
	mkdir -p jar
	cp mysql-connector-java-5.1.35/mysql-connector-java-5.1.35-bin.jar jar/mysql-connector-java-5.1.35-bin.jar
	cp NanoHTTPD.java jar
	cp JDBCExample.java jar
	javac -d jar NanoHTTPD.java
	javac -d jar JDBCExample.java
	echo Main-Class: JDBCExample > jar/manifest.txt
	echo Class-Path: mysql-connector-java-5.1.35-bin.jar >> jar/manifest.txt
	(cd jar; jar cvfm JDBCExample.jar manifest.txt *.class mysql-connector-java-5.1.35-bin.jar)

run:
	java -jar jar/JDBCExample.jar
