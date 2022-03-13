package edd.proyecto_fase1_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class ListaRecepcion {

    Nodorep primero;
    int contador=0;
    String[] nombre={"Carlos","Pedro","Angel","Luis","Eduardo","Hugo","Martin","Lucas","Mateo","Leo","Daniel","Alejandro","Pablo","Manuel","Alvaro","Adrian","David","Mario","Enzo","Diego"};
    String[] apellido={"Pereira","Velasquez","Barrera","Morales","Baran","Lopez","Perez","Garcia","Hernandez","Ramirez","Gomez","Martinez","Vasquez","de Leon","Rodriguez","Diaz","Reyes","Castillo","Cruz","Juarez"};
    
    public void insertar(Cliente cli){
        Nodorep nuevo=new Nodorep(cli);
        if(primero==null){
            primero=nuevo;
            contador=Integer.parseInt(nuevo.cliente.id);
        }else{
            Nodorep actual=primero;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nuevo;
            contador=Integer.parseInt(nuevo.cliente.id);
        }
    }
    
    public void mostrar(){
        Nodorep actual=primero;
        while(actual!=null){
            System.out.println("Cliente: "+actual.cliente.Nombre+" id: "+actual.cliente.id+" trae fotos: "+actual.cliente.imgColor+","+actual.cliente.imgBW);
            actual=actual.siguiente;
        }
    }
    
    public void siguiente_paso(){
        Nodorep actual=primero;
        while(actual!=null){
            actual.cliente.pasos+=1;
            actual=actual.siguiente;
        }
        int nuevos = (int)(Math.random()*4);
        for (int i = 0; i < nuevos; i++) {
            contador++;
            int nom=(int)(Math.random()*20);
            int ape=(int)(Math.random()*20);
            int icolor = (int)(Math.random()*3);
            int ibw = (int)(Math.random()*3);
            if(icolor==0&&ibw==0){
                Cliente nuevo=new Cliente(String.valueOf(contador),nombre[nom]+" "+apellido[ape],0,1);
                insertar(nuevo);
            }else{
                Cliente nuevo=new Cliente(String.valueOf(contador),nombre[nom]+" "+apellido[ape],icolor,ibw);
                insertar(nuevo);
            }
            
        }
        System.out.println("Entraron "+nuevos+" Clientes a la recepción");
    }
    
    public Cliente pasarcliente(){
        if(primero==null){
            return null;
        }else{
            Cliente cli=primero.cliente;
            Nodorep aux=primero.siguiente;
            primero.siguiente=null;
            primero=aux;
            return cli;
        }
        
        
    }
    
    public void graficar(){
        Nodorep actual=primero;
        String dot="digraph G{\nlabel=\""+"COLA RECEPCIÓN"+"\";\nlabelloc = \"t\"\nnode [shape=Mcircle,color=dodgerblue1];\n";
        String nodos="";
        String estructura="";
        if(actual==null){
            System.out.println("No hay nada que mostrar");
        }else{
            while(actual!=null){
                nodos+="cliente"+actual.cliente.hashCode()+"[label=\""+actual.cliente.Nombre+"\\nID: "+actual.cliente.id+"\\nIMG_Color: "+actual.cliente.imgColor+"\\nIMG_BW: "+actual.cliente.imgBW+"\", width=1.5,height=1.5];\n";
                if(actual.siguiente!=null){
                    estructura+="cliente"+actual.cliente.hashCode()+"->"+"cliente"+actual.siguiente.cliente.hashCode()+"[dir=\"none\"];\n";
                }
                actual=actual.siguiente;
            }
        }
        dot+=nodos+"{rank=same\n"+estructura+"}"+"\n}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("ColaRecepcion.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","ColaRecepcion.dot","-o","ColaRecepcion.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2=new ProcessBuilder("cmd.exe","/c","ColaRecepcion.png");
            p2.redirectErrorStream(true);
            p2.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }
}
