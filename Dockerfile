FROM openjdk:10

VOLUME /tmp

# Make port 80 available to the world outside this container
EXPOSE 80

ARG JAR_FILE="winwinwiki/winwinwiki:0.1.0"
COPY ${JAR_FILE} winwinwiki-app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/winwinwiki-app.jar"]
