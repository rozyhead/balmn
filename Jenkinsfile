node('node') {
  stage('Checkout') {
    checkout scm
  }

  stage('Compile'){
    sh './gradlew compileJava'
  }

  stage('Test'){
    sh './gradlew test'
  }

  stage('Packaging'){
    sh './gradlew build'
  }

  stage('Archive'){
    archiveArtifacts artifacts: 'build/libs/balmn-*.jar'
  }
}
