# Enterprise CI/CD Test Automation Framework

An end-to-end, full-stack testing architecture built to enterprise standards. This framework integrates UI, API, and Performance testing into a unified, automated CI/CD pipeline.

## 🚀 Core Architecture & Tech Stack
* **Language:** Java 21
* **UI Automation:** Selenium WebDriver & Page Object Model (POM)
* **API Validation:** RestAssured
* **Performance/Load Testing:** Apache JMeter (CLI/Headless Mode)
* **BDD Framework:** Cucumber & Gherkin
* **Execution Engine:** TestNG (Configured for Parallel Execution)
* **Data-Driven Testing (DDT):** Apache POI (Dynamic Excel Injection)
* **Reporting:** Allure Dashboards
* **CI/CD Orchestration:** Jenkins (Declarative Pipeline)

## ⚙️ Pipeline Workflow (Jenkinsfile)
This project is configured with a fully automated `Jenkinsfile` utilizing Maven Profiles to isolate testing stages:
1. **Source Checkout:** Pulls latest code from version control.
2. **Functional Validation:** Executes parallel Selenium and RestAssured tests (`mvn clean test`).
3. **Performance Gate:** If functional tests pass, triggers headless JMeter load tests (`mvn verify -Pperformance-test`).
4. **Reporting:** Aggregates metrics and generates interactive Allure dashboards.

## 💻 Local Execution Instructions
**Run Functional Tests (UI & API)**
```bash
mvn clean test
