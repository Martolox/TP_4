package org.unrn.ejercicio1.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    private final String USER;
    private final String PWD;
    private final String URL;

    public Conexion(String URL, String USER, String PWD) {
        this.URL = URL;
        this.USER = USER;
        this.PWD = PWD;
    }

    public Connection open() {
        try {
            return DriverManager.getConnection(URL, USER, PWD);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
