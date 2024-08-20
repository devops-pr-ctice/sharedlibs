def call(Map config){
    pipeline{
    agent{
        label "$config.label"
    }
    tools{
        maven "$config.maven"
    }
    stages{
        stage('SCM'){
            steps{
                git url: "$config.url",
                    branch: "$config.branch"
            }
        }
        stage('build'){
            steps{
                sh "mvn $config.goal"
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