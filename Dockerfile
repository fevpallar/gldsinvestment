FROM openjdk:8
EXPOSE 8080
ADD target/goldinvestment.jar goldinvestment.jar
ENTRYPOINT ["java","-jar","/goldinvestment.jar"]