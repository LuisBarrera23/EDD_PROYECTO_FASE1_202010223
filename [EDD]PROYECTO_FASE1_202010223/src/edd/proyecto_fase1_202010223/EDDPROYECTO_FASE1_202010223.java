package edd.proyecto_fase1_202010223;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;//libreria de scanner

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class EDDPROYECTO_FASE1_202010223 {

    static int paso = 1;
    static ListaRecepcion recepcion = new ListaRecepcion();
    static ListaVentanilla ventanillas = new ListaVentanilla();
    static Impresora impresoraC = new Impresora("Color");
    static Impresora impresoraBW = new Impresora("Blanco y negro");
    static Listaespera listaespera = new Listaespera();
    static Listaatendidos listaatendidos = new Listaatendidos();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);// variable para scannear
        int opcion;
        boolean salida = false;
        while (!salida) {
            try {
                System.out.println("---------------MENU PRINCIPAL---------------");
                System.out.println("1.Parametros iniciales");
                System.out.println("2.Ejecutar paso");
                System.out.println("3.Estado en memoria de las Estructuras");
                System.out.println("4.Reportes");
                System.out.println("5.Acerca de");
                System.out.println("6.Salir");
                System.out.println("Ingrese el numero de la opcion que desea realizar");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        System.out.println("opcion parametros");
                        int opcion2 = 0;
                        do {

                            System.out.println("---------------Menu parametros---------------");
                            System.out.println("1.Carga masiva de Clientes");
                            System.out.println("2.Cantidad de ventanillas");
                            System.out.println("3.Regresar al menu principal");
                            opcion2 = sc.nextInt();

                            switch (opcion2) {
                                case 1:
                                    System.out.println("-------carga masiva-------");
                                    leerJSON();
                                    break;
                                case 2:
                                    System.out.print("inserte la cantidad de ventanillas que desea tener:");
                                    int cantidad = sc.nextInt();
                                    ventanillas.generarventanillas(cantidad);
                                    ventanillas.recorrer();
                                    System.out.println(cantidad);
                                    break;
                                default:
                                    break;
                            }
                        }while(opcion2 != 3);
                        break;
                    case 2:
                        siguientepaso();
                        break;
                    case 3:
                        int opcion3 = 0;
                        do {

                            System.out.println("---------------Menu visualizacion de estructuras---------------");
                            System.out.println("1.Cola Recepcion");
                            System.out.println("2.Lista Ventanillas");
                            System.out.println("3.Lista Clientes en Espera");
                            System.out.println("4.Cola Impresoras");
                            System.out.println("5.Clientes atendidos");
                            System.out.println("6.Regresar al Menu Principal");
                            opcion3 = sc.nextInt();

                            switch (opcion3) {
                                case 1:
                                    recepcion.graficar();
                                    break;
                                case 2:
                                    ventanillas.graficar();
                                    break;
                                case 3:
                                    listaespera.graficar();
                                    break;
                                case 4:
                                    graficarimpresoras();
                                    break;
                                case 5:
                                    listaatendidos.graficar();
                                    break;
                                default:
                                    break;
                            }
                        } while (opcion3 != 6);
                        break;
                    case 4:
                        int opcion4 = 0;
                        do {

                            System.out.println("---------------Menu Reportes---------------");
                            System.out.println("1.Top clientes con mayor cantidad de imágenes a color.");
                            System.out.println("2.Top clientes con menor cantidad de imágenes en blanco y negro.");
                            System.out.println("3.Información del cliente que más pasos estuvo en el sistema.");
                            System.out.println("4.Datos de un cliente en específico");
                            System.out.println("5.Regresar al Menu Principal");
                            opcion4 = sc.nextInt();

                            switch (opcion4) {
                                case 1:
                                    listaatendidos.graficartop1();
                                    break;
                                case 2:
                                    listaatendidos.graficartop2();
                                    break;
                                case 3:
                                    listaatendidos.graficarmaspasos();
                                    break;
                                case 4:
                                    int id=0;
                                    System.out.print("Ingrese el ID del cliente que quiere buscar: ");
                                    id=sc.nextInt();
                                    listaatendidos.graficarcliente(id);
                                    break;
                                default:
                                    break;
                            }
                        } while (opcion4 != 5);
                        break;
                    case 5:
                        System.out.println("Luis Angel Barrera Velásquez");
                        System.out.println("202010223");
                        System.out.println("Estudiante de Ingenieria en Ciencias y Sistemas");
                        System.out.println("Quinto semestre");
                        break;
                    case 6:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no valida");

                }//fin del switch

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error, parametro ingresado no valido");
                sc.nextLine();
            }//final del nactch
        }
    }

    public static void leerJSON() throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String ruta = "";
        try {
            System.out.print("Ingrese la ruta del archivo .JSON: ");
            ruta = sc.next();
            File doc = new File(ruta);
            BufferedReader obj = new BufferedReader(new FileReader(doc));
            String texto = "";
            String contenido = "";
            while ((texto = obj.readLine()) != null) {
                contenido += texto + "\n";
            }
            String[] p = (contenido.toUpperCase()).split("\\{|\\}");
            contenido = "";
            for (int i = 0; i < p.length; i++) {
                if (p[i].contains("ID_CLIENTE")) {
                    contenido += "{" + p[i] + "},";
                }
            }
            contenido = "[" + contenido + "]";
            contenido = contenido.replace(",]", "]");
            JsonParser parser = new JsonParser();
            JsonArray arreglo = parser.parse(contenido).getAsJsonArray();
            for (int i = 0; i < arreglo.size(); i++) {
                JsonObject objeto = arreglo.get(i).getAsJsonObject();
                String id = objeto.get("ID_CLIENTE").getAsString();
                String nombre = objeto.get("NOMBRE_CLIENTE").getAsString();
                int icolor = objeto.get("IMG_COLOR").getAsInt();
                int ibw = objeto.get("IMG_BW").getAsInt();
                Cliente nuevo = new Cliente(id, nombre, icolor, ibw);
                recepcion.insertar(nuevo);
            }
            //recepcion.mostrar();
        } catch (Exception e) {
            sc.nextLine();
            System.out.println("Error en la lectura del archivo");
            System.out.println(e);
        }

    }

    public static void siguientepaso() {
        System.out.println("--------------------PASO " + paso + "--------------------");
        recepcion.siguiente_paso();
        listaespera.siguiente_paso();
        impresoraC.colaimp.siguiente_paso();
        impresoraBW.colaimp.siguiente_paso();
        ventanillas.siguiente_paso();
        paso++;
    }
    
    public static void graficarimpresoras(){
        NodoColaimp actual1=impresoraC.colaimp.primero;
        String dot="digraph G{\nlabel=\""+"COLA IMPRESORAS"+"\";\nlabelloc = \"t\"\nnode [shape=box,color=dodgerblue1];\n";
        String nodos="";
        String estructura="";
        nodos+="impresora"+impresoraC.hashCode()+"[label=\""+"Impresora a Color"+"\", width=1.5,height=1.5,style=filled];\n";
        if(actual1==null){
            System.out.println("No hay nada que mostrar");
        }else{
            estructura+="{rank=same\n"+"impresora"+impresoraC.hashCode()+"->"+"imagen"+actual1.imagen.hashCode()+"[dir=\"none\"];"+"\n"+"}"+"\n";
            while(actual1!=null){
                    nodos+="imagen"+actual1.imagen.hashCode()+"[label=\""+actual1.imagen.tipo+"\", width=1.5,height=1.5];\n";
                if(actual1.siguiente!=null){
                    estructura+="{rank=same\n"+"imagen"+actual1.imagen.hashCode()+"->"+"imagen"+actual1.siguiente.imagen.hashCode()+"[dir=\"none\"];"+"\n"+"}"+"\n";
                }
                actual1=actual1.siguiente;
            }
        }
        
        actual1=impresoraBW.colaimp.primero;
        nodos+="impresora"+impresoraBW.hashCode()+"[label=\""+"Impresora Blanco y Negro"+"\", width=1.5,height=1.5,style=filled];\n";
        if(actual1==null){
            System.out.println("No hay nada que mostrar");
        }else{
            estructura+="{rank=same\n"+"impresora"+impresoraBW.hashCode()+"->"+"imagen"+actual1.imagen.hashCode()+"[dir=\"none\"];"+"\n"+"}"+"\n";
            while(actual1!=null){
                    nodos+="imagen"+actual1.imagen.hashCode()+"[label=\""+actual1.imagen.tipo+"\", width=1.5,height=1.5];\n";
                if(actual1.siguiente!=null){
                    estructura+="{rank=same\n"+"imagen"+actual1.imagen.hashCode()+"->"+"imagen"+actual1.siguiente.imagen.hashCode()+"[dir=\"none\"];"+"\n"+"}"+"\n";
                }
                actual1=actual1.siguiente;
            }
        }
        
        estructura+="impresora"+impresoraC.hashCode()+"->"+"impresora"+impresoraBW.hashCode()+"[style=invis];";
        dot+=nodos+estructura+"\n}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("ColaImpresoras.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","ColaImpresoras.dot","-o","ColaImpresoras.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2=new ProcessBuilder("cmd.exe","/c","ColaImpresoras.png");
            p2.redirectErrorStream(true);
            p2.start();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    

}
