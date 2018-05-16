FROM java:8
VOLUME /tmp
EXPOSE 8765
ADD lesstraffic-gateway-server.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]