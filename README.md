# CS665 Term Project

## Overview

This is an application that simulates a department (swith focus on the Computer Science Department) of a university.  There is a class hierarchy for the students and employees along with their responsibilities as well as the artifacts that each person interacts with.  

The purpose of the project is to demonstrate the software design patterns in the class.

Outside of using [JUnit](https://junit.org/junit5/) to show output and [Spotless](https://github.com/diffplug/spotless) for automatic formatting of code, the application was deliberately implemented to be self-contained and not to use any other third-party library dependencies to highlight that every design pattern is implemented from the ground up.

The full documentation can be found [here](doc/README.md).

## Build and Output

The application uses Gradle, to build it:

```shell
$ gradle build 
```

### Output

The output of the application runs under a JUnit test, so you can simply run using Gradle:

```shell
$ gradle test 
```

## Design and Implementation

### Assumptions

- A member of the faculty cannot be a student.  In other words, a student named "John Doe" is treated as separate entity as faculty member "John Doe" even though realistically they can be referring to the same person.
- A student takes courses in consecutive school years (no gap).

### Notes 

- Objects/classes are subclassed from `AbstractEntity` or `AbstractEntityRelationship` which implements a `getId()` method.  Since there is a lot of lookup in collections, this basic abstract classes overrides `Object::equals` to ensure value equality through their `getId()`.
- Application relies on `java.util.logging` package to emit messages on the console.

### Class Diagram
### Design Patterns