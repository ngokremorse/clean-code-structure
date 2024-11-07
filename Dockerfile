#FROM amazoncorretto:17.0.9-alpine3.18
FROM registry.fke.fptcloud.com/31f392ec-105b-44e9-b3b5-abe1dd07e37e/eclipse-temurin:17-jre-jammy
ENV TZ="Asia/Ho_Chi_Minh"
RUN mkdir -p /build
COPY build/app.jar /build/bpm-qtrr-01.jar
ENTRYPOINT java $JAVA_OPTS -jar /build/bpm-qtrr-01.jar
