FROM java:8
VOLUME /tmp
EXPOSE 9411

ADD zipkin-server-2.8.3.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar" ]