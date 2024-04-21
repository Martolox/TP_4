package org.unrn.ejercicio1.persistencia;

import org.unrn.ejercicio1.modelo.Registro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistroPersona implements Registro {
    private final Connection dbConn;

    public RegistroPersona(Conexion conexion) {
        dbConn = conexion.open();
    }

    @Override
    public void cargarPersona(String nombre, String telefono, String region) {

        try (PreparedStatement st = dbConn
                .prepareStatement("insert into participantes(nombre, telefono, region) " +
                        "values(?,?,?)")) {
            st.setString(1, nombre);
            st.setString(2, telefono);
            st.setString(3, region);
            st.executeUpdate();

        } catch (SQLException e1) {
            throw new RuntimeException(e1);
        }
    }
}
