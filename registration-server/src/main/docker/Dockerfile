FROM java:8
VOLUME /tmp
EXPOSE 8761

ADD lesstraffic-registration-server.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]