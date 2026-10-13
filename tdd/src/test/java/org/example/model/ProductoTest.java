package org.example.model;

import java.math.BigDecimal;
import junit.framework.TestCase;
import org.example.excepciones.ProductoInvalidoException;

public class ProductoTest extends TestCase {
    public void testCreaProductoValido() {
        Producto producto = Producto.crear("Teclado", "TEC-001", new BigDecimal("12500.50"), 8);
        assertEquals("Teclado", producto.getNombre());
        assertEquals("TEC-001", producto.getCodigo());
        assertEquals(new BigDecimal("12500.50"), producto.getPrecio());
        assertEquals(8, producto.getStock());
    }

    public void testRechazaNombreVacio() {
        assertInvalid(" ", "TEC-001", new BigDecimal("10.00"), 1);
    }

    public void testRechazaCodigoFueraDeFormato() {
        assertInvalid("Teclado", "teclado 1", new BigDecimal("10.00"), 1);
    }

    public void testRechazaPrecioNoPositivo() {
        assertInvalid("Teclado", "TEC-001", BigDecimal.ZERO, 1);
    }

    public void testRechazaStockNegativo() {
        assertInvalid("Teclado", "TEC-001", new BigDecimal("10.00"), -1);
    }

    private void assertInvalid(String nombre, String codigo, BigDecimal precio, int stock) {
        try {
            Producto.crear(nombre, codigo, precio, stock);
            fail("Se esperaba ProductoInvalidoException");
        } catch (ProductoInvalidoException expected) {
            assertNotNull(expected.getMessage());
        }
    }
}
