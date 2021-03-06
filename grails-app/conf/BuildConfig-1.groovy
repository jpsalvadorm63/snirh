grails.servlet.version = "2.5" // Change depending on target container compliance (2.5 or 3.0)
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6
grails.project.source.level = 1.6
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolver = "maven"

grails.project.dependency.resolution = {



  // inherit Grails' default dependencies
  inherits("global") {
    // specify dependency exclusions here; for example, uncomment this to disable ehcache:
    // excludes 'ehcache'

    excludes 'xercesImpl', 'xml-apis'
  }
  log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
  checksums true // Whether to verify checksums on resolve

  repositories {
    inherits true // Whether to inherit repository definitions from plugins

    grailsPlugins()
    grailsHome()
    grailsCentral()

    mavenLocal()
    mavenCentral()

    // uncomment these (or add new ones) to enable remote dependency resolution from public Maven repositories
    //mavenRepo "http://snapshots.repository.codehaus.org"
    //mavenRepo "http://repository.codehaus.org"
    //mavenRepo "http://download.java.net/maven/2/"
    //mavenRepo "http://repository.jboss.com/maven2/"
  }
  dependencies {
    // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.
    // compile ":excel-export:0.1.6"
    // compile "net.sf.ehcache:ehcache-core:2.4.6"
    // runtime "postgresql:postgresql:9.2-1001.jdbc4"
  }

  plugins {
    runtime ":excel-export:0.1.6"
    // runtime ":hibernate:$grailsVersion"
    runtime ":hibernate:3.6.10.1"
    runtime ":jquery:1.8.0"

    // Uncomment these (or add new ones) to enable additional resources capabilities
    //runtime ":zipped-resources:1.0"
    //runtime ":cached-resources:1.0"
    //runtime ":yui-minify-resources:0.1.4"

    //build ":tomcat:$grailsVersion"
    build ":tomcat:7.0.40.1"

    runtime ":database-migration:1.1"
    compile ":scaffolding:1.0.0"
    compile ':cache:1.0.0'
    compile ":file-uploader:1.2.1"
    compile ":jquery-ui:1.8.24"
    compile ":spring-security-core:1.2.7.3"
  }
}
