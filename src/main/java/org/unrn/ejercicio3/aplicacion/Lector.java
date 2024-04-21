package org.unrn.ejercicio3.aplicacion;

import java.io.IOException;
import java.util.List;

public interface Lector {
    List<String[]> leerArchivo() throws IOException;
}
