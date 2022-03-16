package edd.proyecto_fase2_202010223;

public class NodoM {
    public NodoM siguiente=null;
    public NodoM anterior=null;
    public NodoM arriba=null;
    public NodoM abajo=null;
    
    public String color;
    public int x;
    public int y;
    
    public NodoM(int x, int y,String color){
        this.x=x;
        this.y=y;
        this.color=color;
    }

    @Override
    public String toString() {
        return "NodoM{" + "color=" + color + ", x=" + x + ", y=" + y + '}';
    }
    
    
}
