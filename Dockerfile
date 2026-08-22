

FROM eclipse-temurin:21-jre-alpine AS builder
WORKDIR /application

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar

RUN java -Djarmode=tools extract --layers --launcher application.jar

FROM eclipse-temurin:21-jre-alpine
WORKDIR /application

*RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=builder /application/dependencies/ ./
COPY --from=builder /application/spring-boot-loader/ ./
COPY --from=builder /application/snapshot-dependencies/ ./
COPY --from=builder /application/application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]



# FROM maven:3.9.9-eclipse-temurin-21 AS build

# WORKDIR /app

# COPY pom.xml .

# COPY src ./src

# RUN mvn clean package -DskipTests


# FROM eclipse-temurin:21-jre

# WORKDIR /app

# COPY --from=build /app/target/*.jar app.jar

# EXPOSE 9003

# ENTRYPOINT ["java", "-jar", "app.jar"]
