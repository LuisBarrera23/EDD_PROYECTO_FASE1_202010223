package edd.proyecto_fase1_202010223;

public class Ventanilla {
    int numero;
    int dir;
    Cliente cliente;
    Pilav imagenes;

    public Ventanilla(int numero) {
        this.numero = numero;
        this.dir = this.hashCode();
        this.cliente = null;
        this.imagenes=new Pilav();
    }
    
}
