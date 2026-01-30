pipeline {
    agent any

    // Use Jenkins configured tools (Manage Jenkins -> Tools)
    tools {
        jdk 'JDK17'
        maven 'M3'
    }

    options {
        timestamps()
    }

    stages {
        stage('Checkout') {
            steps {
                // Works when job is "Pipeline script from SCM"
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // -B = batch mode (clean console output)
                bat 'mvn -B clean test'
            }
        }

        stage('Delivery (Artifacts)') {
            steps {
                // "Delivery" for freshers = keep outputs as downloadable build artifacts
                archiveArtifacts artifacts: 'target/**, test-output/**, **/surefire-reports/**', fingerprint: true
            }
        }
    }

    post {
        always {
            // Publish TestNG XML results (expects testng-results.xml from XMLReporter)
            testNG reportFilenamePattern: '**/testng-results.xml'

            // Publish Cucumber report from JSON
            cucumber fileIncludePattern: '**/target/cucumber/*.json'
        }
    }
}
