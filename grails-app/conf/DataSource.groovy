dataSource {
  pooled = true
  driverClassName = "org.postgresql.Driver"
  // dialect = org.hibernate.dialect.PostgreSQLDialect
  dialect = net.sf.hibernate.dialect.PostgreSQLDialect
}
hibernate {
  cache.use_second_level_cache = true
  cache.use_query_cache = false
  cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}

// environment specific settings
environments {

  development {
        dataSource {
          dbCreate = "update"
          // url="jdbc:postgresql://192.168.56.101:5432/hidrodb"
          url="jdbc:postgresql://192.168.56.101:5432/hidrodb"
          username = "postgres"
          password = "vicsecret"
        }
    }

    test {
        dataSource {
          dbCreate = "update"
          url="jdbc:postgresql://localhost:5432/gisdb"
          username = "postgres"
          password = "secreto"

        }
    }

    production {
        dataSource {

          dbCreate = "update"
          url="jdbc:postgresql://10.0.1.50:5432/hidrodb"
          username = "postgres"
          password = "vicsecret"
          pooled = true

          properties {
             maxActive = -1
             minEvictableIdleTimeMillis=1800000
             timeBetweenEvictionRunsMillis=1800000
             numTestsPerEvictionRun=3
             testOnBorrow=true
             testWhileIdle=true
             testOnReturn=true
             validationQuery="SELECT 1"
          }
        }
    }
}
