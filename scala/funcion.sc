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

//Seleccion(filter)
productos.filter(_.precio < 100).map(_.nombre)

//Transformacion(map)
productos.map(p => p.precio * p.stock)

//Validacion(exists, forall)
productos.exists(_.precio > 800)
productos.forall(_.stock > 0)

//Conteo(count)
productos.count(_.stock < 10)

