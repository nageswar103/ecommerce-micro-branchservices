
pipeline {
    agent any

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'cart-service', credentialsId: 'githubcrd', url: 'https://github.com/nageswar103/ecommerce-micro-branchservices.git'
            }
        }
        

        stage('Build with Maven') {
            steps {
                dir('cart-service') {
                   sh 'mvn clean package -DskipTests'
                }
           }
        }

        stage('Build Docker Image') {
            steps {
                dir('cart-service') {
                    sh 'docker build -t venkathub/cart-service:latest .'
                }
            }    

        }
        stage('Push Docker Image') {
            steps {
                script {
                    withDockerRegistry(credentialsId: 'dockercrd', toolName: 'docker') {
                        sh "docker push venkathub/cart-service:latest "
                    }
                }
            }
        }    
    }

}
