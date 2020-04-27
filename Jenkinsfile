pipeline {
  agent any
  environment {
    MAVEN_OPTS = '-Dhttp.proxyHost=hklxdv47 -Dhttp.proxyPort=20101 -Dhttps.proxyHost=hklxdv47 -Dhttps.proxyPort=20101'
    DOCKER_IMAGE = 'tatp-springboot-backend'
    DOCKER_NAME = 'springboot'
  }
  stages {
    stage('Build') {
      steps{
        checkout scm
        sh 'pwd'
        sh 'chmod +x ./mvnw'
        sh './mvnw clean install -DskipTests'
        sh 'docker build -t ${DOCKER_IMAGE} --build-arg http_proxy=http://hklxdv47:20101 --build-arg https_proxy=http://hklxdv47:20101 .'
      }
    }
    stage('Test'){
      steps{
        sh './mvnw test'
      }
    }
    stage('Deploy') {
      steps{
        sh 'docker container ls -a -fname=${DOCKER_NAME}-dev -q | xargs -r docker container rm --force'
        sh 'docker run -d -p 9300:9300 --name ${DOCKER_NAME}-dev ${DOCKER_IMAGE}'
        sh 'docker system prune -f'
      }
    }
    stage('Deploy to production'){
      steps{
        input "Deploy to prod?"
        sh 'docker container ls -a -fname=${DOCKER_NAME}-production -q | xargs -r docker container rm --force'
        sh 'docker run -d -p 9400:9300 --name ${DOCKER_NAME}-production -e SPRING_PROFILES_ACTIVE=production ${DOCKER_IMAGE}'
        sh 'docker system prune -f'
      }
    }
  }
}