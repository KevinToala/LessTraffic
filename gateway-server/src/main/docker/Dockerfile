FROM java:8
VOLUME /tmp
EXPOSE 8765

ADD wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

ADD lesstraffic-gateway-server.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "/wait-for-it.sh", "-t", "0", "configuration-server:8888", "--", "java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]