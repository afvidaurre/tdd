package org.example.model;

import java.math.BigDecimal;
import junit.framework.TestCase;
import org.example.excepciones.PedidoInvalidoException;

public class PedidoTest extends TestCase {
    public void testCreaPedidoValidoYCalculaTotal() {
        Cliente cliente = Cliente.crear("Ana", "ana@example.com", "12345678");
        Producto producto = Producto.crear("Teclado", "TEC-001", new BigDecimal("12500.50"), 8);
        Pedido pedido = Pedido.crear(cliente, producto, 2);
        assertSame(cliente, pedido.getCliente());
        assertSame(producto, pedido.getProducto());
        assertEquals(2, pedido.getCantidad());
        assertEquals(new BigDecimal("25001.00"), pedido.getTotal());
    }

    public void testRechazaClienteNulo() {
        Producto producto = Producto.crear("Teclado", "TEC-001", new BigDecimal("10.00"), 8);
        assertInvalid(null, producto, 1);
    }

    public void testRechazaProductoNulo() {
        Cliente cliente = Cliente.crear("Ana", "ana@example.com", "12345678");
        assertInvalid(cliente, null, 1);
    }

    public void testRechazaCantidadNoPositiva() {
        Cliente cliente = Cliente.crear("Ana", "ana@example.com", "12345678");
        Producto producto = Producto.crear("Teclado", "TEC-001", new BigDecimal("10.00"), 8);
        assertInvalid(cliente, producto, 0);
    }

    public void testRechazaCantidadMayorAlStock() {
        Cliente cliente = Cliente.crear("Ana", "ana@example.com", "12345678");
        Producto producto = Producto.crear("Teclado", "TEC-001", new BigDecimal("10.00"), 2);
        assertInvalid(cliente, producto, 3);
    }

    private void assertInvalid(Cliente cliente, Producto producto, int cantidad) {
        try {
            Pedido.crear(cliente, producto, cantidad);
            fail("Se esperaba PedidoInvalidoException");
        } catch (PedidoInvalidoException expected) {
            assertNotNull(expected.getMessage());
        }
    }
}
