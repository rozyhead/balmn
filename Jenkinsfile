node('node') {
  stage('Checkout') {
    checkout scm
  }

  stage('Compile'){
    sh './gradlew --daemon classes'
  }

  stage('Compile tests'){
    sh './gradlew --daemon testClasses'
  }

  stage('Test'){
    sh './gradlew --daemon test'
    junit 'build/test-results/**/*.xml'
  }

  stage('Packaging'){
    sh './gradlew --daemon build'
  }

  stage('Archive'){
    archiveArtifacts artifacts: 'build/libs/balmn-*.jar'
  }
}
