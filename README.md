# Laboratorio 4

## Supuestos
- Esto corresponde al 2 retiro
- El cálculo de impuestos se hace con la tabla global complementario 2022
- Siempre se solicita el 10%
- Se utilizan ambientes docker para hacer pruebas, y que se puedan conectar a la red de jenkins, por lo tanto todos las direcciones apuntan a los nombres de estos contenedores.


## Backend

Disponible en puerto 9091, corriendo en docker dentro de la red servers.

### Ejecución

```console
$ git clone https://github.com/usach-devops-seccion1-grupo6/TrabajoFinalM4SWD
$ cd TrabajoFinalM4SWD
$ docker run --rm --name TrabajoFinalM4SWD --net servers -it -p 9091:9091 -v $(pwd):/code --workdir /code maven mvn spring-boot:run
```

### Endpoints

* Diez por ciento:
    * URI: /rest/msdxc/dxc?ahorro={numero}
    * Respuesta: integer; ejemplo: 1000000
* Saldo:
    * URI: /rest/msdxc/saldo?ahorro={numero}&sueldo={numero}
    * Respuesta: integer; ejemplo: 500000
* Impuesto:
    * URI: /rest/msdxc/impuesto?sueldo={numero}&ahorro={numero}
    * Respuesta: string; ejemplo: Si
* UF:
    * URI: /rest/msdxc/uf
    * Respuesta: integer; ejemplo: 38292
* Todo:
    * URI: /rest/msdxc/todo?sueldo={numero}&ahorro={numero}
    * Respuesta: json; ejemplo: <code>{
        "dxc": 1000000, 
        "saldo": 200000, 
        "impuesto": "No",
        "sueldo": 1200000,
        "ahorro": 1200000
        }
    </code>

## Frontend

Disponible en puerto 9090, corriendo en docker dentro de la red servers, corresponde a codigo html con javascript.

### Ejecución

```console
$ cd TrabajoFinalM4SWD/frontend
$ docker run --rm -p 9090:80 --name laboratorio-modulo4-frontend --net servers -v ${PWD}:/usr/share/nginx/html:ro -d nginx
```

Para poder ejecutarlo de manera local y no a través de Jenkins, es necesario que la línea 117 teanga lo siguiente:

`
var url_base = 'http://localhost:9091/rest/msdxc/todo?';
`

## Selenium

Pruebas de selenium que corren sobre el frontend, esta configurado por usar el docker de selenium-grid-firefox, se ejecuta de la siguiente manera:
```console
$ docker run --rm --name sfirefox -d -p 4444:4444 --net servers -p 7900:7900 selenium/standalone-firefox:latest
```

Listo lo anterior podemos ejecutar las pruebas a través de docker.

```console
$ git clone https://github.com/usach-devops-seccion1-grupo6/laboratorio-modulo4-selenium
$ cd laboratorio-modulo4-selenium
$ docker run --rm --name selenium-test --net servers -it -v $(pwd):/code --workdir /code maven mvn test
```

## Jenkins
Está configurado en un docker, en la red servers, con newman y maven. El archivo está incluido dentro de este repositorio. Para que se ejecuten las pruebas correctamente, debe estar ejecutándose el **frontend** y **selenium-grid-firefox**

## Jmeter y Postman

Los exportables se encuentran en el directorio *pruebas/*
