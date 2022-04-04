FROM eclipse-temurin:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8001
ENTRYPOINT ["java","-jar","/app.jar"]
