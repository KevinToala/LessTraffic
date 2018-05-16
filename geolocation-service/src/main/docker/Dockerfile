FROM java:8
VOLUME /tmp
EXPOSE 9091
ADD lesstraffic-geolocation-service.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]