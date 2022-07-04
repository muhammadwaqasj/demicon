FROM maven:3.8.6-openjdk-18 AS maven_build
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp/
RUN mvn package
FROM openjdk:18-slim as build
LABEL maintainer="Muhammad Waqas"
COPY --from=maven_build /tmp/target/*.jar app.jar
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf /app.jar)
FROM openjdk:18-slim
VOLUME /tmp
ARG DEPENDENCY=/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","de.demicon.connector.ConnectorApplication"]