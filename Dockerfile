FROM openjdk:8
ADD target/hotel-manager-1.0.jar hotel-manager.jar
EXPOSE 8082
ENTRYPOINT ["java", "-Dspring.datasource.url=jdbc:mysql://hotel-manager_mysqldb_1/hotelmanager", "-Dspring.datasource.username=root", "-Dspring.datasource.password=root" , "-jar", "hotel-manager.jar"]
