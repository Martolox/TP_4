package org.unrn.ejercicio2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.unrn.ejercicio2.aplicacion.Emisor;
import org.unrn.ejercicio2.aplicacion.Lector;
import org.unrn.ejercicio2.aplicacion.Modelo;
import org.unrn.ejercicio2.sercicio.LectorDeArchivos;

public class TestMailDeSaludo {
    Lector lector;
    Emisor emisor;
    Modelo modelo;

    @BeforeEach
    public void setUp() {
        lector = new LectorDeArchivos("src/main/resources/data.csv");
        emisor = new MockEmisor();
        modelo = new Modelo(lector, emisor);
    }

    @Test
    public void seCargaListaYEnviaMail() {
        modelo.cargarListaEmpleados();
        modelo.eviarEmails(modelo.buscarPorFecha());
    }

    @Test
    public void seGarantizaQueUnEmpleadoCumplaHoy() {
        modelo.cargarListaEmpleados();
        modelo.debug_AgregarUltimoEmpleado();
        modelo.eviarEmails(modelo.buscarPorFecha());
    }
}
