pipeline {
    agent any

    tools {
        jdk 'JDK17'
        maven 'Maven3'
    }

    environment {
        SONAR_TOKEN = credentials('sonar-token')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Lamy97/GestionTODO.git',
                    credentialsId: 'github-credentials-id'
            }
        }

        stage('Build + Tests') {
            steps {
                bat 'mvn -B clean verify'
            }
        }

        stage('Analyse SonarQube') {
            steps {
                withSonarQubeEnv('SonarQube') {
                    bat "mvn -B sonar:sonar -Dsonar.login=%SONAR_TOKEN%"
                }
            }
        }

        stage('Quality Gate') {
            steps {
                timeout(time: 2, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }

        stage('Build Docker') {
            steps {
                bat 'docker build -t gestiontodo:1.0 .'
            }
        }
    }

    post {
        always {
            echo 'Pipeline terminé.'
        }
        failure {
            echo 'Pipeline échoué.'
        }
    }
}