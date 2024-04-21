package org.unrn.ejercicio1.main;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupBD {
    private final String USER;
    private final String PWD;
    private final String URL;

    public SetupBD(String URL, String USER, String PWD) {
        this.URL = URL;
        this.USER = USER;
        this.PWD = PWD;
    }

    public void inicializar() {
        try (var conn = DriverManager.getConnection(URL, USER, PWD)) {
            var stmt = conn.createStatement();
            dropTablaParticipantes(stmt);
            crearTablaParticipantes(stmt);
            insertarDatosIniciales(stmt);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void dropTablaParticipantes(Statement stmt) {
        try {
            stmt.executeUpdate("DROP TABLE participantes");
        } catch (Exception e) {
//            throw new RuntimeException(e);
        }
    }

    private void crearTablaParticipantes(Statement stmt) throws SQLException {
        stmt.executeUpdate("CREATE TABLE participantes (id_participantes INT NOT NULL " +
                "primary key generated always as identity (start with 1, increment by 1), " +
                "nombre char(10), telefono double, region char(10))");
    }

    private void insertarDatosIniciales(Statement stmt) throws SQLException {
        stmt.executeUpdate("insert into participantes(nombre, telefono, region) "
                + "values('Manuel', 5555111111, 'China')");
        stmt.executeUpdate("insert into participantes(nombre, telefono, region) "
                + "values('Juan', 5555222222, 'US')");
        stmt.executeUpdate("insert into participantes(nombre, telefono, region) "
                + "values('David', 5555333333, 'Europa')");
    }
}