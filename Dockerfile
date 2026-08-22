# FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
# WORKDIR /app
# COPY pom.xml .

# COPY src ./src
# RUN mvn clean package -DskipTests

# FROM eclipse-temurin:21-jre-alpine
# RUN apk update && apk upgrade --no-cache 
# WORKDIR /app
# COPY --from=builder /app/target/*.jar /app/app.jar
# EXPOSE 9005

# ENTRYPOINT ["java", "-jar", "/app/app.jar"]


FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder

WORKDIR /app

COPY pom.xml .

RUN mvn -B dependency:go-offline


COPY src ./src

RUN mvn -B package -DskipTests

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/target/*.jar /app/app.jar

USER 1001

EXPOSE 9005

ENTRYPOINT ["java", "-jar", "/app/app.jar"]