FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM eclipse-temurin:17-jre-alpine
EXPOSE 8080
WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar bot-app.jar

ENTRYPOINT ["java", "-jar", "bot-app.jar"]