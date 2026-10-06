FROM openjdk:8u111-jdk-alpine
LABEL maintainer="chung.ho@nvsoft.vn"

#Set app home folder
ENV APP_HOME /app

#Create base app folder
RUN mkdir $APP_HOME

#Create folder to save configuration files
RUN mkdir $APP_HOME/config

#Create folder with application logs
RUN mkdir $APP_HOME/logs

#Create folder with application upload files (/app/uploads)
RUN mkdir $APP_HOME/upload

VOLUME $APP_HOME/logs
VOLUME $APP_HOME/config
VOLUME $APP_HOME/uploads

VOLUME /tmp
ADD /target/*.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
