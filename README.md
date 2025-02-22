# tech-test
This repository contains the implementation of automated tests for two distinct scenarios: a UI test and an API test.
# Automation Tests for UI and API Scenarios

This repository contains automated tests for two scenarios as part of a skills demonstration:
1. **UI Test**: Validates search functionality on [britinsurance.com](https://www.britinsurance.com/).
2. **API Test**: Tests the PATCH endpoint of [restful-api.dev](https://restful-api.dev/).

The project uses **Java 17**, **TestNG** as the testing framework, **Selenium WebDriver** for UI automation, and **RestAssured** for API testing. Dependencies are managed with **Maven**.

## Prerequisites

- **Java 17**: Ensure JDK 17 is installed (`java -version`).
- **Maven**: Required for dependency management (`mvn -version`).
- **Chrome Browser**: Needed for UI tests (Selenium uses ChromeDriver).
- **Git**: To clone this repository.

## Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/shashikant-git/tech-test.git
   cd tech-test
   git checkout test-brit
2. **Install Dependencies**
   ```bash
   mvn clean install -DskipTests
3. **ChromeDriver Setup**
   Managed automatically by WebDriverManager (included in pom.xml). Ensure Chrome is installed and updated.

## Running the Tests:

  **Run All Tests:**

    mvn clean test
   
  **Run Specific Tests:**
  
    1. UI Test:
      
       mvn test -Dtest=BritInsuranceSearchTest
       
    2. API Test:
       
       mvn test -Dtest=RestfulApiPatchTest
   
   **Logs**: Test execution logs are output to the console via SLF4J/Logback.
