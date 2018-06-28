# TACS-UTN-2018

Proyecto de criptomoneda realizado para la catedra de Tecnicas Avanzadas de Construccion de Software - Universidad Tecnologica Nacional

## Para empezar

Clonar el proyecto de git o descargar en archivo .zip/.tar.gz y descomprimir

### Prerrequisitos

Tener MAVEN y NPM instalado

### Deploy

Para levantar el proyecto solo es necesario ejecutar runApp.sh o runApp.bat

```
mvn clean install tomcat7:run
```

## Para probar

Si se usa INSOMNIA se puede importar el archivo ../Rest Json/Insomnia-UTN-TACS.json el cual contiene la collection de endpoints

### Un ejemplo seria:

```
curl --request POST \
  --url http://localhost:8080/utn/crypto-currency/users/login \
  --header 'content-type: application/json' \
  --data '{
	"nick": "yisas",
	"pass": "1234"
}'
```

## Construido con:

* [Jersey](https://jersey.github.io/documentation/latest/index.html) - Web Service
* [Maven](https://maven.apache.org/) - Dependency Management
* [Spring Data](http://projects.spring.io/spring-data/#quick-start) - Framework ORM
* [Mongo DB](https://docs.mongodb.com/manual/?_ga=2.169420449.1849471009.1530199703-2038166312.1530199703) - Base de Datos
* [Spring Framework](https://docs.spring.io/spring/docs/5.0.5.RELEASE/javadoc-api/) - Framework Dependency Injection

## Autores

Ver lista de [contribuyentes](https://github.com/brianwolf/TACS-UTN-2018/contributors) que participaron y participan en este proyecto.
