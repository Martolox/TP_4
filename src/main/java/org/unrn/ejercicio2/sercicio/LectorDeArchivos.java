package org.unrn.ejercicio2.sercicio;

import com.opencsv.CSVReader;
import org.unrn.ejercicio2.aplicacion.Lector;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LectorDeArchivos implements Lector {
    private final String ruta;

    public LectorDeArchivos(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public List<String[]> leerArchivo() throws IOException {
        var lista = new ArrayList<String[]>();
        var lector = new CSVReader(new FileReader(ruta));
        String[] fila;
        while ((fila = lector.readNext()) != null) {
            lista.add(fila);
        }
        lector.close();
        return lista;
    }
}
