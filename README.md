# easy-web-app
Web-application: resteasy+swagger-ui 

**Startup:**

    cmd -> mvn jetty:run-war
    browser -> http://localhost:8080/swagger/
    
**Debug mode**
    
    set MAVEN_OPTS=-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,address=4000,server=y,suspend=n
    cmd -> mvn jetty:run-war
    browser -> http://localhost:8080/swagger/
    
   
