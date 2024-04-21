package org.unrn.ejercicio3;

import org.unrn.ejercicio3.aplicacion.Modelo;
import org.unrn.ejercicio3.interfaz.RadioCompetition;
import org.unrn.ejercicio3.servicio.EmisorDeRegistros;
import org.unrn.ejercicio3.servicio.LectorDeConcursos;

import javax.swing.*;

public class Main {
    private final String RUTA_CONCURSOS = "./src/main/resources/concursos.txt";
    private final String RUTA_INSCRIPTOS = "./src/main/resources/inscriptos.txt";
    LectorDeConcursos lector;
    EmisorDeRegistros emisor;
    Modelo modelo;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Main().start();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    private void start() {
        lector = new LectorDeConcursos(RUTA_CONCURSOS);
        emisor = new EmisorDeRegistros(RUTA_INSCRIPTOS);
        modelo = new Modelo(lector, emisor);
        new RadioCompetition(modelo);
    }
}
