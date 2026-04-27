# 🚀 Master Automation Framework

An end-to-end, enterprise-grade Quality Engineering pipeline demonstrating comprehensive testing across the entire application stack. This framework integrates UI, API, and Performance testing into a unified Continuous Integration (CI/CD) workflow.

## 📌 Framework Overview

This project was engineered to simulate a production-ready testing environment. It is built using **Java** and implements the **Page Object Model (POM)** design pattern alongside **Behavior-Driven Development (BDD)** to ensure the codebase remains maintainable, scalable, and business-readable.

### 🌟 Core Capabilities
* **UI Automation:** E-commerce workflow automation (SauceDemo) managing dynamic elements, complex cart interactions, and strict price assertions. Handles browser-level security popups and race conditions via Explicit Waits.
* **API Testing:** Automated backend validation ensuring correct endpoint responses, status codes, and JSON data parsing.
* **Performance Testing:** Integrated headless load testing simulating concurrent users to monitor server stress metrics.
* **Data-Driven Execution:** Dynamic test credential injection utilizing Apache POI to parse Excel spreadsheets.
* **Robust Infrastructure:** Thread-safe execution using `ThreadLocal` WebDriver initialization, primed for Parallel Execution on Selenium Grid.

---

## 🛠️ Tech Stack & Tools

* **Programming Language:** Java 21
* **UI Automation:** Selenium WebDriver (v4.x)
* **BDD Framework:** Cucumber
* **Test Runner & Assertions:** TestNG
* **API Automation:** RestAssured
* **Performance Testing:** Apache JMeter (CLI Non-GUI Mode)
* **Data Management:** Apache POI (Excel Data-Driven Testing)
* **Build Management:** Maven
* **CI/CD:** Jenkins
* **Reporting:** Allure Reports

---

## 🏗️ Architectural Design (Page Object Model)

The framework is strictly separated into logical layers to prevent code duplication and reduce maintenance overhead:

```text
src/test/
├── java/
│   ├── base/          # BaseTest.java (ThreadLocal WebDriver setup & ChromeOptions)
│   ├── pages/         # Page Objects (ProductsPage, CheckoutPage, etc.)
│   ├── steps/         # Cucumber Step Definitions (EcomSteps, LoginSteps)
│   └── utils/         # Helper classes (Excel Readers, Configuration logic)
└── resources/
    ├── features/      # BDD Gherkin files (.feature)
    ├── jmeter/        # LoadTest.jmx execution files
    └── testdata/      # Excel spreadsheets for Data-Driven Testing