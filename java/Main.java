import java.util.*;
import java.util.stream.*;

public class Main {

    record Producto(String nombre, String categoria, double precio, int stock) {}

    public static void main(String[] args) {
        List<Producto> productos = List.of(
                new Producto("Laptop", "Electronica", 850.0, 5),
                new Producto("Mouse", "Electronica", 20.0, 60),
                new Producto("Teclado", "Electronica", 45.0, 30),
                new Producto("Monitor", "Electronica", 220.0, 12),
                new Producto("Silla", "Muebles", 130.0, 8),
                new Producto("Escritorio", "Muebles", 250.0, 4),
                new Producto("Lampara", "Muebles", 35.0, 25),
                new Producto("Cuaderno", "Papeleria", 3.5, 200),
                new Producto("Esfero", "Papeleria", 0.8, 500),
                new Producto("Mochila", "Accesorios", 40.0, 15)
        );

        //Seleccion(filter)
        List<String> mas100 = productos.stream().filter(p -> p.precio() > 100).map(Producto::nombre).toList();
        System.out.println("Selección: " + mas100 + "\n");

        //Transformacion(map)
        List<Double> inventario = productos.stream().map(p -> p.precio() * p.stock()).toList();
        System.out.println("Transformacion: " + inventario + "\n");

        //validacion
        List<Boolean> validacion = List.of(
                productos.stream().anyMatch(p -> p.precio() > 800),
                productos.stream().allMatch(p -> p.stock() > 0)
        );
        System.out.println("Validación: " + validacion + "\n");

        //Conteo
        long pocos = productos.stream().filter(p -> p.stock() < 10).count();
        System.out.println("Conteo: " + pocos + "\n");
    }
}