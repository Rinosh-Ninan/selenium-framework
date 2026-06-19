# Selenium Java Automation Framework

A production-style Selenium Java automation framework developed using industry-standard design patterns and CI/CD practices.

## Features

* Page Object Model (POM)
* TestNG Framework
* ThreadLocal WebDriver Management
* DriverFactory Design Pattern
* Retry Analyzer
* TestNG Listeners
* Extent Reports
* Screenshot Capture on Failure
* Parallel Execution
* Configurable Test Groups
* Configurable Environments
* Headless Execution

## CI/CD

* Git Version Control
* GitHub Repository
* GitHub Actions Pipeline
* Automated Test Execution
* Extent Report Artifact Upload

## Technology Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* Git
* GitHub
* GitHub Actions

## Project Structure

```text
src/main/java
src/test/java
.github/workflows
pom.xml
testng.xml
```

## Execution

Run all tests:

```bash
mvn clean test
```

Run smoke tests:

```bash
mvn clean test -Dgroups=smoke
```

Run regression tests:

```bash
mvn clean test -Dgroups=regression
```

Run headless:

```bash
mvn clean test -Dheadless=true
```
