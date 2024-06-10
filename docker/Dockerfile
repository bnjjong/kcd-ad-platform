FROM  openjdk:21-jdk-slim
LABEL maintainer="Hnery <dogfootmaster@gmail.com>"
LABEL type="application"
LABEL jdk="21"
LABEL gradle="8.7"
LABEL title="kcd-ad-platform"
LABEL version="1.0"

WORKDIR /apps


# copy app jar.
COPY app/ad/build/libs/*.jar /apps/app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/apps/app.jar"]