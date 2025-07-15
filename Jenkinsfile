pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'order-service', 
                    credentialsId: 'githubcrd', 
                    url: 'https://github.com/nageswar103/ecommerce-micro-branchservices.git'
            }
        }

        stage('Build with Maven') {
            steps {
                dir('order-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('order-service') {
                    sh 'docker build -t venkathub/order-service:latest .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockercrd', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
                    sh 'docker push venkathub/order-service:latest'
                }
            }
        }
    }
}