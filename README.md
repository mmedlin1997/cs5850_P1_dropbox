# Dropbox
Purpose to project, as a part of Assignment 1 for CS5850 Verification & Validation, is to implement a Dropbox style Java application. The application watches a given local folder, and mirrors its contents to a remote cloud storage, in this case AWS S3. 

A side goal is exercise professional coding form, with focus on proper unit testing, code coverage, code style and bug checking of all classes. 

## Installation
Download source project from GitHub repository:

```
git clone https://github.com/mmedlin1997/cs5850_P1_dropbox
cd cs5850_P1_dropbox
```

## Test
Perform validation tests.
 
`mvn test`

## Run

1 Build the application.
   
   ```
   mvn clean package
   ```
   
   Locate the resulting *.jar file: `target/CS5850_P1_Dropbox-0.0.1-SNAPSHOT.jar`
   
2 Run from the command line.
   
   ```
   cd target
   java -cp CS5850_P1_Dropbox-0.0.1-SNAPSHOT.jar cs5850.CS5850_P1_Dropbox.App
   ```

## Usage
Select a folder on local drive to be used as the drop-box.
A remote folder hosted on AWS S3 will mirror the contents of the local folder.

Changes to files and folders to the local folder also apply to the S3 folder.
Supported file and folder events are:
 * Add
 * Modify
 * Move
 * Delete 

## Site reporting
This project uses the following Maven plugins for code validation and QA:
* [Cobertura](http://www.mojohaus.org/cobertura-maven-plugin/) for code coverage
* [Checkstyle Plugin](https://maven.apache.org/plugins/maven-checkstyle-plugin/) for coding style
* [Findbugs](https://mvnrepository.com/artifact/org.codehaus.mojo/findbugs-maven-plugin/3.0.5)
for bug checking

To generate reports, execute:

```
mvn site
```

Then find reports in:

* `/target/site/cobertura/index.html`
* `/target/site/checkstyle.html`
* `/target/site/findbugs.html`
