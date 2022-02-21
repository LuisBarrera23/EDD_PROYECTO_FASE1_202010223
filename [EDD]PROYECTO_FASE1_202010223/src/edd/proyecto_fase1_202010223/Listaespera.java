package edd.proyecto_fase1_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Listaespera {

    Nodoespera primero = null;
    Nodoespera ultimo = null;

    public void insertar(Cliente cli) {
        Nodoespera nuevo = new Nodoespera(cli);
        if (primero == null) {
            primero = nuevo;
            primero.siguiente = primero;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
        } else {
            ultimo.siguiente = nuevo;
            nuevo.siguiente = primero;
            nuevo.anterior = ultimo;
            ultimo = nuevo;
            primero.anterior = ultimo;
        }
    }

    public void mostrar() {
        Nodoespera actual = primero;
        if(actual==null){
            return;
        }
        do {
            System.out.println(actual.cliente.Nombre);
            System.out.println("tiene las siguientes imagenes impresas");
            Nodolistaimg actual2 = actual.cliente.listaimg.primero;
            while (actual2 != null) {
                System.out.println(actual2.imagen.tipo);
                actual2 = actual2.siguiente;
            }
            actual = actual.siguiente;
        } while (actual != primero);
    }

    public void entregarimagen(Imagen img) {
        Nodoespera actual = primero;
        do {
            if (actual.cliente.dir == img.dircliente) {
                System.out.println("imagen entregada a " + actual.cliente.Nombre);
                actual.cliente.listaimg.insertar(img);
                actual.cliente.totalactual++;
                //mostrar();
            }
            actual = actual.siguiente;
        } while (actual != primero);
    }

    public void siguiente_paso() {
        if (primero != null) {
            Nodoespera actual = primero;
            do {
                actual.cliente.pasos++;
                if (actual.cliente.totalinicial == actual.cliente.totalactual) {
                    System.out.println(actual.cliente.Nombre + " completo todas sus imagenes");
                    completado(actual.cliente);
                    //mostrar();
                }
                actual = actual.siguiente;
            } while (actual != primero);
        }
    }

    public void completado(Cliente cli) {
        Nodoespera actual = primero;
        Nodoespera anterior=ultimo;
        do {
            if(actual.cliente.dir==cli.dir){
                if(actual==primero){
                    EDDPROYECTO_FASE1_202010223.listaatendidos.insertar(cli);
                    primero=primero.siguiente;
                    ultimo.siguiente=primero;
                    primero.anterior=ultimo;
                }else if(actual==ultimo){
                    EDDPROYECTO_FASE1_202010223.listaatendidos.insertar(cli);
                    ultimo=anterior;
                    primero.anterior=ultimo;
                    ultimo.siguiente=primero;
                }else{
                    EDDPROYECTO_FASE1_202010223.listaatendidos.insertar(cli);
                    anterior.siguiente=actual.siguiente;
                    actual.siguiente.anterior=anterior;
                }
            }
            anterior=actual;
            actual = actual.siguiente;
        } while (actual != primero);
    }
    
    public void graficar(){
        Nodoespera actual=primero;
        String dot="digraph G{\nlabel=\""+"LISTA CLIENTES EN ESPERA"+"\";\nlabelloc = \"t\"\nnode [shape=box,color=dodgerblue1];\n";
        String nodos="";
        String estructura="";
        if(actual==null){
            System.out.println("No hay nada que mostrar");
        }else{
            do {
                nodos+="cliente"+actual.cliente.hashCode()+"[label=\""+actual.cliente.Nombre+"\\nID: "+actual.cliente.id+"\", width=1.5,height=1.5];\n";
                if(actual.siguiente!=null){
                    estructura+="{rank=same\n"+"cliente"+actual.cliente.hashCode()+"->"+"cliente"+actual.siguiente.cliente.hashCode()+"[dir=\"both\"]\n"+"}"+"\n";
                }
                
                if(actual.cliente.listaimg.primero!=null){
                    Nodolistaimg actual2 = actual.cliente.listaimg.primero;
                    estructura+="cliente"+actual.cliente.hashCode()+"->"+"imagen"+actual2.imagen.hashCode()+";\n";
                    while (actual2 != null) {
                        nodos+="imagen"+actual2.imagen.hashCode()+"[label=\""+actual2.imagen.tipo+"\", width=1.5,height=1.5];\n";
                        if(actual2.siguiente!=null){
                            estructura+="imagen"+actual2.imagen.hashCode()+"->"+"imagen"+actual2.siguiente.imagen.hashCode()+";\n";
                        }
                        actual2 = actual2.siguiente;
                    }
                }
                actual = actual.siguiente;
            } while (actual != primero);
        }
        dot+=nodos+estructura+"\n}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("ListaEspera.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","ListaEspera.dot","-o","ListaEspera.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2=new ProcessBuilder("cmd.exe","/c","ListaEspera.png");
            p2.redirectErrorStream(true);
            p2.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
