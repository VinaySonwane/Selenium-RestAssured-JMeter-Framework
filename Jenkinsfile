pipeline {
    agent any

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

        stage('2. Test Execution (UI, API, & Performance)') {
            steps {
                echo 'Executing TestRunner (Selenium, RestAssured, and native JMeter CLI)...'
                bat 'mvn clean test'
            }
        }
    }

    post {
        always {
            echo 'Compiling Test Metrics and Generating Allure Dashboard...'
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