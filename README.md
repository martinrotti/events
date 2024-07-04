# events

### Tecnologías:
- Java Spring Boot
- PostgreSQL
- JMS y ActiveMQ

### Comentarios:
A continuación, una serie de mejoras que le haría a la aplicación:

- incorporar una herramienta para el manejo de migraciones (por ejemplo Flyway) para interactuar con la base de datos al momento de agregar o modificar tablas.
- agregar un manejador de errores de manera de centralizarlo en un componente, evitando así la duplicidad de código. Esto se puede llevar a cabo con las annotations @RestControllerAdvice y @ExceptionHandler
- realizar un mapeo entre DTO y Entity de manera de que cuando la aplicación se vuelva más compleja, no implique hacer demasiados cambios. Esto se puede realizar con la librería MapStruct.
- reestructurar el sistema de packages para lograr un orden tal que al crecer el proyecto no se vuelva engorroso. En lugar de tener todos los archivos dentro de un src, se podría tener por ejemplo un src-api para los Controller y DTO, src-business para los Service y un src-model para las Entity y Repositories. Además se podría tener un src-utils para archivos o clases utiles tales como enumerados. Al separar en distintos src, cada uno puede tener su propio pom.xml, con sus propias dependencias específicas, dejando en el pom.xml principal solo las dependencias que se utilizan en varios src.

En la carpeta results se agregan capturas de pantallas mostrando el resultado de crear, actualizar y borrar datos, los cuales generan eventos que se manejan de forma asincrónica y se loguean en la consola. Estos eventos se pueden visualizar la consola que brinda la herramienta ActiveMQ.

Para correr el proyecto se debe:
- tener la herramienta activeMQ y correrla con el comando **activeMQ start**. Los eventos se pueden visualizar entrando a http://127.0.0.1:8161/admin/
- crear la base de datos en PostgreSQL.
- clonar el proyecto, correr el comando **mvn clean install** para instalar las dependencias y ejecutarlo.
