package edd.proyecto_fase2_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JOptionPane;

public class Matriz {
public NodoM raiz;
public int Ccolumnas=0;
public int Cfilas=0;

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
            boolean repetido=this.verificar_repetido(nuevo);
            if(repetido==false){
                nuevo=insertarenfila(nuevo,fila);
                nuevo=insertarencolumna(nuevo,columna);
            }
        }
        
    }
    
    public boolean verificar_repetido(NodoM nuevo){
        NodoM actual=raiz;
        while(actual!=null){
            NodoM actual2=actual;
            while(actual2!=null){
                if(nuevo.x==actual2.x&&nuevo.y==actual2.y){
                    actual2.color=nuevo.color;
                    return true;
                }
                actual2=actual2.siguiente;
            }
            actual=actual.abajo;
        }
        return false;
    }
    
    public NodoM verificar_existencia(int cor1, int cor2){
        NodoM actual=raiz;
        while(actual!=null){
            NodoM actual2=actual;
            while(actual2!=null){
                //System.out.println(cor1+","+cor2+"    "+actual2.x+","+actual2.y);
                if(actual2.x==cor1&&actual2.y==cor2){
                    //System.out.println("encontrado gg");
                    return actual2;
                }
                actual2=actual2.siguiente;
            }
            actual=actual.abajo;
        }
        return null;
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
        if(Ccolumnas<x){
            Ccolumnas=x;
        }
        return insertarenfila(nodo,this.raiz);
    }
    public NodoM crear_fila(int y){
        NodoM nodo=new NodoM(-1,y,"fila"+y);
        if(Cfilas<y){
            Cfilas=y;
        }
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
    
    public void graficardot_matriz(){
        String dot="digraph G{\nnode[shape=box width=1 height=1];\n";
        String nodos="";
        String estructura="";
        
        NodoM actual=raiz;
        while(actual!=null){
            NodoM actual2=actual;
            while(actual2!=null){
                if(actual2.y==-1&&actual2.x==-1){
                    nodos+="nodo"+actual2.hashCode()+"[label="+actual2.color+", style = filled, fillcolor = white,group="+actual2.x+"];\n";
                }else if(actual2.x==-1){
                    nodos+="nodo"+actual2.hashCode()+"[label="+actual2.color+", style = filled, fillcolor = white,group="+actual2.x+"];\n";
                }else if(actual2.y==-1){
                    nodos+="nodo"+actual2.hashCode()+"[label="+actual2.color+", style = filled, fillcolor = white,group="+actual2.x+"];\n";
                }else{
                    nodos+="nodo"+actual2.hashCode()+"[label=\"\""+", style = filled, fillcolor = \""+actual2.color+"\",group="+actual2.x+"];\n";
                }
                if(actual2.siguiente!=null){
                    estructura+="{rank=same "+"nodo"+actual2.hashCode()+"->"+"nodo"+actual2.siguiente.hashCode()+"[dir=both,arrowsize=0.5]}\n";
                }
                
                actual2=actual2.siguiente;
            }
            actual=actual.abajo;
        }
        
        actual=raiz;
        while(actual!=null){
            NodoM actual2=actual;
            while(actual2!=null){
                if(actual2.abajo!=null){
                    estructura+="nodo"+actual2.hashCode()+"->"+"nodo"+actual2.abajo.hashCode()+"[dir=both,arrowsize=0.5]\n";
                }
                actual2=actual2.abajo;
            }
            actual=actual.siguiente;
        }
        dot+=nodos+estructura+"}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("graficoCapa.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","graficoCapa.dot","-o","graficoCapa.png");
            p.redirectErrorStream(true);
            p.start();
            java.util.concurrent.TimeUnit.SECONDS.sleep(2);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    
    
    public void graficar_matriz2(){
        String dot="digraph G{\nnode [shape=plaintext ];\n";
        String nodos="";
        NodoM actual=raiz;
        
        //doble while para ver el numero de cabeceras de columnas (ver max)
        
        while(actual.siguiente!=null){
            actual=actual.siguiente;
        }
        int ancho=actual.x;
        actual=raiz;
        while(actual.abajo!=null){
            actual=actual.abajo;
        }
        int alto=actual.y;
        //System.out.println(ancho+" "+alto);
        nodos+="Nodounico[ label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"0\" width=\"100\" height=\"100\" >\n";
        
        actual=null;
        for (int j = 0; j < alto+1; j++) {
            nodos+="<tr>\n";
            for (int i = 0; i < ancho+1; i++) {
                actual=verificar_existencia(i, j);
                if(actual==null){
                    nodos+="<td bgcolor=\"white\" width=\"1\" height=\"1\"></td>\n";
                }else{
                    nodos+="<td bgcolor=\""+actual.color+"\" width=\"1\" height=\"1\"></td>\n";
                }
            }
            nodos+="</tr>\n";
        }
        nodos+="</table>>];\n";
        
        dot+=nodos+"}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("graficoCapa.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","graficoCapa.dot","-o","graficoCapa.png");
            p.redirectErrorStream(true);
            p.start();
            java.util.concurrent.TimeUnit.SECONDS.sleep(3);
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    public void graficar_imagen(){
        String dot="digraph G{\nnode [shape=plaintext ];\n";
        String nodos="";
        NodoM actual=raiz;
        
        //doble while para ver el numero de cabeceras de columnas (ver max)
        
        while(actual.siguiente!=null){
            actual=actual.siguiente;
        }
        int ancho=actual.x;
        actual=raiz;
        while(actual.abajo!=null){
            actual=actual.abajo;
        }
        int alto=actual.y;
        //System.out.println(ancho+" "+alto);
        nodos+="Nodounico[ label=<<table border=\"0\" cellspacing=\"0\" cellborder=\"0\" width=\"100\" height=\"100\" >\n";
        
        actual=null;
        for (int j = 0; j < alto+1; j++) {
            nodos+="<tr>\n";
            for (int i = 0; i < ancho+1; i++) {
                actual=verificar_existencia(i, j);
                if(actual==null){
                    nodos+="<td bgcolor=\"white\" width=\"1\" height=\"1\"></td>\n";
                }else{
                    nodos+="<td bgcolor=\""+actual.color+"\" width=\"1\" height=\"1\"></td>\n";
                }
            }
            nodos+="</tr>\n";
        }
        nodos+="</table>>];\n";
        
        dot+=nodos+"}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("graficoImagen.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","graficoImagen.dot","-o","graficoImagen.png");
            p.redirectErrorStream(true);
            p.start();
            java.util.concurrent.TimeUnit.SECONDS.sleep(3);
        }catch(Exception e){
            System.out.println(e);
        }
    }

}
