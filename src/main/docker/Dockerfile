FROM frolvlad/alpine-oraclejdk8:slim
EXPOSE 8080
VOLUME /tmp
ADD encore-1.0.0-SNAPSHOT.jar app.jar
RUN sh -c 'touch /app.jar'
ENTRYPOINT ["java","-XX:NativeMemoryTracking=summary","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=with-security","-jar","/app.jar"]