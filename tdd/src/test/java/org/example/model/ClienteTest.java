package org.example.model;

import junit.framework.TestCase;
import org.example.excepciones.ClienteInvalidoException;

public class ClienteTest extends TestCase {
    public void testCreaClienteValido() {
        Cliente cliente = Cliente.crear("Ana Perez", "ana@mail.com", "12345678");
        assertEquals("Ana Perez", cliente.getNombre());
        assertEquals("ana@mail.com", cliente.getEmail());
        assertEquals("12345678", cliente.getDocumento());
    }

    public void testRechazaNombreVacio() {
        assertInvalid("", "ana@mail.com", "12345678");
    }

    public void testRechazaEmailInvalido() {
        assertInvalid("Ana", "ana-mail.com", "12345678");
    }

    public void testRechazaDocumentoQueNoTieneEntreSieteYOchoDigitos() {
        assertInvalid("Ana", "ana@mail.com", "1234A678");
    }

    private void assertInvalid(String nombre, String email, String documento) {
        try {
            Cliente.crear(nombre, email, documento);
            fail("Se esperaba ClienteInvalidoException");
        } catch (ClienteInvalidoException expected) {
            assertNotNull(expected.getMessage());
        }
    }
}
