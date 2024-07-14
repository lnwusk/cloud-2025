pipeline {
    agent any
    stages {
        stage('Clone Code') {
            steps {
                echo "1.Git Clone Code"
                sh 'curl "http://p2.nju.edu.cn/portal_io/login?username=usm&password=pwd"' //todo
                git url: "https://github.com/wcx0206/your-project.git"
            }
        }

        stage('Maven Build') {
            steps {
                script {
                    docker.image('maven:3.8.4-openjdk-17').inside("-v /root/.m2:/root/.m2") {
                        echo "2.Maven Build Stage"
                        sh "mvn clean install -Dmaven.test.skip=true"
                    }
                }
            }
        }
        stage('Image Build') {
            steps {
                echo "3.Image Build Stage"
                sh 'docker build -f Dockerfile --build-arg jar_name=target/your-project-0.0.1-SNAPSHOT.jar -t your-project:${BUILD_ID} . '
                sh 'docker tag  your-project:${BUILD_ID}  harbor.edu.cn/library/your-project:${BUILD_ID}'
            }
        }
        stage('Image Push') {
            steps {
                echo "4.Push Docker Image Stage"
                sh "docker login --username=admin harbor.edu.cn -p Harbor12345"
                sh "docker push harbor.edu.cn/library/your-project:${BUILD_ID}"
            }
        }

        stage('Clone Git Repository') {
            steps {
                node('slave') {
                    container('jnlp-kubectl') {
                        echo "5. Git Clone YAML To Slave"
                        git url: "https://github.com/wcx0206/your-k8s-configs.git"

                        echo "6. Change YAML File Stage"
                        sh 'sed -i "s#{VERSION}#${BUILD_ID}#g" ./jenkins/scripts/your-project.yaml'

                        echo "7. Deploy To K8s Stage"
                        sh 'kubectl apply -f ./jenkins/scripts/your-project.yaml'

                        echo "8. Deploy ServiceMonitor To K8s"
                        sh 'kubectl apply -f ./jenkins/scripts/your-project-serviceMonitor.yaml'
                    }
                }
            }
        }
    }
}