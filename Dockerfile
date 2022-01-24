FROM openjdk:11 as build

ADD . /tmp/kingdomino
WORKDIR /tmp/kingdomino
RUN ./gradlew build

FROM openjdk:11-jre-slim-stretch
LABEL maintainer="marc.partensky@gmail.com"
LABEL source="https://github.com/marcpartensky/kingdomino"
RUN apt-get update
RUN apt-get install -y libx11-dev libgl-dev libgtk-3-dev

WORKDIR /usr/src/kingdomino
COPY --from=build /tmp/kingdomino/build build
COPY .gradle .gradle
COPY .settings .settings
COPY .project .project
COPY assets assets
COPY gradle gradle
COPY src src
COPY build.gradle gradlew ./

ENTRYPOINT ["./gradlew", "run", "--no-rebuild"]
