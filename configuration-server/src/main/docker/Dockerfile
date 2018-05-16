FROM java:8
VOLUME /tmp
EXPOSE 8888
ADD lesstraffic-configuration-server.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]