FROM java:8
VOLUME /tmp
EXPOSE 9099

ADD wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

ADD lesstraffic-geolocation-service.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "/wait-for-it.sh", "-t", "0", "configuration-server:8888", "--", "java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=prod", "-jar", "/app.jar"]