FROM openjdk:9

VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

ARG JAR_FILE="winwinwiki/winwinwiki:0.1.0"
COPY ${JAR_FILE} winwinwiki-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/winwinwiki-app.jar"]
