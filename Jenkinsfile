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
              steps {
                sh "docker build -t nflinnovator/calculator ."
              }
           }
           stage("Docker push") {
              steps {
                withDockerRegistry([ credentialsId: "docker-hub-credentials", url: "" ]) {
                bat "docker push nflinnovator/calculator"
               // sh "docker push nflinnovator/calculator"
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
                 sh "chmod +x acceptance_test.sh && ./acceptance_test.sh"
              }
           }
         }

         post {
           always {
              sh "docker stop calculator"
           }
         } 
}
