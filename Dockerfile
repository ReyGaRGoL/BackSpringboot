FROM amazoncorretto:17-alpine-jdk

COPY target/post-management-9.2.0.jar app.jar

ENTRYPOINT [ "java" , "-jar" , "/app.jar" ]