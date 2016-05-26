// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.use.accept.header = false
grails.mime.types = [
    all:           '*/*',
    atom:          'application/atom+xml',
    css:           'text/css',
    csv:           'text/csv',
    form:          'application/x-www-form-urlencoded',
    html:          ['text/html','application/xhtml+xml'],
    js:            'text/javascript',
    json:          ['application/json', 'text/json'],
    multipartForm: 'multipart/form-data',
    rss:           'application/rss+xml',
    text:          'text/plain',
    xml:           ['text/xml', 'application/xml'],
    pdf:           'application/pdf',
    rtf:           'application/rtf',
    excel:         'application/vnd.ms-excel',
    ods:           'application/vnd.oasis.opendocument.spreadsheet'
]

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// What URL patterns should be processed by the resources plugin
grails.resources.adhoc.patterns = ['/images/*', '/css/*', '/js/*', '/plugins/*']

// The default codec used to encode data with ${}
grails.views.default.codec = "none" // none, html, base64
grails.views.gsp.encoding = "UTF-8"
grails.converters.encoding = "UTF-8"
// enable Sitemesh preprocessing of GSP pages
grails.views.gsp.sitemesh.preprocess = true
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

environments {
    development {
        grails.logging.jul.usebridge = true
    }
    production {
        grails.logging.jul.usebridge = false
        // TODO: grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    // Example of changing the log pattern for the default console appender:
    //
    //appenders {
    //    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
    //}

    error  'org.codehaus.groovy.grails.web.servlet',        // controllers
           'org.codehaus.groovy.grails.web.pages',          // GSP
           'org.codehaus.groovy.grails.web.sitemesh',       // layouts
           'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
           'org.codehaus.groovy.grails.web.mapping',        // URL mapping
           'org.codehaus.groovy.grails.commons',            // core / classloading
           'org.codehaus.groovy.grails.plugins',            // plugins
           'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
           'org.springframework',
           'org.hibernate',
           'net.sf.ehcache.hibernate'
}

fileuploader {
  estaciones {
    maxSize = 1024 * 1024 * 4 //4 MB
    allowedExtensions = ["xls","xlsx","csv"]
    path = "/senagua/estaciones/"
  }
  datos {
    maxSize = 1024 * 1024 * 4 //4 MB
    allowedExtensions = ["xls","xlsx","csv"]
    path = "/senagua/datos/"
  }
}

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'accesus.Usuario'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'accesus.UsuarioRol'
grails.plugins.springsecurity.authority.className = 'accesus.Rol'
// grails.plugins.springsecurity.auth.forceHttps = true
grails.plugins.springsecurity.password.algorithm='SHA-512'
grails.plugins.springsecurity.securityConfigType = grails.plugins.springsecurity.SecurityConfigType.InterceptUrlMap
grails.plugins.springsecurity.interceptUrlMap = [
    '/usuario/**' : ['ROLE_ADMIN'],
    '/*/edit/**' : ['ROLE_ADMIN'],
    '/*/create/**' : ['ROLE_ADMIN'],
    '/*/delete/**' : ['ROLE_ADMIN'],
    '/*/uploadFile/**' : ['ROLE_DATALOADER'],
    '/*/deleteData/**' : ['ROLE_ADMIN','ROLE_DATALOADER'],
    '/login/**' : ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/logout/**' : ['IS_AUTHENTICATED_ANONYMOUSLY'],
    '/*' : ['IS_AUTHENTICATED_FULLY']
]

// email server configuration
// para producción, en desarrollo comentar el bloque
grails {
  mail {
    host = "mail.senagua.gob.ec"
    port = 25
    username = "hidromet"
    password = "20agua15"
  }
}

// para desarrollo, en producción comentar el bloque
/*grails {
  mail {
    host = "localhost"
    port = 25
    username = "jpsalvador@hotmail.com"
    password = "nada"
  }
}*/

// Uncomment and edit the following lines to start using Grails encoding & escaping improvements

/* remove this line 
// GSP settings
grails {
    views {
        gsp {
            encoding = 'UTF-8'
            htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
            codecs {
                expression = 'html' // escapes values inside null
                scriptlet = 'none' // escapes output from scriptlets in GSPs
                taglib = 'none' // escapes output from taglibs
                staticparts = 'none' // escapes output from static template parts
            }
        }
        // escapes all not-encoded output at final stage of outputting
        filteringCodecForContentType {
            //'text/html' = 'html'
        }
    }
}
remove this line */
