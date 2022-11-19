FROM openjdk:8-jdk-alpine as builder-stage-1
WORKDIR /app/

COPY ./pom.xml .
COPY ./.mvn ./.mvn
COPY ./mvnw .
COPY ./pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
COPY ./src ./src
RUN ./mvnw clean package -DskipTests

FROM openjdk:8-jre-alpine as builder-stage-2

ARG PORT_APP=8090
ENV API_NAME reto-tecnico

WORKDIR /app
RUN mkdir ./logs
RUN mkdir /data

RUN adduser -D usuarioSpring
RUN chown -R usuarioSpring /data
RUN chown -R usuarioSpring ./logs

ENV PORT $PORT_APP
ENV JAR_FOLDER /app/target/$API_NAME*.jar

USER usuarioSpring
COPY --from=builder-stage-1 $JAR_FOLDER ./$API_NAME.jar

EXPOSE $PORT
ENTRYPOINT ["sh", "-c"]
CMD ["java -Duser.timezone=America/Lima -jar ${API_NAME}.jar"]