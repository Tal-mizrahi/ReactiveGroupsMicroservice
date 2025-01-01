FROM openjdk:21-jdk-slim
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} ReactiveGroupsMicroservice.jar
EXPOSE 9081
ENTRYPOINT ["java","-jar","/ReactiveGroupsMicroservice.jar","-web -webAllowOthers -tcp -tcpAllowOthers -browser"]