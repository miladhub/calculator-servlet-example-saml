Calculator Servlet Example using SSO
===

This is just a sample servlet-based Calculator example on SSO via SAML.

Installing Tomcat
===

[Download it](http://mirror.nohup.it/apache/tomcat/tomcat-9/v9.0.34/bin/apache-tomcat-9.0.34.zip)
and extract it to `~/apache-tomcat-9.0.34/`.

Start it:

    ~/apache-tomcat-9.0.34/bin/catalina.sh jpda start

Build & deploy
===

    mvn clean install && cp target/mycalcwebapp.war ~/apache-tomcat-9.0.34/webapps/

Accessing the app
===

Install project <https://github.com/miladhub/shibboleth-sp-example> and copy file `ssl.conf`:

    docker cp shib-sp:/etc/httpd/conf.d/ssl.conf .

Change the app-specific part of `ssl.conf` as follows:

    <Location /mycalcwebapp>
        AuthType shibboleth
        ShibRequestSetting requireSession 1
        require shib-session
        ShibUseHeaders On
        ProxyPass "http://host.docker.internal:8080/mycalcwebapp"
        ProxyPassReverse "http://host.docker.internal:8080/mycalcwebapp"
        ShibRequestSetting exportAssertion true
    </Location>

Copy the file back to the container:

    docker cp ssl.conf shib-sp:/etc/httpd/conf.d/
    docker restart shib-sp
    
Access the app at <https://localhost/mycalcwebapp/>.
