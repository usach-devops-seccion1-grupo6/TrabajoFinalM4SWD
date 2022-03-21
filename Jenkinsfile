import groovy.json.JsonSlurperClassic

def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

pipeline {
    agent any

    environment {
        WORKSPACE = "/repos/TrabajoFinalM4SWD/"
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

        stage("newman"){
            steps {
                script {
                    sh "newman pruebas/dxc_collection.json"
                }
            }
        }

        stage("selenium"){
            steps {
                script {
                    checkout([  
                        $class: 'GitSCM', 
                        branches: [[name: 'refs/heads/main']], 
                        doGenerateSubmoduleConfigurations: false, 
                        extensions: [[$class: 'RelativeTargetDirectory', relativeTargetDir: 'selenium']], 
                        submoduleCfg: [],
                        userRemoteConfigs: [[url: 'https://github.com/usach-devops-seccion1-grupo6/laboratorio-modulo4-selenium.git']]
                    ])

                    dir("selenium"){
                        sh 'pwd'
                        sh 'mvn test'
                    }
                }
            }
        }
    }


}
