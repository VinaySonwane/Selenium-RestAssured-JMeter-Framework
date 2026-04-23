pipeline {
    // Allows Jenkins to run this on any available node/server
    agent any

    // Ensure these names match the tools configured in your Jenkins dashboard
  tools {
          maven 'Maven'
          jdk 'JDK'
      }

    stages {
        stage('1. Checkout Repository') {
            steps {
                echo 'Pulling the latest MegaProject code from GitHub...'
                checkout scm
            }
        }

        stage('2. Functional Automation (UI & API)') {
            steps {
                echo 'Executing Cucumber TestRunner (Selenium & RestAssured)...'
                // Using 'bat' for Windows.
                bat 'mvn clean test'
            }
        }

        stage('3. Performance Load Testing (JMeter)') {
            steps {
                echo 'Functional tests passed! Initiating JMeter load test...'
                // Executes your LoadTest.jmx file headlessly
                bat 'mvn jmeter:jmeter'
            }
        }
    }

    // This block executes regardless of whether the tests pass or fail
    post {
        always {
            echo 'Compiling Test Metrics and Generating Allure Dashboard...'
            // Requires the Allure Jenkins Plugin to be installed
            allure([
                includeProperties: false,
                jdk: '',
                properties: [],
                reportBuildPolicy: 'ALWAYS',
                results: [[path: 'target/allure-results']]
            ])
        }
        success {
            echo '✅ PIPELINE SUCCESS: Code is stable, APIs are responsive, and UI is flawless.'
        }
        failure {
            echo '❌ PIPELINE FAILED: Bugs detected. Halting deployment. Check Allure Report.'
        }
    }
}