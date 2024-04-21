package org.unrn.ejercicio1.main;

import org.unrn.ejercicio1.modelo.Modelo;
import org.unrn.ejercicio1.persistencia.Conexion;
import org.unrn.ejercicio1.persistencia.RegistroPersona;
import org.unrn.ejercicio1.ui.UI;

public class Main {
    //Conexión para DB cliente / servidor
    //private static final String URL = "jdbc:derby://localhost:1527/participantes";
    //Conexión para DB en memoria
    public static final String URL = "jdbc:derby:memory:participantes;create=true";
    private static final String USER = "app";
    private static final String PWD = "app";

    public static void main(String[] args) {
        setupBD();
        lanzarAplicacion();
    }

    private static void setupBD() {
        var jdbc = new SetupBD(URL, USER, PWD);
        jdbc.inicializar();
    }

    private static void lanzarAplicacion() {
        javax.swing.SwingUtilities.invokeLater(() -> {
            try {
                var conexion = new Conexion(URL, USER, PWD);
                var modelo = new Modelo(new RegistroPersona(conexion));
                new UI(modelo);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}