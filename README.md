# Brainfuck interpreter
Simple java console application that reads brainfuck source code and executes it.

## Build
The package uses [Apache Maven](https://maven.apache.org), so run:
```
mvn package
```

## Usage
Basic syntax:
```
java -jar target\bf-<version>.jar "<brainfuck source code>" ["<input string>"]
```
Example:
```
java -jar target\bf-0.1.0.jar "++++++++++++++++++++++++++++++++++++++++++++++++.>,." "1"
```
outputs `01`
