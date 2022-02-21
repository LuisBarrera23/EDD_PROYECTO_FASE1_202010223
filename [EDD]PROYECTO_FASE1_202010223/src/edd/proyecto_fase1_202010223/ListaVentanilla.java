package edd.proyecto_fase1_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ListaVentanilla {
    Nodoven primero;
    
    public void generarventanillas(int cantidad){
        for (int i = 0; i < cantidad; i++) {
            Ventanilla nueva=new Ventanilla(i+1);
            Nodoven nuevo=new Nodoven(nueva);
            insertar(nuevo);
        }
    }
    public void insertar(Nodoven ven){
        if(primero==null){
            primero=ven;
        }else{
            Nodoven actual=primero;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=ven;
        }
    }
    public void recorrer(){
        Nodoven actual=primero;
        while(actual!=null){
            System.out.println("ventanilla: "+actual.ventanilla.numero);
            actual=actual.siguiente;
        }
    }
    public void siguiente_paso(){
        Nodoven actual=primero;
        while(actual!=null){
            if(actual.ventanilla.cliente!=null){
                actual.ventanilla.cliente.pasos++;
            }
            actual=actual.siguiente;
        }
        actual=primero;
        while(actual!=null){
            if(actual.ventanilla.cliente==null){
                Cliente cli=EDDPROYECTO_FASE1_202010223.recepcion.pasarcliente();
                actual.ventanilla.cliente=cli;
                if(actual.ventanilla.cliente!=null){
                    System.out.println("Cliente: "+cli.Nombre+" paso a ventanilla "+actual.ventanilla.numero);
                    actual.ventanilla.cliente.ventanilla=actual.ventanilla.numero;
                }
                return;
            }else{
                if(actual.ventanilla.cliente.imgColor!=0){
                    Imagen nueva=new Imagen("imgColor",actual.ventanilla.cliente.dir,2);
                    actual.ventanilla.imagenes.apilar(nueva);
                    actual.ventanilla.cliente.imgColor--;
                    System.out.println("La ventanilla "+actual.ventanilla.numero+" recibio una imagen a color");
                }else if(actual.ventanilla.cliente.imgBW!=0){
                    Imagen nueva=new Imagen("imgBW",actual.ventanilla.cliente.dir,1);
                    actual.ventanilla.imagenes.apilar(nueva);
                    actual.ventanilla.cliente.imgBW--;
                    System.out.println("La ventanilla "+actual.ventanilla.numero+" recibio una imagen en blanco y negro");
                }else if(actual.ventanilla.cliente.imgBW==0&&actual.ventanilla.cliente.imgColor==0){
                    System.out.println("cliente"+actual.ventanilla.cliente.Nombre+" de la ventanilla "+actual.ventanilla.numero+" entrego todas sus imagenes");
                    System.out.println("El cliente de la ventanilla "+actual.ventanilla.numero+" va a sala de espera");
                    EDDPROYECTO_FASE1_202010223.listaespera.insertar(actual.ventanilla.cliente);
                    actual.ventanilla.cliente=null;
                    actual.ventanilla.cliente=EDDPROYECTO_FASE1_202010223.recepcion.pasarcliente();
                    if(actual.ventanilla.cliente!=null){
                        System.out.println("Cliente: "+actual.ventanilla.cliente.Nombre+" paso a ventanilla "+actual.ventanilla.numero);
                        actual.ventanilla.cliente.ventanilla=actual.ventanilla.numero;
                    }
                    //EDDPROYECTO_FASE1_202010223.listaespera.mostrar();
                    actual.ventanilla.imagenes.pasarimagenes();
                }
            }
            actual=actual.siguiente;
        }
    }
    
    public void graficar(){
        Nodoven actual=primero;
        String dot="digraph G{\nlabel=\""+"LISTA VENTANILLAS"+"\";\nlabelloc = \"t\"\nnode [shape=box,color=dodgerblue1];\n";
        String nodos="";
        String estructura="";
        if(actual==null){
            System.out.println("No hay nada que mostrar");
        }else{
            while(actual!=null){
                nodos+="ventanilla"+actual.ventanilla.hashCode()+"[label=\""+"Ventanilla"+actual.ventanilla.numero+"\", width=1.5,height=1.5];\n";
                if(actual.ventanilla.cliente!=null){
                    nodos+="cliente"+actual.ventanilla.cliente.hashCode()+"[label=\""+actual.ventanilla.cliente.Nombre+"\\nID: "+actual.ventanilla.cliente.id+"\\nIMG_Color: "+actual.ventanilla.cliente.imgColor+"\\nIMG_BW: "+actual.ventanilla.cliente.imgBW+"\", width=1.5,height=1.5];\n";
                    estructura+="cliente"+actual.ventanilla.cliente.hashCode()+"->"+"ventanilla"+actual.ventanilla.hashCode()+"[dir=\"none\"];\n";
                }
                if(actual.siguiente!=null){
                    estructura+="{rank=same\n"+"ventanilla"+actual.ventanilla.hashCode()+"->"+"ventanilla"+actual.siguiente.ventanilla.hashCode()+"\n"+"}"+"\n";
                }
                
                if(actual.ventanilla.imagenes.primero!=null){
                    Nodopv actual2=actual.ventanilla.imagenes.primero;
                    estructura+="ventanilla"+actual.ventanilla.hashCode()+"->"+"imagen"+actual2.imagen.hashCode()+";\n";
                    while(actual2!=null){
                        nodos+="imagen"+actual2.imagen.hashCode()+"[label=\""+actual2.imagen.tipo+"\", width=1.5,height=1.5];\n";
                        if(actual2.siguiente!=null){
                            estructura+="imagen"+actual2.imagen.hashCode()+"->"+"imagen"+actual2.siguiente.imagen.hashCode()+";\n";
                        }
                        actual2=actual2.siguiente;
                    }
                    
                }
                actual=actual.siguiente;
            }
        }
        dot+=nodos+estructura+"\n}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("ListaVentanilla.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","ListaVentanilla.dot","-o","ListaVentanilla.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2=new ProcessBuilder("cmd.exe","/c","ListaVentanilla.png");
            p2.redirectErrorStream(true);
            p2.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
