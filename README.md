# Maxar Code Challenge

### Please note*
* I decided to build this on top of Spring boot 
as Trent mentioned he thought it was a framework 
that is utilized on the Java team

### Prerequisites

Ensure the following softwares are installed on your machine: 
* [Maven](https://maven.apache.org/) 
* [Java jdk](https://www.oracle.com/java/technologies/javase-jdk14-downloads.html)
* [Java](https://www.java.com/en/)

### Building the project 
A jar is included in the project
```
api-0.0.1-SNAPSHOT.jar
```

If you'd like to build your own jar run the following command from the root of the project
```
mvn install
```

## Running the project

You may run the jar included in the project
```
java -jar ./api-0.0.1-SNAPSHOT.jar 
```

Alternatively you can build and run your own jar 
```
java -jar <path-to-your-built-jar-file>
```

## Tests

* I've included a context test and one very simple test to check that we're able to instantiate a job

```
src/test/java/com/maxar/api/MaxarCodeChallengeApplicationTests.java
```

## Built With

* [OkHttp](https://square.github.io/okhttp/) - Web client
* [Maven](https://maven.apache.org/) - Dependency Management

