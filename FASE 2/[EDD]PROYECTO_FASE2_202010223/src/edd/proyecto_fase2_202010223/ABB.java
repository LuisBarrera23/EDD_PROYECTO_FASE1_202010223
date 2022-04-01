package edd.proyecto_fase2_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author Espino
 * @modificado Luis Barrera para implementacion
 */
public class ABB {
    class Nodo {

        Capa capa;
        Nodo izquierda;
        Nodo derecha;

        public Nodo(Capa capa) {
            this.capa = capa;
            this.izquierda=null;
            this.derecha=null;
        }

        
    }
    
    Nodo raiz=null;
    String estructura;
    Capa buscada=null;
    int contador=0;
    int conteo=0;
    Imagen recorrida;
    String capas;
    
    void insertar(Capa capa){
        if(raiz!=null){
            insertar(capa,raiz);
        }else{
            raiz=new Nodo(capa);
        }
    }
    
    void insertar(Capa capa,Nodo temp){
        if(capa.numero<temp.capa.numero){
           if(temp.izquierda!=null){
               insertar(capa,temp.izquierda);
           }else{
               temp.izquierda=new Nodo(capa);
           }
        }else if(capa.numero>temp.capa.numero){
            if(temp.derecha!=null){
                insertar(capa,temp.derecha);
            }else{
                temp.derecha=new Nodo(capa);
            }
        }
    }
    
    
    
    void modificado(Nodo temp) {
        if (temp != null) {
            if (temp.izquierda != null) {
                estructura+=temp.capa.numero + "->" + temp.izquierda.capa.numero+"\n";
                //System.out.println(tmp.value + "->" + tmp.left.value);
            }
            if (temp.derecha != null) {
                estructura+=temp.capa.numero + "->" + temp.derecha.capa.numero+"\n";
                //System.out.println(tmp.value + "->" + tmp.right.value);
            }
            modificado(temp.izquierda);
            modificado(temp.derecha);
            

            //System.out.print(tmp.value+" ");
        }
    }
    
    String graficar(){
        estructura="";
        modificado(raiz);
        String dot="digraph G{\nnode [shape=circle,color=dodgerblue1,style = filled];\n";
        
        dot+=estructura+"\n}";
        //System.out.println(dot);
        return dot;
        
    }
    
    void combocapas(Nodo temp,Vcliente vent) {
        if (temp != null) {
            vent.ComboCapas.addItem(String.valueOf(temp.capa.numero));
            combocapas(temp.izquierda,vent);
            combocapas(temp.derecha,vent);
        }
    }
    
    void contarcapas(Nodo temp){
        if (temp != null) {
            contador++;
            contarcapas(temp.izquierda);
            contarcapas(temp.derecha);
        }
    }
    void comboCantidad(Vcliente vent){
        contador=0;
        contarcapas(raiz);
        //System.out.println(contador);
        for (int i = 0; i < contador; i++) {
            vent.CombocantidadCapas.addItem(String.valueOf(i+1));
        }
    }
    
    void buscar(Nodo temp,int n) {
        if (temp != null) {
            buscar(temp.izquierda,n);
            buscar(temp.derecha,n);
            if(temp.capa.numero==n){
                buscada=temp.capa;
            }
        }        
    }
    
    Capa buscarcapa(int num){
        buscada=null;
        buscar(raiz,num);
        if(buscada!=null){
            return buscada;
        }else{
            return buscada;
        }
    }
    
    void graficarcapa(int num){
        buscada=null;
        buscar(raiz,num);
        if(buscada!=null){
            buscada.matriz.graficar_matriz2();
        }
    }
    
    void graficarcapalogica(int num){
        buscada=null;
        buscar(raiz,num);
        if(buscada!=null){
            buscada.matriz.graficardot_matriz();
        }
    }
    
    void preorder(Nodo temp) {
        if (temp != null) {
            if(conteo!=0){
                recorrida.agregarcapa(temp.capa);
                conteo--;
                capas+=temp.capa.numero+" ";
            }
            preorder(temp.izquierda);
            preorder(temp.derecha);
        }
    }

    void enorder(Nodo temp) {
        if (temp != null) {
            enorder(temp.izquierda);
            if(conteo!=0){
                recorrida.agregarcapa(temp.capa);
                conteo--;
                capas+=temp.capa.numero+" ";
            }
            enorder(temp.derecha);
        }
    }

    void postorder(Nodo temp) {
        if (temp != null) {
            postorder(temp.izquierda);
            postorder(temp.derecha);
            if(conteo!=0){
                recorrida.agregarcapa(temp.capa);
                conteo--;
                capas+=temp.capa.numero+" ";
            }
        }
    }
    
    void recorrer(int tipo, int cantidad){
        conteo=0;
        conteo=cantidad;
        capas="";
        recorrida=new Imagen(0);
        if(tipo==0){
            preorder(raiz);
            recorrida.Mimagen.graficar_imagen();
            //preorder
        }else if(tipo==1){
            //inorder
            enorder(raiz);
            recorrida.Mimagen.graficar_imagen();
        }else if(tipo==2){
            //postorder
            postorder(raiz);
            recorrida.Mimagen.graficar_imagen();
        }
    }
}
