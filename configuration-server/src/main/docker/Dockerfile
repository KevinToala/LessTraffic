FROM java:8
VOLUME /tmp
EXPOSE 8888

RUN apt-get update -q \
  && DEBIAN_FRONTEND=noninteractive apt-get install -qy netcat \
  && apt-get clean \
  && rm -rf /var/lib/apt

ADD wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

ADD lesstraffic-configuration-server.jar app.jar
RUN sh -c 'touch /app.jar'

ENTRYPOINT [ "/wait-for-it.sh", "-t", "0", "registration-server:8761", "--", "java", "-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]