package edd.proyecto_fase1_202010223;

public class Nodoespera {
    Cliente cliente;
    Nodoespera siguiente=null;
    Nodoespera anterior=null;
    public Nodoespera(Cliente cli){
        this.cliente=cli;
    }
}
