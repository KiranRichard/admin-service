#INSTALLATION OF THE OPERATING SYSTEM
FROM eclipse-temurin:17-jdk
COPY target/admin-service-dev-1.jar admin-service.jar
EXPOSE 8099
ENTRYPOINT ["java","-Dspring.profiles.active=prod","-jar","admin-service.jar"]