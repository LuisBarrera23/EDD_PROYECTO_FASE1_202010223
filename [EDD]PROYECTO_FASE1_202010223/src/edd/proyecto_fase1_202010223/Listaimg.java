package edd.proyecto_fase1_202010223;

public class Listaimg {
    Nodolistaimg primero;
    
    public void insertar(Imagen img){
        Nodolistaimg nueva=new Nodolistaimg(img);
        if(primero==null){
            primero=nueva;
        }else{
            Nodolistaimg actual=primero;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nueva;
        }
    }
}
