FROM openjdk:8-jre

ENV APP_VERSION 1.0.0-SNAPSHOT
ENV DOCKERIZE_VERSION v0.6.0

RUN mkdir /app
WORKDIR /app
COPY springconfig-$APP_VERSION.jar /app/springconfig.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/springconfig.jar"]
EXPOSE 3001

