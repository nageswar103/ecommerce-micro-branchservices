pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'payment-service', credentialsId: 'githubcrd', url: 'https://github.com/nageswar103/ecommerce-micro-branchservices.git'
            }
        }
        

        stage('Build with Maven') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Build Docker Image') {
            steps {
                sh 'docker build -t venkathub/payment-service:latest .'
            }
        }
        stage('Push Docker Image') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'dockercrd', toolName: 'docker') {
                        sh "docker push venkathub/payment-service:latest "
                    }
                }
            }
        }    
    }

}