node('node') {
  stage('Checkout') {
    checkout scm
  }

  stage('Compile'){
    sh './gradlew --daemon compileJava'
  }

  stage('Test'){
    sh './gradlew --daemon test'
  }

  stage('Packaging'){
    sh './gradlew --daemon build'
  }

  stage('Archive'){
    archiveArtifacts artifacts: 'build/libs/balmn-*.jar'
  }
}
