FROM maven:3.9.6-eclipse-temurin-17 as build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app/target/bankease-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
