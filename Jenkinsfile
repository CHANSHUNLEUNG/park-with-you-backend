node {
    checkout scm
  try {
    stage('Build docker image') {
        sh 'docker build -t tatp-springboot-backend --build-arg http_proxy=http://hklxdv47:20101 --build-arg https_proxy=http://hklxdv47:20101 .'
    }
    stage('Remove docker container if exists'){
        sh 'docker container ls -a -fname=springboot-dev -q | xargs -r docker container rm --force'
    }
    stage('Deploy') {
        sh 'docker run --rm -d -p 9300:9300 --network=tatp --name springboot-dev tatp-springboot-backend'
    }
  }
  catch (err) {
    throw err
  }
}