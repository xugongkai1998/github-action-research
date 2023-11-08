FROM openjdk:17-alpine

RUN rm -f /etc/localtime \
&& ln -sv /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezone

ADD target/github-action-research-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar","/app/app.jar"]