package org.example.model;

import java.math.BigDecimal;
import org.example.excepciones.PedidoInvalidoException;

public final class Pedido {
    private final Cliente cliente;
    private final Producto producto;
    private final int cantidad;

    private Pedido(Cliente cliente, Producto producto, int cantidad) {
        this.cliente = cliente;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public static Pedido crear(Cliente cliente, Producto producto, int cantidad) {
        if (cliente == null) {
            throw new PedidoInvalidoException("El cliente del pedido es obligatorio.");
        }
        if (producto == null) {
            throw new PedidoInvalidoException("El producto del pedido es obligatorio.");
        }
        if (cantidad <= 0) {
            throw new PedidoInvalidoException("La cantidad debe ser mayor que cero.");
        }
        if (cantidad > producto.getStock()) {
            throw new PedidoInvalidoException("La cantidad supera el stock disponible.");
        }
        return new Pedido(cliente, producto, cantidad);
    }

    public Cliente getCliente() { return cliente; }
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public BigDecimal getTotal() { return producto.getPrecio().multiply(BigDecimal.valueOf(cantidad)); }
}
