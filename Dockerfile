FROM maven:3.6.0-jdk-11-slim AS builder
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=builder /home/app/target/todoilst-0.0.1-SNAPSHOT.jar /usr/local/lib/todolist.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar" , "/usr/local/lib/todolist.jar"]

FROM openjdk:11
RUN apt-get update
RUN apt-get install -y maven
COPY pom.xml /home/app/pom.xml
COPY src /home/app/src
RUN mvn -f /home/app/pom.xml clean package
ENTRYPOINT ["java", "-jar", "home/app/target/todoilst-0.0.1-SNAPSHOT.jar"]
