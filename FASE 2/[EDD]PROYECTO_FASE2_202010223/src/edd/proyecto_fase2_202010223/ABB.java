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
    
    void preorder(Nodo temp) {
        if (temp != null) {
            System.out.print(temp.capa.numero + " ");
            preorder(temp.izquierda);
            preorder(temp.derecha);
        }
    }

    void enorder(Nodo temp) {
        if (temp != null) {
            enorder(temp.izquierda);
            System.out.print(temp.capa.numero + " ");
            enorder(temp.derecha);
        }
    }

    void postorder(Nodo temp) {
        if (temp != null) {
            postorder(temp.izquierda);
            postorder(temp.derecha);
            System.out.print(temp.capa.numero + " ");
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
        System.out.println(dot);
        return dot;
        
    }
}
