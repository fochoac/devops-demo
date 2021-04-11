FROM openjdk:8-jre-alpine

RUN mkdir /app

WORKDIR /app

ADD ./target/devops-demo-ws-1.0.0-SNAPSHOT.jar /app

EXPOSE 8084


CMD ["java", "-jar", "devops-demo-ws-1.0.0-SNAPSHOT.jar"]