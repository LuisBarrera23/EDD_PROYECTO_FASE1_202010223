package edd.proyecto_fase2_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author angel
 */

class NodoImg {
    public Imagen imagen;
    public NodoImg siguiente=null;
    public NodoImg(Imagen imagen){
        this.imagen=imagen;
    }
}
public class Listaimagenes {
    public NodoImg primero;
    
    public void insertar(Imagen img){
        NodoImg nuevo=new NodoImg(img);
        if(primero==null){
            primero=nuevo;
        }else{
            NodoImg actual=primero;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nuevo;
        }
    }
    
    
}
