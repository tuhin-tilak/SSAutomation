Here's the updated **README.md** with detailed folder-level descriptions:


# **SSAutomation Framework**

## **Project Structure**
```
/SSAutomation                       # Root directory of the project
├── /src                            # Source code directory
│   ├── /main                       # Main application code
│   └── /test                       # Test-related source code
│       ├── /java                   # Java test classes
│       │   ├── /cucumbercraft      # Cucumber-related classes
│       │   ├── /entity             # Entity classes
│       │   ├── /framework          # Framework utilities and configurations
│       │   ├── /models             # Model classes supporting the tests
│       │   ├── /POMPages           # Page Object Model classes
│       │   ├── /runners            # TestNG runner classes
│       │   │   ├── RunCucumberTests.java
│       │   │   ├── RunCucumberTests_Api.java
│       │   │   └── RunCucumberTests_Regression.java
│       │   └── /stepdefinitions    # Step definition classes for Cucumber
│       │       └── YourStepDefinitions.java
│       └── /resources              # Resource files needed for tests
│           ├── /endpoints         # API endpoint inputs
│           │   └── EndPointInputs
│           ├── /test-data          # Test data files
│           │   ├── Automation Data.xlsx
│           │   ├── Buy Now Automation.xlsx
│           │   └── Buy Now Test Data.xlsx
│           └── /config             # Configuration files
│               ├── allure.properties
│               ├── extent.properties
│               ├── extent-config.xml
│               ├── Framework MetaData.xml
│               ├── Global Settings.properties
│               ├── log4j.xml
│               ├── log4j2.properties
│               ├── logback.xml
│               └── spark-config.xml
├── /results                        # Directory for storing test results
│   └── your-test-report.html       # File name for test report output
├── /logs                           # Log files generated during test executions
│   └── your-log-file.log           # Example log file
├── /testng                         # TestNG XML files for test execution
│   ├── TestNGRunAPITests.xml       # TestNG XML for API tests
│   ├── TestNGRunParallelTests.xml  # TestNG XML for parallel tests
│   ├── TestNGRunRegressionTests.xml # TestNG XML for regression tests
│   └── TestNGRunSmokeTests.xml     # TestNG XML for smoke tests
└── README.md                       # Project README file with information about the project


```

## **Folder Descriptions**

### `/SSAutomation`
- **Root directory**: This is the root folder of the project, containing all the code, configuration files, and reports.

### `/src`
- **Source code directory**: Contains all the main code and test-related source files.

#### `/src/main`
- **Main application code**: This directory holds the core business logic of your application, separate from the test code.

#### `/src/test`
- **Test-related source code**: Contains all the test classes, configurations, and resources for automation testing.

##### `/src/test/java`
- **Java test classes**: Includes Java code for implementing the tests, step definitions, page object model classes, and frameworks.

- `/cucumbercraft`: Contains classes related to the Cucumber testing framework.
- `/entity`: Contains entity classes used in tests.
- `/framework`: Includes utilities and framework setup for testing.
- `/models`: Contains supporting model classes for test data and other utilities.
- `/POMPages`: Holds the Page Object Model (POM) classes, which represent web pages or other components in your application.
- `/runners`: Includes TestNG runner classes that are used to execute specific test suites.
    - `RunCucumberTests.java`: Cucumber test runner.
    - `RunCucumberTests_Api.java`: Cucumber runner specifically for API tests.
    - `RunCucumberTests_Regression.java`: Cucumber runner for regression tests.
- `/stepdefinitions`: Contains step definition classes for Cucumber, mapping steps in feature files to Java methods.
    - `YourStepDefinitions.java`: Example step definitions class.

#### `/src/test/resources`
- **Resource files needed for tests**: Contains various configurations, test data files, and other resources used during testing.

- `/endpoints`: Holds API endpoint inputs required for the tests.
    - `EndPointInputs`: Stores the input data for API tests.
- `/test-data`: Contains test data files used for running tests.
    - `Automation Data.xlsx`: Generic test data for the framework.
    - `Buy Now Automation.xlsx`: Test data specific to "Buy Now" tests.
    - `Buy Now Test Data.xlsx`: Additional test scenarios and data for Buy Now tests.
- `/config`: Configuration files that control different settings and logging for the framework.
    - `allure.properties`: Configuration file for Allure reporting.
    - `extent.properties`: Configuration file for Extent reporting.
    - `extent-config.xml`: Extent report template for customizing the report output.
    - `Framework MetaData.xml`: Metadata for the framework.
    - `Global Settings.properties`: Global settings for the framework.
    - `log4j.xml`, `log4j2.properties`, `logback.xml`: Logging configuration files for different logging libraries.
    - `spark-config.xml`: Configuration file for Spark reporting.

### `/results`
- **Directory for storing test results**: This directory is used to store the test output in HTML format, generated by Extent or Allure reports.
    - `your-test-report.html`: Example file name for generated test reports.

### `/logs`
- **Log files generated during test executions**: Contains logs that are generated during the execution of tests, helpful for debugging and analysis.
    - `your-log-file.log`: Example log file.

### `/testng`
- **TestNG XML files for test execution**: TestNG XML files used to configure and run different test suites.
    - `TestNGRunAPITests.xml`: TestNG XML configuration for API tests.
    - `TestNGRunParallelTests.xml`: TestNG XML configuration for running tests in parallel.
    - `TestNGRunRegressionTests.xml`: TestNG XML configuration for regression tests.
    - `TestNGRunSmokeTests.xml`: TestNG XML configuration for smoke tests.

---

## **Navigate to the Project Directory**
```bash
cd SSAutomation
```

## **Install Dependencies**
```bash
mvn clean install
```

---

## **Usage**

### **Run Tests**
Use runners or TestNG XML files for executing specific test suites:
```bash
mvn test -Dcucumber.filter.tags="@Smoke"
```

### **Customize Configurations**
Modify properties in the configuration files:
```bash
resources/config/Global Settings.properties
```

---

## **Test Execution**

### **Run Using TestNG**
Execute a specific suite:
```bash
mvn test -DsuiteXmlFile=testng/TestNGRunSmokeTests.xml
```

### **Run Using IDE**
1. Open runner classes in the `runners` folder.
2. Right-click on a runner and select:
   ```text
   Run as TestNG Test
   ```

### **Run Specific Tags**
Filter tests by Cucumber tags:
```bash
mvn test -Dcucumber.filter.tags="@Regression"
```

---

## **Configuration Files**
Customize the framework using files in the `resources/config` directory:
- `Global Settings.properties`: Global framework settings.
- `log4j2.properties`, `logback.xml`: Logging configurations.
- `extent-config.xml`: Extent report template.
- `allure.properties`: Allure reporting options.

---

## **Reports and Logs**

### **Reports**
- Stored in the `/results` directory.
- Open `your-test-report.html` to view Extent or Allure reports.

### **Logs**
- Logs are in the `/logs` directory.
- Example log file: `your-log-file.log`.

---

## **Test Data**
Test data is stored in the `resources/test-data` directory:
- `Automation Data.xlsx`: Generic test data.
- `Buy Now Automation.xlsx`: Data specific to "Buy Now" tests.
- `Buy Now Test Data.xlsx`: Additional test scenarios.

---

## **Contact**
For issues, queries, or contributions, contact the project maintainer:
- **Author**: [Your Name](https://github.com/yourprofile)
- **Email**: [your.email@example.com](mailto:your.email@example.com)
- **LinkedIn**: [linkedin.com/in/yourprofile](https://linkedin.com/in/yourprofile)
