pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'admin-service', 
                    credentialsId: 'githubcrd', 
                    url: 'https://github.com/nageswar103/ecommerce-micro-branchservices.git'
            }
        }

        stage('Build with Maven') {
            steps {
                dir('admin-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('admin-service') {
                    sh 'docker build -t venkathub/admin-service:latest .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockercrd', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
                    sh 'docker push venkathub/admin-service:latest'
                }
            }
        }
    }
}