# PokeDex
Desarrollado por [Brian Guzmán M](https://github.com/bguzmanm).

## Requerimiento

Para el back-end se pide construir una API REST usando Java Spring Boot que exponga la información al Pokedex. 
Esta API debe consumir el servicio [PokeApi](https://pokeapi.co/) para obtener la información.

Desde el lado de frontend solo necesitaremos las diferentes llamadas por curl o postman (esto es parte del entregable) a las APIs expuestas y poder obtener por cada uno de los pokemones (foto y su información básica):
- Tipo (type)
- Peso (weight)
- Listado de Habilidades (ability)

También, al pinchar sobre uno se debe mostrar su ficha descriptiva junto a su foto e información detallada:
- Información Básica (la misma del listado)
- Descripción
- Evoluciones

- Si deseas (OPCIONAL) puedes hacer un front-end que haga las llamadas mencionadas arriba, y puedes elegir el lenguaje de tu preferencia.

La aplicación debe ser desplegada en AWS, Azure, GCP o Heroku, a elección. Es importante que documentes cómo se realiza el despliegue.

Cualquier funcionalidad extra es bienvenida. Idealmente se espera que la aplicación cuente con tests y que las respuestas de Poke Api sean almacenadas en una capa de caché para mejorar el rendimiento de las respuestas.

La solución al desafío debe ser capaz de: 
- Integrar una API externa.
- Exponer un API REST.
- Consumir API desde front-end (opcional)
- Desplegar en la nube.
- Manejar errores.
- Mantener un código ordenado y de calidad. 
 
## Herramientas
- Java 11
- Maven
- Spring Boot 2.7
- Lombok 1.18.24
- JQuery
- Bootstrap 4.0

Mas detalles en pom.xml

## Instrucciones de Despliegue

`./mvnw -DskipTests spring-boot:run`

[Preview](https://pokedex-351803.ue.r.appspot.com)

