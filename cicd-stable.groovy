node('linux')
{
  stage ('Poll') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: '*/main']],
      doGenerateSubmoduleConfigurations: false,
      extensions: [],
      userRemoteConfigs: [[url: 'https://github.com/zopencommunity/quiltport.git']]])
  }
  stage('Build') {
    build job: 'Port-Pipeline', parameters: [string(name: 'PORT_GITHUB_REPO', value: 'https://github.com/zopencommunity/quiltport.git'), string(name: 'PORT_DESCRIPTION', value: 'Quilt allows you to easily manage large numbers of patches by keeping' ), string(name: 'BUILD_LINE', value: 'STABLE') ]
  }
}
