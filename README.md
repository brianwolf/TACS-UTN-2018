# TACS-UTN-2018

Poryecto de criptomoneda realizado para la catedra de Tecnicas Avanzadas de Construccion de Software - Universidad Tecnologica Nacional

## Para empezar

Clonar el proyecto de git o descargar en archivo .zip/.tar.gz y descomprimir

### Prerrequisitos

Tener MAVEN instalado

### Deploy

Para levantar el proyecto solo es necesario ir a la carpeta ../Backend/cryptoCurrency/ y ejecutar el comando:

```
mvn clean install tomcat7:run
```

## Para probar

Si se usa POSTMAN se puede importar el archivo ../TACS-UTN.postman_collection.json el cual contiene la collection de endpoints

### Un ejemplo seria:

```
http://localhost:8080/apiWeb/users/login/userprueba/1234
```

## Construido con:

* [Jersey](https://jersey.github.io/documentation/latest/index.html) - Web Service
* [Maven](https://maven.apache.org/) - Dependency Management
* [Hibernate](http://hibernate.org/orm/documentation/) - Framework ORM
* [Spring Framework](https://docs.spring.io/spring/docs/5.0.5.RELEASE/javadoc-api/) - Framework Dependency Injection

## Autores

Ver lista de [contribuyentes](https://github.com/brianwolf/TACS-UTN-2018/contributors) que participaron y participan en este proyecto.
