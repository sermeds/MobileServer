# App_Dockerfile
FROM eclipse-temurin:17-jdk-alpine as builder
COPY . /app
WORKDIR /app
RUN mvn clean package

FROM eclipse-temurin:17-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]