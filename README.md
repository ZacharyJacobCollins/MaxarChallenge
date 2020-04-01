# Maxar Code Challenge

### Please note*
* I decided to build this on top of Spring boot 
as Trent mentioned it was a framework he thought 
was used on the prospective team i'd be on.

* The included api endpoint does not artificially
 wait 1-10 seconds, however the JobClient included 
 will have no problem getting data from an endpoint that
 does respond randomly between 1-10 seconds
 
* Additionally the program when run on modern computers should 
function fine for the specifics of the requirements (sending 1-2000 requests).
It may run out of memory if asked to query more requests than in the requirements or run on a 
slower computer as the number of threads are not limited per system specification.

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

End with an example of getting some data out of the system or using it for a little demo

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

