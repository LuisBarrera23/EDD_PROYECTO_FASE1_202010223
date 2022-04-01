package edd.proyecto_fase2_202010223;
public class Imagen {
    public Matriz Mimagen;
    public int numero;
    public ABB capas;

    public Imagen(int numero) {
        this.numero = numero;
        this.Mimagen=new Matriz("IMAGEN" + numero);
        this.capas=new ABB();
    }
    
    public void agregarcapa(Capa capa){
        if(capa!=null){
        capas.insertar(capa);
        actualizarimagen(capa.matriz);
        }
    }
    
    public void actualizarimagen(Matriz m){
        NodoM actual=m.raiz;
        while(actual!=null){
            NodoM actual2=actual;
            while(actual2!=null){
                if(actual2.x==-1&&actual2.y==-1){
                    //ignoramos nodo raiz
                }else{
                    Mimagen.insertarNodo(actual2.x, actual2.y, actual2.color);
                }
                //System.out.println("Coordenada x="+actual2.x+" y="+actual2.y+" color="+actual2.color);
                actual2=actual2.siguiente;
            }
            actual=actual.abajo;
        }
    }
    
}
