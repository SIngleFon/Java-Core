FROM openjdk:latest
WORKDIR /usr/src/app
COPY out/artifacts/Java_Core_jar/Java-Core.jar .
CMD ["java", "-jar", "Java-Core.jar"]