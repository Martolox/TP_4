package org.unrn.ejercicio2.aplicacion;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Modelo {
    private static final int COL_APELLIDO = 0;
    private static final int COL_NOMBRE = 1;
    private static final int COL_FECHA = 2;
    private final Lector lector;
    private final Emisor emisor;
    private final DateTimeFormatter dtf = DateTimeFormatter.ofPattern(" yyyy/MM/dd");
    List<String[]> lista;

    public Modelo(Lector lector, Emisor emisor) {
        this.lector = lector;
        this.emisor = emisor;
    }

    public void cargarListaEmpleados() {
        leerArchivo();
        removerPrimerLinea();
    }

    private void leerArchivo() {
        try {
            lista = lector.leerArchivo();
        } catch (IOException e) {
            throw new RuntimeException("El archivo.csv debe existir", e);
        }
    }

    private void removerPrimerLinea() {
        lista.removeFirst();
    }

    public List<String[]> buscarPorFecha() {
        var output = new ArrayList<String[]>();
        for (String[] linea : lista) {
            var fecha = LocalDate.parse(linea[COL_FECHA], dtf);
            if (isCumpleanios(fecha)) {
                output.add(linea);
            }
        }
        return output;
    }

    private boolean isCumpleanios(LocalDate fecha) {
        return fecha.getDayOfMonth() == LocalDate.now().getDayOfMonth() &&
                fecha.getMonth() == LocalDate.now().getMonth();
    }

    public void eviarEmails(List<String[]> lista) {
        for (String[] linea : lista) {
            String mensaje = String.format("Feliz cumpleaneos %s %s!",
                    linea[COL_APELLIDO], linea[COL_NOMBRE]);
            emisor.enviarMail(mensaje);
        }
    }

    public void debug_AgregarUltimoEmpleado() {
        var fecha = LocalDate.now().format(dtf);
        String[] empleado = {"Perez", "Juan", fecha, "asdf@mail.com"};
        lista.add(empleado);
    }
}
