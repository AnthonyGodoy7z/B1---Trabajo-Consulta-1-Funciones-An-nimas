case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)
val productos = List(
  Producto("Laptop", "Electronica", 850.0, 5),
  Producto("Mouse", "Electronica", 20.0, 60),
  Producto("Teclado", "Electronica", 45.0, 30),
  Producto("Monitor", "Electronica", 220.0, 12),
  Producto("Silla", "Muebles", 130.0, 8),
  Producto("Escritorio", "Muebles", 250.0, 4),
  Producto("Lampara", "Muebles", 35.0, 25),
  Producto("Cuaderno", "Papeleria", 3.5, 200),
  Producto("Esfero", "Papeleria", 0.8, 500),
  Producto("Mochila", "Accesorios", 40.0, 15)
)

//sortby
productos.sortBy(_.precio).map(_.nombre)

//groupby
val porCategoria = productos.groupBy(p => p.categoria)
porCategoria("Electronica").map(p => p.nombre)  // List(Laptop, Mouse, Teclado, Monitor)
porCategoria("Muebles").map(p => p.nombre)      // List(Silla, Escritorio, Lampara)
porCategoria("Papeleria").map(p => p.nombre)    // List(Cuaderno, Esfero)
porCategoria("Accesorios").map(p => p.nombre)   // List(Mochila)

//partition
val (pocos, suficientes) = productos.partition(_.stock < 10)

//find
productos.find(p => p.categoria == "Muebles").map(_.nombre)
productos.find(p => p.precio > 1000)

//nuevas operaciones
productos
  .groupBy(p => p.categoria)                      // 1
  .map(g => (g._1, g._2.map(p => p.stock).sum))   // 2
  .filter(t => t._2 > 50)                         // 3
  .toList
  .sortBy(t => -t._2)                             // 4
// List((Papeleria,700), (Electronica,107))