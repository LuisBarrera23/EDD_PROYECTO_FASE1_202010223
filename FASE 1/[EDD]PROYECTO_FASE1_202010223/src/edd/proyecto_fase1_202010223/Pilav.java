package edd.proyecto_fase1_202010223;

public class Pilav {
    Nodopv primero;
    
    public void apilar(Imagen img){
        Nodopv nuevo=new Nodopv(img);
        if(primero==null){
            primero=nuevo;
        }else{
            nuevo.siguiente=primero;
            primero=nuevo;
        }
    }
    
    public void pasarimagenes(){
        Nodopv actual=primero;
        //mostrar();
        while(actual!=null){
            //System.out.println(actual.imagen.tipo);
            if(actual.imagen.tipo=="imgColor"){
                Imagen img=actual.imagen;
                EDDPROYECTO_FASE1_202010223.impresoraC.colaimp.insertar(img);
            }else if(actual.imagen.tipo=="imgBW"){
                Imagen img=actual.imagen;
                EDDPROYECTO_FASE1_202010223.impresoraBW.colaimp.insertar(img);
            }
            actual=actual.siguiente;
        }
        primero=null;
        //mostrar();
    }
    public void mostrar(){
        System.out.println("imagenes actuales en la ventanilla");
        Nodopv actual=primero;
        while(actual!=null){
            System.out.println("----"+actual.imagen.tipo);
            actual=actual.siguiente;
        }
    }
}
