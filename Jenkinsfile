pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'user-service', 
                    credentialsId: 'githubcrd', 
                    url: 'https://github.com/nageswar103/ecommerce-micro-branchservices.git'
            }
        }

        stage('Build with Maven') {
            steps {
                dir('user-service') {
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                dir('user-service') {
                    sh 'docker build -t venkathub/user-service:latest .'
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockercrd', usernameVariable: 'DOCKER_USERNAME', passwordVariable: 'DOCKER_PASSWORD')]) {
                    sh 'echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin'
                    sh 'docker push venkathub/user-service:latest'
                }
            }
        }
    }
}