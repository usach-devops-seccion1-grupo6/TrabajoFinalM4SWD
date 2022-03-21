import groovy.json.JsonSlurperClassic

def jsonParse(def json) {
    new groovy.json.JsonSlurperClassic().parseText(json)
}

pipeline {
    agent any

    environment {
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
        stage("Execute"){
            steps {
                script {
                    sh "timeout 60 \$(which mvn) spring-boot:run&"
                    sleep 10
                }
            }
        }
    }


}
