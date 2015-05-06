clean:
	rm -rf jar mysql-connector-java-5.1.35 build_jre

mysql-connector-java-5.1.35:
	curl -vL http://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.35.tar.gz | tar -xz  

build: mysql-connector-java-5.1.35
	mkdir -p jar
	cp mysql-connector-java-5.1.35/mysql-connector-java-5.1.35-bin.jar jar/mysql-connector-java-5.1.35-bin.jar
	cp NanoHTTPD.java jar
	cp Example.java jar
	javac -d jar NanoHTTPD.java
	javac -d jar Example.java
	echo Main-Class: Example > jar/manifest.txt
	echo Class-Path: mysql-connector-java-5.1.35-bin.jar >> jar/manifest.txt
	(cd jar; jar cvfm Example.jar manifest.txt *.class)

run:
	java -jar jar/Example.jar

dockerjdk:
	docker rm -f build_container || true
	mkdir -p build_jre
	docker build -t example:jdk .
	docker run --name build_container example:jdk
	docker cp build_container:/opt/Example.jar build_jre
	docker cp build_container:/opt/mysql-connector-java-5.1.35-bin.jar build_jre
	docker rm build_container
	make dockerjre

dockerjre:
	cp Dockerfile.jre build_jre/Dockerfile
	docker build -t example:alpine build_jre

runmysql:
	docker run \
    -e MYSQL_ROOT_PASSWORD=password \
    -e MYSQL_USER=root \
    -e MYSQL_DATABASE=test \
    --rm --name db docker.io/mysql

runexample:
	docker run \
    --link db:db \
    -p 8089:8089 \
    -e EXAMPLE_ROOT_PASSWORD=password \
    -e EXAMPLE_USER=root \
    -e EXAMPLE_DATABASE=mysql://db:3306/test \
    --rm --name example example:alpine 

