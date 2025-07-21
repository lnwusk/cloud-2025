pipeline {
    agent any
    stages {
        stage('Clone Code') {
            steps {
                echo "1.Git Clone Code"
                git url: "https://github.com/lnwusk/cloud-2025.git"
            }
        }

        stage('Maven Build & Test') {
            steps {
                script {
                    docker.image('maven:3.8.4-openjdk-17').inside("-v /root/.m2:/root/.m2") {
                        echo "2.Maven Build & Test Stage"
                        sh "mvn clean install"
                    }
                }
            }
        }
        stage('Image Build') {
            steps {
                echo "3.Image Build Stage"
                sh 'docker build -f Dockerfile --build-arg jar_name=target/FinalProject-0.0.1-SNAPSHOT.jar -t lnwusk/cloud-2025:${BUILD_ID} . '
            }
        }
        stage('Image Push') {
            steps {
                echo "4.Push Docker Image Stage"
                sh "docker login --username=lnwusk --password=你的token"
                sh "docker push lnwusk/cloud-2025:${BUILD_ID}"
            }
        }
    }
}