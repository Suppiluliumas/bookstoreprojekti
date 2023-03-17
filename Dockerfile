FROM maven:3.8.6-eclipse-temurin-17-focal AS build
COPY src /bookstore/app/src
COPY pom.xml /bookstore/app
RUN mvn -f /bookstore/app/pom.xml clean package

# Package stage
FROM eclipse-temurin:17-jre-focal
COPY --from=build /bookstore/app/target/bookstore-0.0.1-SNAPSHOT.jar /usr/local/lib/bookstore.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/bookstore.jar"]