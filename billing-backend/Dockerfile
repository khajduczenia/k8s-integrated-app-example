FROM gradle:jdk8-slim as builder

COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle bootJar

FROM openjdk:8-jre-slim

COPY --from=builder /src/build/libs/billing-backend-0.0.1-SNAPSHOT.jar billing-backend.jar
CMD java -jar billing-backend.jar