pipeline {
  agent any
  environment {
    MAVEN_OPTS = '-Dhttp.proxyHost=hklxdv47 -Dhttp.proxyPort=20101 -Dhttps.proxyHost=hklxdv47 -Dhttps.proxyPort=20101'
  }
  stages {
    stage('Build') {
      steps{
        checkout scm
        sh 'pwd'
        sh 'chmod +x ./mvnw'
        sh './mvnw clean install -DskipTests'
        sh 'docker build -t tatp-springboot-backend --build-arg http_proxy=http://hklxdv47:20101 --build-arg https_proxy=http://hklxdv47:20101 .'
      }
    }
    stage('Test'){
      steps{
        sh './mvnw test'
      }
    }
    stage('Deploy') {
      steps{
        sh 'docker container ls -a -fname=springboot-dev -q | xargs -r docker container rm --force'
        sh 'docker run -d -p 9300:9300 --network=tatp --name springboot-dev tatp-springboot-backend'
        sh 'docker system prune -f'
      }
    }
  }
}