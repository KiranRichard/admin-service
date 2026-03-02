#INSTALLATION OF THE OPERATING SYSTEM
FROM eclipse-temurin:17-jdk
COPY target/admin-service-0.0.1-SNAPSHOT.jar admin-service.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","admin-service.jar"]