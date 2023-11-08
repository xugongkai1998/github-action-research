# FROM openjdk:17-alpine 会在GithubAction构建失败: failed to solve: openjdk:17-alpine: no match for platform in manifest: not found
FROM openjdk:17-jdk-slim

RUN rm -f /etc/localtime \
&& ln -sv /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo "Asia/Shanghai" > /etc/timezone

ADD target/github-action-research-0.0.1-SNAPSHOT.jar /app/app.jar

ENTRYPOINT ["java", "-jar","/app/app.jar"]