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

Es una funcion que no tiene nombre y se la escribe en el mismo lugar donde se la va a usar, el nombre viene del calculo lambda donde las funciones se escribian con la letra griega λ

```scala
val f = (x: Double) => x * 2
```

**¿En qué se diferencia de una función nombrada? Escribe la misma lógica de ambas formas.**

La diferencia es que la nombrada tiene un identificador y se puede reutilizar en cualquier parte del codigo mientras que la anonima es donde se escribe se usa, tienen la misma logica.

```scala
//funcion nombrada
def par(n: Int): Boolean = n % 2 == 0
List(1, 2, 3, 4).filter(par)
//funcion anonima
List(1, 2, 3, 4).filter(_ % 2 == 0)
```
**¿Qué significa que las funciones sean ciudadanos de primera clase? ¿Por qué eso permite pasar una lambda a map o filter?**

Eso significa que la funcion es tratada como cualquier otro valor pudiendo ser guardada dentro de una variable, pasar como argumento y ser devuelta como resultado, por eso se permite que map y filtren puedan pasar una lambda ya que son funciones de orden superior que esperan otra funcion como un parametro.

```scala
val f = (x: Int) => x * 2
List(1, 2, 3, 4).map(f)
```
**¿Qué es un closure? Muestra una lambda que use una variable definida fuera de ella**

Es una funcion anonima o una lambda que recuerda y permite acceder a variables definidas que estan fuera de ella y las puede usar aunque estas ya no esten activas

```scala
val multi = 3
val f = (x: Int) => x * multi //se usa la variable multi que esta fuera de este lambda
f(5)
```
## Parte 3 – Aplicar: ¿en qué problemas sirven las funciones anónimas?
Esta es la parte central del trabajo. Vas a identificar los tipos de problemas que se resuelven con funciones anónimas y a resolver cada uno con lambdas en Scala, Java y Python. La comparación entre los lenguajes se hace en la Parte 5.

**3.1 Catálogo de tipos de problemas**

Cada operación vista en clase resuelve un tipo de problema. Documenta al menos 4de los 7 tipos de la siguiente tabla:

<img width="608" height="289" alt="image" src="https://github.com/user-attachments/assets/2ceec814-3b93-4655-99d3-56aeb954c013" />

Para cada tipo de problema documenta:

**1. Situación real:** un ejemplo concreto donde aparece ese problema (por ejemplo, “un banco necesita detectar las transacciones mayores a $1 000”).

**2. Solución con funciones anónimas** en los tres lenguajes:

&emsp;**Scala**, con las operaciones vistas en clase.
  
&emsp;**Java**, con Streams.
  
&emsp;**Python**, con lambda y las funciones equivalentes (por ejemplo, map, filter, max con key o functools.reduce).

**3. Salida** de cada versión, para comprobar que las tres dan el mismo resultado.

### Seleccion(filter)

Situación real: una tienda quiere listar los productos que cuestan más de 100 dolares para una promoción.

**Scala**
```scala
productos.filter(_.precio < 100).map(_.nombre)
```
Salida: List(Mouse, Teclado, Lampara, Cuaderno, Esfero, Mochila)

**Java**
```Java
List<String> mas100 = productos.stream().filter(p -> p.precio() > 100).map(Producto::nombre).toList();
System.out.println("Selección: " + mas100 + "\n");
```
Salida: Selección: [Laptop, Monitor, Silla, Escritorio]

### Transformación(map)

Situación real: el área de contabilidad necesita el valor de inventario de cada producto (precio por stock).

**Scala**
```scala
productos.map(p => p.precio * p.stock)
```
Salida: List(4250.0, 1200.0, 1350.0, 2640.0, 1040.0, 1000.0, 875.0, 700.0, 400.0, 600.0)

**Java**
```java
List<Double> inventario = productos.stream().map(p -> p.precio() * p.stock()).toList();
System.out.println("Transformacion: " + inventario + "\n");
```
Salida: Transformacion: [4250.0, 1200.0, 1350.0, 2640.0, 1040.0, 1000.0, 875.0, 700.0, 400.0, 600.0]
 
### Validación(exists, forall)

Situación real: antes de una auditoría, se quiere saber si algún producto cuesta más de $800 y si todos tienen stock.

**Scala**
```scala
productos.exists(_.precio > 800)
productos.forall(_.stock > 0)
```
Salida: true y true

**Java**
```java
List<Boolean> validacion = List.of(
                productos.stream().anyMatch(p -> p.precio() > 800),
                productos.stream().allMatch(p -> p.stock() > 0)
        );
System.out.println("Validación: " + validacion + "\n");
```
Salida: Validación: [true, true]

### Conteo(count)

Situación real: el bodeguero quiere saber cuántos productos tienen menos de 10 unidades para reabastecer.

**Scala**
```scala
productos.count(_.stock < 10)
```
Salida: Int = 3

**Java**
```java
long pocos = productos.stream().filter(p -> p.stock() < 10).count();
System.out.println("Conteo: " + pocos + "\n");
```
Salida: Conteo: 3
