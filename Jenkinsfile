pipeline {
     agent any
     triggers {
        pollSCM('* * * * *')
     }
        stages {
           stage("Compile") {
              steps {
                sh "./gradlew compileJava"
              }
           } 
 
           stage("Unit test") {
              steps {
                sh "./gradlew test"
              }  
           }

           stage("Code coverage") {
              steps {
                sh "./gradlew jacocoTestReport"
                sh "./gradlew jacocoTestCoverageVerification"
              }
           }

           stage("Static code analysis") {
              steps {
                sh "./gradlew checkstyleMain"
              }
           }

           stage("Package") {
              steps {
                sh "./gradlew build"
              }
           }

          stage("Docker build") {
               steps{
                 sh "docker build -t nflinnovator/calculator ."
                }
           }
               
           stage("Docker push") {
                  steps {
                     script {
                         docker.withRegistry('https://index.docker.io/v1/', 'docker-hub-credentials') {
                         sh 'docker push nflinnovator/calculator'
                         }
                     }
                  }
           }

           stage("Deploy to staging") {
              steps {
                sh "docker run -d --rm -p 8765:8080 --name calculator nflinnovator/calculator"
              }
           }

           stage("Acceptance test") {
              steps {
                 sleep 60
                 sh "./gradlew acceptanceTest -Dcalculator.url=http://localhost:8765"
              }
           }
         }

         post {
           always {
              sh "docker stop calculator"
           }
         } 
}
