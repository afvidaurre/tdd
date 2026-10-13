package org.example.model;

import org.example.excepciones.ClienteInvalidoException;

public final class Cliente {
    private final String nombre;
    private final String email;
    private final String documento;

    private Cliente(String nombre, String email, String documento) {
        this.nombre = nombre;
        this.email = email;
        this.documento = documento;
    }

    public static Cliente crear(String nombre, String email, String documento) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new ClienteInvalidoException("El nombre del cliente es obligatorio.");
        }
        if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new ClienteInvalidoException("El email del cliente no tiene un formato válido.");
        }
        if (documento == null || !documento.matches("\\d{7,8}")) {
            throw new ClienteInvalidoException("El documento debe contener entre 7 y 8 dígitos.");
        }
        return new Cliente(nombre.trim(), email.trim(), documento);
    }

    public String getNombre() { return nombre; }
    public String getEmail() { return email; }
    public String getDocumento() { return documento; }
}
