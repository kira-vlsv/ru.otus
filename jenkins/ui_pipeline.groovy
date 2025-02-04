timeout(time: 60, unit: 'MINUTES') {
    node('maven') {

        def config = readYaml text: params.YAML_CONFIG
        config.each { k, v ->
            env."${k}" = v
        }

        stage('Checkout') {
            checkout scm
        }

        stage('Run UI tests') {
            status = sh (
                "gradle test -DBROWSER=$env.BROWSER -DBASE_URL=$env.BASE_URL",
                returnStatus: true
            )

            if(status > 0) {
                currentBuild.status = 'UNSTABLE'
            }
        }

        stage('Publish allure report') {
            allure(
                disabled: true,
                includeProperties: false,
                jdk: '',
                report: './target/allure-results',
                reportBuildPolicy: 'ALWAYS'
            )
        }
    }
}