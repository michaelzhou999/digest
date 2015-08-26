# Digest Algorithm Benchmarking

## Purpose

The purpose of this project is to get a quick estimate on performance of these digest algorithms:

- SHA-1
- SHA-256
- SHA-512
- MD5

## Variation

In addition to the algorithms, the length of the bytes (to be digested) is also varied.

## Build

This is a simple project. You can either build with Maven or simply with `javac`.

### Maven

```
mvn install
```

### Javac

```
cd digest
mkdir -p target/classes
javac src/main/java/digest/* -d target/classes
```

## Run

### Maven

```
mvn install
```

### Java

```
cd digest
java -cp target/classes digest/DigestBenchmark 
```
