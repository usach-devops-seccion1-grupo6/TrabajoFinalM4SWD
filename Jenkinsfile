import groovy.json.JsonSlurperClassic

def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

pipeline {
    agent any

    environment {
        CREDENCIAL_ID = "CheckerJenkinsGrupo6"
    }

    stages {
        stage("Compilar"){
            steps {
                script {
                    sh 'mvn -Dmaven.test.skip=true clean compile -e'
                }
            }
        }

        stage("unitTest"){
            steps {
                script {
                    sh 'mvn test -e'
                }
            }
        }

        stage("jar"){
            steps {
                script {
                    sh 'mvn -Dmaven.test.skip=true package -e'
                }
            }
        }

        stage("run"){
            steps {
                script {
                    sh "timeout 60 \$(which mvn) spring-boot:run&"
                    sleep 10
                }
            }
        }

        stage("selenium"){
            steps {
                script {
                    checkout([
                        $class: 'GitSCM', 
                        branches: [[name: '*/main']], 
                        extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'selenium']], 
                        userRemoteConfigs: [
                            [
                                credentialsId: "${env.CREDENCIAL_ID}", 
                                url: 'https://github.com/usach-devops-seccion1-grupo6/laboratorio-modulo4-selenium'
                            ]
                        ]
                    ])

                    dir("selenium"){
                        sh 'mvn test'
                    }
                }
            }
        }

        stage("newman"){
            steps {
                script {
                    sh "newman run pruebas/dxc_collection.json"
                }
            }
        }
    }


}
