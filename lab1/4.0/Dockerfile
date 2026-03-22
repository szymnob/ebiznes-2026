# Dockerfile for Java, Kotlin, Gradle and JDBC

FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    curl \
    unzip \
    zip \
    && apt-get clean

RUN curl -s "https://get.sdkman.io" | bash

RUN bash -c "source /root/.sdkman/bin/sdkman-init.sh && \
    sdk install java 8.0.482-tem && \
    sdk install kotlin && \
    sdk install gradle"

ENV JAVA_HOME="/root/.sdkman/candidates/java/current"
ENV KOTLIN_HOME="/root/.sdkman/candidates/kotlin/current"
ENV GRADLE_HOME="/root/.sdkman/candidates/gradle/current"
ENV PATH="${JAVA_HOME}/bin:${KOTLIN_HOME}/bin:${GRADLE_HOME}/bin:${PATH}"

CMD ["sh", "-c", "gradle -v && kotlin -version"]