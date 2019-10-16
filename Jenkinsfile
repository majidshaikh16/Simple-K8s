node{
    def mvn = tool (name: 'maven3', type: 'maven') + '/bin/mvn'

    stage('checkout to SCM'){
        checkout scm
    }

    stage('compile and test rabbit-mq event api'){
        sh "cd msgq/ && ${mvn} clean install -DskipTests"
    }

    stage('compile and test rabbit-mq worker api'){
        sh "cd SimpleWeb/ && ${mvn} clean install -DskipTests"
    }
    
    stage('Push Rabbit-mq worker api image'){    
        def dockerfile = 'Dockerfile'
            // sh 'docker build -t majidshaikh16/simple-web -f ./msgq/${dockerfile} ./msgq/'
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-creds') {
            def customImage = docker.build("majidshaikh16/rabbit-mq-worker-apis","-f ./SimpleWeb/${dockerfile} ./SimpleWeb/")
            customImage.push()
            // customImage.push('staging')        
        }

    }

    stage('Push Rabbit-mq event api image'){    
        def dockerfile = 'Dockerfile'
            // sh 'docker build -t majidshaikh16/simple-web -f ./msgq/${dockerfile} ./msgq/'
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-creds') {
            def customImage = docker.build("majidshaikh16/rabbit-mq-event-apis","-f ./msgq/${dockerfile} ./msgq/")
            customImage.push()
            // customImage.push('staging')        
        }

    }

     stage('deploy and remove old build from Kubernetes'){
         sh 'cd msgq/ && kubectl apply -f k8s'
     }

    // stage('stop old and up'){
    //     sh 'cd msgq/ && docker-compose stop && docker-compose rm -f && docker-compose up -d'
    // }
    
}