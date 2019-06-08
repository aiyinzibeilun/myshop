FROM openjdk:8-jre

ENV APP_VERSION 1.0.0-SNAPSHOT
ENV DOCKERIZE_VERSION v0.6.0
#下载插件，用来等待依赖
RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

RUN mkdir /app
WORKDIR /app
COPY eureka-$APP_VERSION.jar /app/eureka.jar
ENTRYPOINT ["dockerize", "-timeout", "5m", "-wait", "http://192.168.47.131:3001/eureka/prod/master", "java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/eureka.jar"]
EXPOSE 8761

