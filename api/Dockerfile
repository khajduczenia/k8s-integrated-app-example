FROM gradle:jdk8-alpine as builder

COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle bootJar

FROM openjdk:8-jre-alpine

COPY --from=builder /src/build/libs/api-0.0.1-SNAPSHOT.jar api.jar
CMD java -jar api.jar