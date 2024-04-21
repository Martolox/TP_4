package org.unrn.ejercicio2.aplicacion;

import java.io.IOException;
import java.util.List;

public interface Lector {
    List<String[]> leerArchivo() throws IOException;
}
