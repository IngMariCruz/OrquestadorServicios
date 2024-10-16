# Usa la imagen base de OpenJDK
FROM openjdk:11-jre-slim

# Establece el directorio de trabajo
WORKDIR /stepOne

# Copia el archivo JAR de tu aplicación al contenedor
COPY target/stepOne.jar stepOne.jar

# Expone el puerto en el que tu servicio escucha
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "stepOne.jar"]
