package edd.proyecto_fase1_202010223;

class Colaimpresion {
    NodoColaimp primero;
    
    public void insertar(Imagen img){
        NodoColaimp nueva=new NodoColaimp(img);
        if(primero==null){
            primero=nueva;
        }else{
            NodoColaimp actual=primero;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nueva;
        }
    }
    
    public void mostrar(){
        NodoColaimp actual=primero;
        while(actual!=null){
            System.out.println("Imagen: "+actual.imagen.tipo+" "+actual.imagen.dircliente);
            actual=actual.siguiente;
        }
    }
    public void siguiente_paso(){
        if(primero!=null){
            if(primero.imagen.pasos==2){
                primero.imagen.pasos--;
            }else if(primero.imagen.pasos==1){
                primero.imagen.pasos--;
                System.out.println("se imprimio la imagen "+primero.imagen.tipo);
                EDDPROYECTO_FASE1_202010223.listaespera.entregarimagen(primero.imagen);
                NodoColaimp aux=primero.siguiente;
                primero.siguiente=null;
                primero=aux;
            }
        }else{
            //System.out.println("no hay imagen para imprimir");
        }
    }
}
