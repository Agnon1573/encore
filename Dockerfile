FROM openjdk:8-jre-alpine
COPY ./target/encore-1.0.0-SNAPSHOT.jar /usr/src/encore/
WORKDIR /usr/src/encore
CMD ["java", "-jar", "encore-1.0.0-SNAPSHOT.jar"]