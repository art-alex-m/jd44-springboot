FROM openjdk:17-alpine

MAINTAINER artalexm

EXPOSE 8080

WORKDIR app

ADD ./target/l20-authservice-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
