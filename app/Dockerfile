FROM openjdk:17-alpine

WORKDIR /home/app
EXPOSE 8080
ENV JAVA_OPTS -Xmx64m -Xms64m
ENTRYPOINT ["java", "-jar", "/home/app/application.jar"]

COPY build/layers/libs /home/app/libs
COPY build/layers/resources /home/app/resources
COPY build/layers/application.jar /home/app/application.jar
