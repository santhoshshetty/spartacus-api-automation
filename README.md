# API Automation Project

## Overview
This is an API Automation project built using Rest Assured with BDD as the test framework. It is specifically designed for the APIs of Spartacus, an e-commerce storefront by SAP Hybris. Spartacus provides a comprehensive set of REST APIs that can be utilized as a complete application or as individual REST endpoints. This project demonstrates API automation following proper standards, using BDD Cucumber as the test runner. The project includes features for making REST calls, parsing responses, making assertions, and generating reports.

## Features
- **REST Assured Integration**: Simplifies the process of making HTTP requests to Spartacus APIs.
- **BDD Cucumber Framework**: Uses Gherkin syntax for defining test scenarios, making tests readable and maintainable.
- **Response Parsing**: Extracts and processes data from API responses.
- **Assertions**: Validates API responses against expected outcomes.
- **Reporting**: Generates detailed reports of test execution.

## Getting Started
### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse)

### Installation
1. **Clone the repository:**
   ```sh
   git clone <repository_url>
   ```
2. **Navigate to the project directory:**
   ```sh
   cd <project_directory>
   ```
3. **Install dependencies using Maven:**
   ```sh
   mvn clean install
   ```

## Usage
### Running Tests
To execute the tests, use the following Maven command:
```sh
mvn test
```

### Project Structure
- `src/main/java`: Contains the main Java source code.
- `src/test/java`: Contains the test code, including step definitions and hooks.
- `src/test/resources/features`: Contains the feature files written in Gherkin syntax.
- `src/test/resources`: Contains additional resources like configuration files.

### Writing Feature Files
Feature files are written in Gherkin syntax and are located in the `src/test/resources/features` directory. Here's an example of a feature file:

```gherkin
Feature: API Testing with Rest Assured

  Scenario: Get product details
    Given I have the endpoint "/products/{productId}"
    When I make a GET request to the endpoint with productId "12345"
    Then the response status code should be 200
    And the response should contain the product name "Sample Product"
```

### Step Definitions
Step definitions are implemented in Java and map the Gherkin steps to code. These are located in the `src/test/java` directory.

## Reporting
The project is configured to generate test reports.

## Contact
For any questions or suggestions, please contact [Santhosh] at santhoshshetty58@gmail.com

Happy testing!
