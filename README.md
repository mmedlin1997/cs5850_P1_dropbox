# cs5850 Project 1 Dropbox

## Description

## Installation

## Usage

## Test

`mvn integration-test`

## Site reporting
This project uses the following plugins for code validation and QA:
1 **Cobertura** for code coverage
  -
  -
2 **Checkstyle** for coding style
  -Apache Maven Checkstyle Plugin
  -[https://maven.apache.org/plugins/maven-checkstyle-plugin/]
3 **Findbugs** for bug checking
  -
  -[https://mvnrepository.com/artifact/org.codehaus.mojo/findbugs-maven-plugin/3.0.5]

To generate report, execute:
`mvn site`

Then find reports in:
1 `/target/site/cobertura/index.html` code coverage report
2 `/target/site/checkstyle.html` style report
3 `/target/site/findbugs.html` bug report
