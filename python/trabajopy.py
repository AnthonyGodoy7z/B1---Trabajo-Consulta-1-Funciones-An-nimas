from collections import namedtuple
from functools import reduce

Producto = namedtuple("Producto", "nombre categoria precio stock")
productos = [
    Producto("Laptop", "Electronica", 850.0, 5),
    Producto("Mouse", "Electronica", 20.0, 60),
    Producto("Teclado", "Electronica", 45.0, 30),
    Producto("Monitor", "Electronica", 220.0, 12),
    Producto("Silla", "Muebles", 130.0, 8),
    Producto("Escritorio", "Muebles", 250.0, 4),
    Producto("Lampara", "Muebles", 35.0, 25),
    Producto("Cuaderno", "Papeleria", 3.5, 200),
    Producto("Esfero", "Papeleria", 0.8, 500),
    Producto("Mochila", "Accesorios", 40.0, 15),
]

# Selección
print("Selección:", [p.nombre for p in filter(lambda p: p.precio > 100, productos)])

# Transformación
print("Transformación:", list(map(lambda p: p.precio * p.stock, productos)))

# Validación
print("Validación: hay caro =", any(map(lambda p: p.precio > 800, productos)),
      ", todos con stock =", all(map(lambda p: p.stock > 0, productos)))

# Conteo
print("Conteo:", len(list(filter(lambda p: p.stock < 10, productos))))