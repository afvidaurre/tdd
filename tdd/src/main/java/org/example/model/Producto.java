package org.example.model;

import java.math.BigDecimal;
import org.example.excepciones.ProductoInvalidoException;

public final class Producto {
    private final String nombre;
    private final String codigo;
    private final BigDecimal precio;
    private final int stock;

    private Producto(String nombre, String codigo, BigDecimal precio, int stock) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.stock = stock;
    }

    public static Producto crear(String nombre, String codigo, BigDecimal precio, int stock) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ProductoInvalidoException("El nombre del producto es obligatorio.");
        }
        if (codigo == null || !codigo.matches("[A-Z]{3}-\\d{3}")) {
            throw new ProductoInvalidoException("El código debe tener el formato AAA-999.");
        }
        if (precio == null || precio.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ProductoInvalidoException("El precio debe ser mayor que cero.");
        }
        if (stock < 0) {
            throw new ProductoInvalidoException("El stock no puede ser negativo.");
        }
        return new Producto(nombre.trim(), codigo, precio, stock);
    }

    public String getNombre() { return nombre; }
    public String getCodigo() { return codigo; }
    public BigDecimal getPrecio() { return precio; }
    public int getStock() { return stock; }
}
