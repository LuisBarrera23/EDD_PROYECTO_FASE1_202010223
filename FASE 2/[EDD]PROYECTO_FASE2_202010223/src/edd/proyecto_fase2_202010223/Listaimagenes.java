package edd.proyecto_fase2_202010223;


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
        nuevo.siguiente=primero;
        primero=nuevo;
    }
    public void mostrar(){
        NodoImg actual=primero;
        while(actual!=null){
            System.out.print(actual.imagen.numero+" ");
            actual=actual.siguiente;
        }
        System.out.println(" ");
    }
    
    public void eliminar(int numero){
        //mostrar();
        NodoImg actual=this.primero;
        NodoImg anterior=null;
        
        while(actual!=null&&actual.imagen.numero!=numero){
            anterior=actual;
            actual=actual.siguiente;
        }
        
        if(anterior==null){
            this.primero=actual.siguiente;
        }else if(actual!=null&&actual.siguiente==null){
            anterior.siguiente=null;
        }else if(actual!=null){
            anterior.siguiente=actual.siguiente;
        }
        //System.out.println("despues ");
        //mostrar();
    }
    
    public void topcapas(){
        if(primero!=null){
            NodoImg pivote=primero;
            NodoImg actual=null;
            Imagen aux=null;
            while(pivote.siguiente!=null){
                actual=pivote.siguiente;
                while(actual!=null){
                    if(pivote.imagen.capas.totalcapas()<actual.imagen.capas.totalcapas()){
                        aux=pivote.imagen;
                        pivote.imagen=actual.imagen;
                        actual.imagen=aux;
                    }
                    actual=actual.siguiente;
                }
                pivote=pivote.siguiente;
            }
        }
        mostrar();
    }
    
    
}
