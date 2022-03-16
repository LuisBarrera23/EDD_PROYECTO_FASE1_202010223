package edd.proyecto_fase2_202010223;

public class Matriz {
public NodoM raiz;

    public Matriz(String n) {
    this.raiz=new NodoM(-1,-1,n);
    }
    
    public void insertarNodo(int x,int y,String color){
        NodoM nuevo=new NodoM(x,y,color);
        NodoM columna=buscar_columna(x);
        NodoM fila=buscar_fila(y);
        
        if(columna==null&&fila==null){                  //CASO1
            columna=crear_columna(x);
            fila=crear_fila(y);
            nuevo=insertarenfila(nuevo,fila);
            nuevo=insertarencolumna(nuevo,columna);
        }else if(columna!=null&&fila==null){            //CASO2
            fila=crear_fila(y);
            nuevo=insertarenfila(nuevo,fila);
            nuevo=insertarencolumna(nuevo,columna);
        }else if(columna==null&&fila!=null){            //CASO3
            columna=crear_columna(x);
            nuevo=insertarenfila(nuevo,fila);
            nuevo=insertarencolumna(nuevo,columna);
        }else{                                          //CASO4
            nuevo=insertarenfila(nuevo,fila);
            nuevo=insertarencolumna(nuevo,columna);
        }
        
    }
    
    public NodoM buscar_columna(int x){
        NodoM actual=this.raiz;
        while(actual!=null){
            if(actual.x==x){
                return actual;
            }
            actual=actual.siguiente;
        }
        return null;
    }
    
    public NodoM buscar_fila(int y){
        NodoM actual=this.raiz;
        while(actual!=null){
            if(actual.y==y){
                return actual;
            }
            actual=actual.abajo;
        }
        return null;
    }
    
    public NodoM insertarenfila(NodoM nuevo,NodoM cabecerafila){
        NodoM actual=cabecerafila;
        boolean bandera=false;
        while(true){
            if(actual.x>nuevo.x){
                bandera=true;
                break;
            }else if(actual.siguiente!=null){
                actual=actual.siguiente;
            }else{
                break;
            }
        }
        if(bandera){
            nuevo.siguiente=actual;
            nuevo.anterior=actual.anterior;
            actual.anterior.siguiente=nuevo;
            actual.anterior=nuevo;
        }else{
            actual.siguiente=nuevo;
            nuevo.anterior=actual;
        }
        return nuevo;
    }
    
    public NodoM insertarencolumna(NodoM nuevo,NodoM cabeceracolumna){
        NodoM actual=cabeceracolumna;
        boolean bandera=false;
        while(true){
            if(actual.y>nuevo.y){
                bandera=true;
                break;
            }else if(actual.abajo!=null){
                actual=actual.abajo;
            }else{
                break;
            }
        }
        if(bandera){
            nuevo.abajo=actual;
            nuevo.arriba=actual.arriba;
            actual.arriba.abajo=nuevo;
            actual.arriba=nuevo;
        }else{
            actual.abajo=nuevo;
            nuevo.arriba=actual;
        }
        return nuevo;
    }
    
    public NodoM crear_columna(int x){
        NodoM nodo=new NodoM(x,-1,"columna"+x);
        return insertarenfila(nodo,this.raiz);
    }
    public NodoM crear_fila(int y){
        NodoM nodo=new NodoM(-1,y,"fila"+y);
        return insertarencolumna(nodo,this.raiz);
    }
    
    public void mostrarmatriz(){
        NodoM actual=raiz;
        while(actual!=null){
            NodoM actual2=actual;
            while(actual2!=null){
                System.out.println("Coordenada x="+actual2.x+" y="+actual2.y+" color="+actual2.color);
                actual2=actual2.siguiente;
            }
            actual=actual.abajo;
        }
    }

}
