FROM maven:3.9.9-eclipse-temurin-21-alpine AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests


FROM eclipse-temurin:21-jre-alpine
RUN apk update && apk upgrade --no-cache
WORKDIR /app
COPY --from=builder /app/target/*.jar /app/app.jar

EXPOSE 9090
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]