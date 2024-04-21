package org.unrn.ejercicio3.aplicacion;

import org.unrn.ejercicio2.aplicacion.Lector;
import org.unrn.ejercicio3.servicio.EmisorDeRegistros;
import org.unrn.ejercicio3.servicio.LectorDeConcursos;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private static final int COL_IDCONCURSO = 0;
    private static final int COL_NOMBRE = 1;
    private static final int COL_FECHAINICIO = 2;
    private static final int COL_FECHAFIN = 3;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private final Lector lector;
    private Emisor emisor;
    private List<String[]> lista;

    public Modelo(LectorDeConcursos lector, EmisorDeRegistros emisor) {
        this.lector = lector;
        this.emisor = emisor;
    }

    public String[] cargarConcursos() {
        intentarLeerArchivo();
        removerPrimerLinea();
        return buscarInscripcionesAbiertas();
    }

    private void intentarLeerArchivo() {
        try {
            lista = lector.leerArchivo();
        } catch (IOException e) {
            throw new RuntimeException("No se encontro archivo .txt", e);
        }
    }

    private void removerPrimerLinea() {
        lista.removeFirst();
    }

    public String[] buscarInscripcionesAbiertas() {
        var output = new ArrayList<String>();
        for (String[] linea : lista) {
            var fechaInicio = LocalDate.parse(linea[COL_FECHAINICIO], dtf);
            var fechaFin = LocalDate.parse(linea[COL_FECHAFIN], dtf);
            if (isEntreFechas(fechaInicio, fechaFin)) {
                output.add(linea[COL_NOMBRE]);
            }
        }
        return output.toArray(String[]::new);
    }

    private boolean isEntreFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        return LocalDate.now().isAfter(fechaInicio.minusDays(1))
                && LocalDate.now().isBefore(fechaFin);
    }

    public void guardarDatos(String nombre, String apellido, String email, String telefono, String concurso) {
        intentarLeerArchivo();
        String texto = "apellido,nombre,teléfono,email,idconcurso\n";
        texto += String.format("%s,%s,%s,%s,%s", apellido, nombre, telefono, email, buscarIdConcurso(concurso));
        emisor.escribirArchivo(texto);
    }

    private String buscarIdConcurso(String concurso) {
        String id = "";
        for (String[] linea : lista) {
            if (linea[COL_NOMBRE].equals(concurso))
                id = linea[COL_IDCONCURSO];
        }
        return id;
    }
}
