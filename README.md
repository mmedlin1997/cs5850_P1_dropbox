# cs5850 Project 1 Dropbox

## Description

## Installation
Download from github
```
git clone
git something
```

## Usage



## Test

`mvn test`

or

`mvn integration-test`

## Run

1 Build
   
```
mvn clean package
```
   
   Locate: 
   
   ```
   target/CS5850_P1_Dropbox-0.0.1-SNAPSHOT.jar.jar
   ```
   
2 Run
   
   ```
   java -cp target/CS5850_P1_Dropbox-0.0.1-SNAPSHOT.jar  cs5850.CS5850_P1_Dropbox.App```

## Site reporting
This project uses the following Maven plugins for code validation and QA:
* [Cobertura](http://www.mojohaus.org/cobertura-maven-plugin/) for code coverage
* [Checkstyle Plugin](https://maven.apache.org/plugins/maven-checkstyle-plugin/) for coding style
* [Findbugs](https://mvnrepository.com/artifact/org.codehaus.mojo/findbugs-maven-plugin/3.0.5)
for bug checking

To generate report, execute:

```
mvn site
```

Then find reports in:

* `/target/site/cobertura/index.html`
* `/target/site/checkstyle.html`
* `/target/site/findbugs.html`
