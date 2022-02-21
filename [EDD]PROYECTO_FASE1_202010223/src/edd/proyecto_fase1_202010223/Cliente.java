package edd.proyecto_fase1_202010223;

public class Cliente {

    String id;
    String Nombre;
    int imgColor;
    int color;
    int imgBW;
    int bw;
    int dir;
    int pasos;
    int totalinicial;
    int totalactual;
    int ventanilla;
    Listaimg listaimg;

    public Cliente(String id, String Nombre, int imgColor, int imgBW) {
        this.id = id;
        this.Nombre = Nombre;
        this.imgColor = imgColor;
        this.imgBW = imgBW;
        this.dir = this.hashCode();
        this.pasos=0;
        this.listaimg=new Listaimg();
        this.totalactual=0;
        this.totalinicial=imgColor+imgBW;
        this.color = imgColor;
        this.bw = imgBW;
        this.ventanilla=0;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", Nombre=" + Nombre + ", imgColor=" + imgColor + ", imgBW=" + imgBW + ", dir=" + dir + '}';
    }

}
