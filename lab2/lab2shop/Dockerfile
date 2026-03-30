FROM kprzystalski/ebiznes-scala:latest AS build
WORKDIR /app
USER root
COPY . .
RUN sbt stage

FROM eclipse-temurin:11-jre
WORKDIR /app
COPY --from=build /app/target/universal/stage .

EXPOSE 9000

ENTRYPOINT ["bin/lab2shop", "-Dplay.http.secret.key='ad3177cf-defa-445a-9c96-931293e00741'"]