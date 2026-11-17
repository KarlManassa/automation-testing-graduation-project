# OrangeHRM Automation Testing Project

## Overview

This project is an automated testing framework for the OrangeHRM web application. It covers the main application functionalities using Selenium WebDriver, Java, TestNG, and Maven.

The framework follows the Page Object Model (POM) design pattern and includes data-driven testing, parallel execution, retry mechanisms, and Allure reporting.

## Application Under Test

- **Application:** OrangeHRM Open Source Demo
- **URL:** https://opensource-demo.orangehrmlive.com/web/index.php/auth/login

## Technologies & Tools

- Java 17
- Selenium WebDriver 4.52.0
- TestNG 7.9.0
- Maven
- Allure Reports
- Jackson (JSON Test Data)
- Log4j2
- Page Object Model (POM)
- IntelliJ IDEA

## Project Structure

```text
src
└── test
    ├── java
    │   ├── base
    │   ├── pages
    │   ├── utils
    │   ├── login
    │   ├── pim
    │   ├── admin
    │   └── footerandsidebar
    └── resources
        ├── config.properties
        ├── testdata.json
        ├── log4j2.xml
        └── testng.xml

pom.xml
README.md
.gitignore
```

## Test Coverage

The project contains 11 automated test cases:

### Login Tests

1. Verify login with valid credentials.
2. Verify login with invalid credentials.
3. Verify required field validation when login fields are empty.

### PIM / Employee Tests

4. Search for an existing employee.
5. Search for a non-existing employee.
6. Verify Add Employee page fields.
7. Verify required validation when the first name is empty.
8. Add a new employee and verify the employee can be found in the employee list.

### Admin Tests

9. Verify the Add User form fields.

### Footer & Sidebar Tests

10. Verify footer branding and the OrangeHRM external link.
11. Verify sidebar menu items.

## Framework Features

- Page Object Model (POM).
- Explicit waits for reliable element interactions.
- Data-driven testing using JSON files and TestNG DataProviders.
- Parallel test execution using TestNG.
- ThreadLocal WebDriver for thread-safe parallel execution.
- Retry Analyzer for failed tests.
- Allure reporting.
- Centralized configuration using properties files.
- Logging support using Log4j2.

## Prerequisites

Make sure the following tools are installed:

- JDK 17 or later.
- Apache Maven.
- Google Chrome.
- Allure Commandline (for generating and viewing reports).

## How to Run

Clone the repository:

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

Navigate to the project directory:

```bash
cd Graduation_Project
```

Run all tests:

```bash
m