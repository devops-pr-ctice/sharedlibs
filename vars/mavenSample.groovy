def call(Map config){
    pipeline{
    agent{
        label 'devnode'
    }
    tools{
        maven 'MAVEN_3.9.9'
    }
    stages{
        stage('SCM'){
            steps{
                git url: 'https://github.com/devops-pr-ctice/spring-petclinic.git',
                    branch: 'main'
            }
        }
        stage('build'){
            steps{
                sh 'mvn package'
            }
        }
    }
    post{
        success{
            junit '**/surefire-reports/*.xml'
            archiveArtifacts artifacts: '**/target/spring-petclinic-*.jar'
        }
    }
}
}