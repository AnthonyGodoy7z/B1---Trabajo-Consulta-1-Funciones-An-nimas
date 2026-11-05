# B1 - Trabajo Consulta 1: Funciones Anónimas
## Parte 1 – Explorar: la programación funcional en el mundo real
Antes de programar, investiga dónde y para qué se usa la programación funcional fuera del aula.
**APACHE Spark**

Lo que resuelve APACHE Spark es que procesa grandes cantidades de datos que estan distribuidos en un cluster (un cluster es un grupo de compus que estan conectadas a una red, trabajando en conjunto como si fueran una computadora mas potente). El lenguaje de programacion que se usa es Scala, tambien se usa una API de colecciones distribuidas como RDD o DATASET, se usan funciones anonimas. La ventaja es que la persona que este programando puede decidir que hacer con cada cosa y Spark hace que las ordenes dadas se repartan entre las maquinas.

**TWITTER BACKEND**

Twitter hizo una migracion a Scala de su backend para que se pueda manejar de mejor manera toda la concurrecia de datos con codigo mas corto y que se menos mutable. La ventaja es el estilo funcional que usan como colecciones inmutables y funciones como valores, reduciendo los errores en los sistemes concurrentes.

## Parte 2 – Entender: ¿qué es una función anónima?
Responde con tus palabras y apoya cada respuesta con un fragmento corto de código:

**¿Qué es una función anónima y de dónde viene el nombre lambda?**

Es una funcion que no tiene nombre 

