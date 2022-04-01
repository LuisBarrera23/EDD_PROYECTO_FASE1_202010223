package edd.proyecto_fase2_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 *
 * @author angel
 */

class NodoALB {
    public Album album;
    public NodoALB siguiente=null;
    public NodoALB(Album album){
        this.album=album;
    }
}
public class ListaAlbum {
    public NodoALB primero;
    
    public void insertar(Album album){
        NodoALB nuevo=new NodoALB(album);
        if(primero==null){
            primero=nuevo;
        }else{
            NodoALB actual=primero;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nuevo;
        }
    }
    
    public void mostrar_grafico(){
        NodoALB actual=primero;
        String dot="digraph G{\nnode [shape=box];\n";
        String nodos="";
        String estructura="";
        if(actual==null){
            System.out.println("No hay nada que mostrar");
        }else{
            while(actual!=null){
                nodos+="AL"+actual.hashCode()+"[label=\""+actual.album.nombre+"\"];\n";
                if(actual.siguiente!=null){
                    estructura+="{rank=same\n"+"AL"+actual.hashCode()+"->"+"AL"+actual.siguiente.hashCode()+";\n}\n";
                }
                NodoImg actual2=actual.album.imagenes.primero;
                if(actual2!=null){
                    estructura+="AL"+actual.hashCode()+"->"+"IMG"+actual2.hashCode()+";\n";
                }
                while(actual2!=null){
                    nodos+="IMG"+actual2.hashCode()+"[label=\"IMAGEN "+actual2.imagen.numero+"\"];\n";
                    if(actual2.siguiente!=null){
                        estructura+="IMG"+actual2.hashCode()+"->"+"IMG"+actual2.siguiente.hashCode()+";\n";
                    }
                    actual2=actual2.siguiente;
                }
                actual=actual.siguiente;
            }
        }
        dot+=nodos+estructura+"\n}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("graficoAlbum.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","graficoAlbum.dot","-o","graficoAlbum.png");
            p.redirectErrorStream(true);
            p.start();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
