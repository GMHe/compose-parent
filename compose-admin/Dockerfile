# Step : Test and package
FROM harbor.ym/devops/maven363:latest as builder
WORKDIR /build
ADD settings.xml /root/.m2/settings.xml
ADD pom.xml pom.xml
#RUN mvn verify clean --fail-never

# add code build
ADD . .
RUN mvn clean install -pl  compose-admin  -am

# # Step : Package image
FROM harbor.ym/devops/openjdk8:latest
#FROM harbor.ym/devops/openjdk8:8-jdk-alpine

#RUN apk update && apk upgrade && apk add --no-cache gcompat
#RUN apk update && apk add --no-cache libc6-compat

WORKDIR /app/lib/
COPY --from=builder /build/yangjian-custom/target/* /app/
RUN mv /app/bin/run.sh /app/lib/

ENTRYPOINT ["sh","/app/lib/run.sh"]
#ENTRYPOINT ["tail","-f","/dev/null"]