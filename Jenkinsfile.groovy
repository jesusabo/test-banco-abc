pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo "Compilando aplicación..."
            }
        }
        stage('Deploy') {
            steps {
                echo "Desplegando en Azure..."
            }
        }
    }
}
