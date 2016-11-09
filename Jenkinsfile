node('node') {
  stage('Checkout') {
    checkout scm
  }

  stage('Compile'){
    sh './gradlew compileJava --console'
  }

  stage('Test'){
    sh './gradlew test --console'
  }

  stage('Packaging'){
    sh './gradlew build --console'
  }

  stage('Archive'){
    archiveArtifacts artifacts: 'build/libs/balmn-*.jar'
  }
}
