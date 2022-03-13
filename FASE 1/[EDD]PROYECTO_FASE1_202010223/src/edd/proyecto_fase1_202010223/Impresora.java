package edd.proyecto_fase1_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Impresora {
    String tipo;
    Colaimpresion colaimp;
    int dir;

    public Impresora(String tipo) {
        this.tipo = tipo;
        this.colaimp = new Colaimpresion();
        this.dir = this.hashCode();
    }
    
    
}
