node {
    checkout scm
  try {
    stage('Build docker image') {
        sh 'docker build -t tatp-springboot-backend --build-arg http_proxy=http://hklxdv47:20101 --build-arg https_proxy=http://hklxdv47:20101 .'
    }
    stage('Deploy') {
        sh 'docker container ls -a -fname=springboot-dev -q | xargs -r docker container rm --force'
        sh 'docker run --rm -d -p 9300:9300 --network=tatp --name springboot-dev tatp-springboot-backend'
        sh 'docker system prune'
    }
  }
  catch (err) {
    throw err
  }
}