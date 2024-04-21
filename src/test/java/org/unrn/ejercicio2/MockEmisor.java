package org.unrn.ejercicio2;

import org.unrn.ejercicio2.aplicacion.Emisor;

public class MockEmisor implements Emisor {
    @Override
    public void enviarMail(String texto) {
        System.out.println("Email enviado satisfactoriamente");
    }
}
