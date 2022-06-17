FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

# 배포용 properties 실행 명령어
ENTRYPOINT ["java","-jar","app.jar"] 

# 로컬용 properties 실행 명령어
# ENTRYPOINT ["java","-jar","/app.jar"]