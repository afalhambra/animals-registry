# animals-registry

Holds in-memory object with some useful information regarding dogs. It also allows you to run
some queries on this elements to get information about their average weight, oldest dog based
on a date, and so forth.

## Getting Started

This library will be packed as a jar file and hosted on a Maven repository, so it can be used by third parties by 
either adding a reference in the dependency pom.xml file or by adding the jar file along with all the third dependencies
 to their classpath.

### Prerequisites

This library requires Java version 1.8 or above.

When using Maven, you can just add a dependency in your POM file to use it.

```
    <dependency>
        <groupId>org.mycompany.animals.dogs</groupId>
        <artifactId>animals-registry</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
```

In case you wanted to use this library without using Maven, you should take into account this project
requires some other third parties to work properly. So you should add all of these dependencies manually to your
classpath.
Below a list of the dependencies used by this project taken
from Maven repository.

```
    <dependency>
        <groupId>javax.xml.bind</groupId>
        <artifactId>jaxb-api</artifactId>
        <version>2.3.1</version>
    </dependency>
    <dependency>
        <groupId>com.sun.xml.bind</groupId>
        <artifactId>jaxb-impl</artifactId>
        <version>2.3.1</version>
    </dependency>
    <dependency>
        <groupId>com.sun.istack</groupId>
        <artifactId>istack-commons-runtime</artifactId>
        <version>3.0.10</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback</groupId>
        <artifactId>logback-classic</artifactId>
        <version>1.2.3</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback.contrib</groupId>
        <artifactId>logback-json-classic</artifactId>
        <version>0.1.5</version>
    </dependency>
    <dependency>
        <groupId>ch.qos.logback.contrib</groupId>
        <artifactId>logback-jackson</artifactId>
        <version>0.1.5</version>
    </dependency>
```

### How to use

This main functionality of this library is exposed and implemented in a class called DogRegistryImpl inside 
the org.animals.dogs package.

This class can be accessed through a factory class called DogRegistryFactory which is accessible at the same time
through an abstract factory class called AnimalFactory. 

FactoryProvider class will allow you to start getting a particular instance of this factory.

An example of how you can use it is shown below:

```
import org.mycompany.animals.AnimalFactory;
import org.mycompany.animals.AnimalType;
import org.mycompany.animals.FactoryProvider;
import org.mycompany.animals.dogs.DogRegistry;
import org.mycompany.animals.dogs.config.DogRegistryConfig;
import org.mycompany.animals.dogs.domain.DogBreed;

import javax.xml.bind.JAXBException;

public class DogsRegistryClient {

    public static void main(String[] args) throws JAXBException {


        AnimalFactory animalFactory = FactoryProvider.getFactory(AnimalType.DOG);
        DogRegistry dogRegistry = (DogRegistry) animalFactory.load("src/test/resources/dogs.xml");
        DogRegistryConfig.setEnableLogging(true);

        double avgWeight = dogRegistry.averageWeight(DogBreed.GREYHOUND);
    }
}
```

As shown above you can also enable/disable logging of this library in case you need to troubleshoot something.
This logging will be displayed in the standard console output (no log file will be generated).
This is achieved by calling:

```
DogRegistryConfig.setEnableLogging(true); // Enable logging
DogRegistryConfig.setEnableLogging(false); // Disable logging
```

Example of an output

```
12:00:01.374 [main] DEBUG o.m.animals.dogs.DogRegistryImpl - Greyhound average weight is 29.0
```

## Running the tests

In order to run the library tests, you will first need to import the library as a Maven project.
After that you will be able to run the automated test along with the micro-benchmarks.

You will need Java version 1.8 or above.
 

### Automated test cases

To run the automated test cases you should:

1. Open a terminal window and change directory to your Maven project. You should be in a directory that contains pom.xml file,
2. Run the below command

    ```
    mvn -Dtest=DogRegistryTest test
    ```

### Micro-benchmark test cases

To run the automated test cases you should:

1. Open a terminal window and change directory to your Maven project. You should be in a directory that contains pom.xml file,
2. Run the below command

    ```
    mvn -Dtest=DogRegistryBenchMarkTest test
    ```

## API Documentation

To generate all the API documentation, follow steps below:
 
1. Open a terminal window and change directory to your Maven project. You should be in a directory that contains pom.xml file,
2. Run the below command

    ```
    mvn javadoc:javadoc
    ```

## Built With

* [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - The language used
* [Maven](https://maven.apache.org/) - Build and Dependency Management
* [Intellij](https://www.jetbrains.com/idea/) - IDE used

## Versioning

For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Antonio Fernandez Alhambra** - *Initial work* - [Github profile](https://github.com/afalhambra/)

