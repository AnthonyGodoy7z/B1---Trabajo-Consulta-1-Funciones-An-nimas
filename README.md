# B1 - Trabajo Consulta 1: Funciones Anónimas
## Parte 1 – Explorar: la programación funcional en el mundo real
Antes de programar, investiga dónde y para qué se usa la programación funcional fuera del aula.

**APACHE Spark**

Lo que resuelve APACHE Spark es que procesa grandes cantidades de datos que estan distribuidos en un cluster (un cluster es un grupo de compus que estan conectadas a una red, trabajando en conjunto como si fueran una computadora mas potente). El lenguaje de programacion que se usa es Scala, tambien se usa una API de colecciones distribuidas como RDD o DATASET, se usan funciones anonimas. La ventaja es que la persona que este programando puede decidir que hacer con cada cosa y Spark hace que las ordenes dadas se repartan entre las maquinas.

Fuente: documentación oficial, Spark RDD Programming Guide (https://spark.apache.org/docs/latest/rdd-programming-guide.html).

FRAGMENTO DE CODIGO
```scala
val counts = textFile.flatMap(line => line.split(" "))
                     .map(word => (word, 1))
                     .reduceByKey(_ + _)
```
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

**Python**
```python
print("Selección:", [p.nombre for p in filter(lambda p: p.precio > 100, productos)])
```
Salida: Selección: ['Laptop', 'Monitor', 'Silla', 'Escritorio']

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

**Python**
```python
print("Transformación:", list(map(lambda p: p.precio * p.stock, productos)))
```
Salida: Transformación: [4250.0, 1200.0, 1350.0, 2640.0, 1040.0, 1000.0, 875.0, 700.0, 400.0, 600.0]

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

**Python**
```python
print("Validación: hay caro =", any(map(lambda p: p.precio > 800, productos)),
      ", todos con stock =", all(map(lambda p: p.stock > 0, productos)))
```
Salida: Validación: hay caro = True , todos con stock = True

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

**Python**
```python
print("Conteo:", len(list(filter(lambda p: p.stock < 10, productos))))
```
Salida: Conteo: 3

## Parte 4 – Ampliar: cuatro operaciones nuevas
Las operaciones vistas en clase (map, filter, exists, forall, count, maxBy, minBy, flatMap, foldLeft y scanLeft) son solo una parte de lo que ofrecen las colecciones. Investiga 4 operaciones diferentes a estas que te parezcan útiles para resolver problemas.

### 4.1 Elige tus operaciones
Puedes elegir entre las siguientes o proponer otras que encuentres en la documentación de Scala:

<img width="708" height="210" alt="image" src="https://github.com/user-attachments/assets/2aac9f29-6f4d-43c6-838b-388ad14663b8" />

### 4.2 Ficha de cada operación

### Ficha 1: sortBy
• **Qué hace:** ordena la lista según un criterio, de menor a mayor.

• **Qué función anónima recibe:** recibe un producto y devuelve el valor por el que se ordena (Double, Int, String).

• **Ejemplo:**
```scala
productos.sortBy(_.precio).map(_.nombre)
//salida: List(Esfero, Cuaderno, Mouse, Lampara, Mochila, Teclado, Silla, Monitor, Escritorio, Laptop)
```
• **Problema que resuelve:** mostrar un catálogo de más barato a más caro. Es un tipo nuevo (ordenamiento).

### Ficha 2: groupBy
• **Qué hace:** agrupa los elementos en un mapa donde la clave es el resultado de la lambda.

• **Qué función anónima recibe:** recibe un producto y devuelve la clave del grupo.

• **Ejemplo:**
```scala
val porCategoria = productos.groupBy(p => p.categoria)
porCategoria("Electronica").map(p => p.nombre)  // List(Laptop, Mouse, Teclado, Monitor)
porCategoria("Muebles").map(p => p.nombre)      // List(Silla, Escritorio, Lampara)
porCategoria("Papeleria").map(p => p.nombre)    // List(Cuaderno, Esfero)
porCategoria("Accesorios").map(p => p.nombre)   // List(Mochila)
//salida

```
• **Problema que resuelve:** armar un reporte de productos por categoría. Es un tipo nuevo (agrupación), parecido a la selección aplicada a todos los valores posibles a la vez.

### Ficha 3: partition
• **Qué hace:** divide la lista en dos, los que cumplen una condición y los que no.

• **Qué función anónima recibe:** recibe un producto y devuelve Boolean.

• **Ejemplo:**
```scala
val (pocos, suficientes) = productos.partition(_.stock < 10)
//salida
//val pocos: List[Producto] = List(Producto(Laptop,Electronica,850.0,5), Producto(Silla,Muebles,130.0,8), Producto(Escritorio,Muebles,250.0,4))
//val suficientes: List[Producto] = List(Producto(Mouse,Electronica,20.0,60), Producto(Teclado,Electronica,45.0,30), Producto(Monitor,Electronica,220.0,12), Producto(Lampara,Muebles,35.0,25), Producto(Cuaderno,Papeleria,3.5,200), Producto(Esfero,Papeleria,0.8,500), Producto(Mochila,Accesorios,40.0,15))
```
• **Problema que resuelve:** separar los productos que hay que reabastecer de los que no. Se parece a la selección (filter), pero conserva los dos grupos en una sola pasada.

### Ficha 4: Find
• **Qué hace:** busca el primer elemento de la lista que cumple una condición y se detiene ahi, devuelve un Option: Some(elemento) si lo encuentra y None si no hay ninguno.

• **Qué función anónima recibe:** recibe un producto y devuelve Boolean.

• **Ejemplo:**
```scala
productos.find(p => p.categoria == "Muebles").map(_.nombre)
productos.find(p => p.precio > 1000)
//salida
//val res2: Option[String] = Some(Silla)
//val res3: Option[Producto] = None
```
• **Problema que resuelve:** localizar un solo producto que cumpla una condición como podria ser el primer mueble del inventario o algún producto de más de 1000 dolares, se parece al tipo Selección (filter), pero devuelve solo el primer resultado y no una lista, con el Option también sirve para validar porque None significa que no existe ninguno.

### 4.3 Combinar operaciones
**Pregunta:** ¿Qué categorías tienen más de 50 unidades de stock en total, ordenadas de mayor a menor stock?

**Respuesta**
```scala
productos
  .groupBy(p => p.categoria)                      // 1
  .map(g => (g._1, g._2.map(p => p.stock).sum))   // 2
  .filter(t => t._2 > 50)                         // 3
  .toList
  .sortBy(t => -t._2)                             // 4
//salida: List((Papeleria,700), (Electronica,107))
```
1. groupBy (nueva) forma un grupo de productos por categoría.

2. map (vista en clase) reemplaza cada grupo por la suma de sus stocks.

3. filter (vista en clase) deja solo las categorías con más de 50 unidades.

4. sortBy (nueva) ordena de mayor a menor stock (se usa el signo negativo).

Tras groupBy cada elemento es una pareja, g._1 es la categoría y g._2 es la lista de sus productos despues del paso 2, t._1 es la categoría y t._2 es el stock total, que es lo que se filtra (paso 3) y se ordena (paso 4).

Resultado: Papelería con 700 y Electrónica con 107. Muebles (37) y Accesorios (15) quedan fuera.

## Parte 5. Comparar y concluir
### 5.1 Scala, Java y Python frente a frente

| Criterio | Scala | Java | Python |
|---|---|---|---|
| Sintaxis de la lambda | `p => p.precio > 100` o `_.precio` | `p -> p.precio() > 100` | `lambda p: p.precio > 100` |
| Cómo se declara el tipo | Se infiere, se puede anotar `(p: Producto) =>` | Se infiere del contexto del Stream | No se declara (tipado dinámico) |
| Operaciones con listas | Muchas métodos directos en la lista como filter, map, groupBy, partition, foldLeft... | Se necesita `.stream()` y terminar con `.toList()` o `collect` | Funciones sueltas (map, filter, max, reduce) y comprensiones de listas |
| Restricción del lenguaje | Ninguna importante para este trabajo | Solo una expresión o bloque con llaves ya que las variables capturadas deben ser efectivamente finales | La lambda solo puede contener una expresión (sin sentencias) |
| Legibilidad (opinión) | Alta, es la más corta | Media, más verboso | Alta para casos simples, baja si se anidan varias lambdas |

**¿En qué lenguaje fue más natural resolver los problemas?** En Scala porque las operaciones están directamente en la lista y se encadenan sin pasos intermedios.

**¿En qué tipo de problema encontraste más diferencias entre lenguajes y por qué?** En el conteo ya que en Scala, count recibe la lambda directamente, en Java hay que usar filter y luego count() que devuelve un long, y en Python hay que usar filter, convertirlo a lista y aplicar len. La misma pregunta se resuelve con una operación en Scala, dos en Java y tres en Python.

### Conclusiones
Las funciones anónimas permiten separar qué se hace con la lista (recorrer, filtrar, sumar) de cómo se decide cada caso, con ellas no se escriben ciclos ni variables auxiliares, se pasa la regla como argumento y la operación hace el recorrido haciendo producir código más corto, más fácil de leer y con menos errores y se aplica igual a una lista de 10 elementos que a millones.
El caso de Apache Spark lo muestra, en el conteo de palabras, flatMap, map y reduceByKey reciben lambdas pequeñas, y Spark puede ejecutarlas en paralelo en muchas máquinas porque cada lambda no depende del resto. Es la misma idea que usamos en la Parte 3 con filter y foldLeft, solo que a mayor escala.
