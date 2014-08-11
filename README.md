# Requirements

* Java 1.8
    * please set JAVA_HOME environment variable 
* Maven
* Bower
    * Node
    * NPM

# How to run

## Console

1. `bower install`
1. `mvn spring-boot:run`

## IntelliJ
 
1. import this project into IntelliJ by select pom.xml or the folder that is containing pom.xml file.
1. `bower install`
1. run whiteship.Application that has main method.

## Packaging

1. `mvn package`
1. `java -jar target/start-1.0-SNAPSHOT.war`
 