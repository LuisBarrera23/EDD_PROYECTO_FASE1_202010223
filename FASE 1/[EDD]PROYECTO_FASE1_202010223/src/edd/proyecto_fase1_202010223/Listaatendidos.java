package edd.proyecto_fase1_202010223;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class Listaatendidos {

    Nodoaten primero;

    public void insertar(Cliente cli) {
        Nodoaten nuevo = new Nodoaten(cli);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodoaten actual = primero;
            while (actual.siguiente != null) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
        }
        //mostrar();
    }

    public void mostrar() {
        System.out.println("lista de los atendidos-------------------------");
        Nodoaten actual = primero;
        while (actual != null) {
            System.out.println(actual.cliente.toString());
            actual = actual.siguiente;
        }
    }

    public void graficar() {
        Nodoaten actual = primero;
        String dot = "digraph G{\nlabel=\"" + "Lista Atendidos" + "\";\nlabelloc = \"t\"\nnode [shape=Mcircle,color=dodgerblue1];\n";
        String nodos = "";
        String estructura = "";
        if (actual == null) {
            System.out.println("No hay nada que mostrar");
        } else {
            while (actual != null) {
                nodos += "cliente" + actual.cliente.hashCode() + "[label=\"" + actual.cliente.Nombre + "\\nID: " + actual.cliente.id + "\\nIMG_Color: " + actual.cliente.color + "\\nIMG_BW: " + actual.cliente.bw + "\\nPasos: " + actual.cliente.pasos + "\\nVentanilla: " + actual.cliente.ventanilla + "\", width=1.5,height=1.5];\n";
                if (actual.siguiente != null) {
                    estructura += "cliente" + actual.cliente.hashCode() + "->" + "cliente" + actual.siguiente.cliente.hashCode() + ";\n";
                }
                actual = actual.siguiente;
            }
        }
        dot += nodos + "{rank=same\n" + estructura + "}" + "\n}";
        //System.out.println(dot);
        try {
            FileWriter f = new FileWriter("ListaAtendidos.dot");
            BufferedWriter bufer = new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p = new ProcessBuilder("dot", "-Tpng", "ListaAtendidos.dot", "-o", "ListaAtendidos.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2 = new ProcessBuilder("cmd.exe", "/c", "ListaAtendidos.png");
            p2.redirectErrorStream(true);
            p2.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void graficartop1() {
        Nodoaten actual = primero;
        String dot = "digraph G{\nlabel=\"" + "TOP CLIENTES CON MAYOR IMAGENES A COLOR" + "\";\nlabelloc = \"t\"\nnode [shape=BOX,color=dodgerblue1];\n";
        String nodos = "";
        String estructura = "";

        if (primero != null) {
            Nodoaten pivote = primero;
            actual = null;
            Cliente aux = null;
            while (pivote.siguiente != null) {
                actual = pivote.siguiente;
                while (actual != null) {
                    if (pivote.cliente.color < actual.cliente.color) {
                        aux = pivote.cliente;
                        pivote.cliente = actual.cliente;
                        actual.cliente = aux;
                    }
                    actual = actual.siguiente;
                }
                pivote = pivote.siguiente;
            }
        }

        actual = primero;
        if (actual == null) {
            System.out.println("No hay nada que mostrar");
        } else {
            int iterador = 0;
            while (actual != null) {
                nodos += "cliente" + actual.cliente.hashCode() + "[label=\"" + actual.cliente.Nombre + "\\nID: " + actual.cliente.id + "\\nIMG_Color: " + actual.cliente.color + "\\nIMG_BW: " + actual.cliente.bw + "\\nPasos: " + actual.cliente.pasos + "\\nVentanilla: " + actual.cliente.ventanilla + "\", width=1.5,height=1.5];\n";
                if (actual.siguiente != null && iterador != 4) {
                    estructura += "cliente" + actual.cliente.hashCode() + "->" + "cliente" + actual.siguiente.cliente.hashCode() + ";\n";
                }
                actual = actual.siguiente;
                iterador++;
                if (iterador == 5) {
                    break;
                }
            }
        }
        dot += nodos + estructura + "\n}";
        //System.out.println(dot);
        try {
            FileWriter f = new FileWriter("top1.dot");
            BufferedWriter bufer = new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p = new ProcessBuilder("dot", "-Tpng", "top1.dot", "-o", "top1.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2 = new ProcessBuilder("cmd.exe", "/c", "top1.png");
            p2.redirectErrorStream(true);
            p2.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void graficartop2() {
        Nodoaten actual = primero;
        String dot = "digraph G{\nlabel=\"" + "TOP CLIENTES CON MENOR IMAGENES EN BLANCO Y NEGRO" + "\";\nlabelloc = \"t\"\nnode [shape=BOX,color=dodgerblue1];\n";
        String nodos = "";
        String estructura = "";

        if (primero != null) {
            Nodoaten pivote = primero;
            actual = null;
            Cliente aux = null;
            while (pivote.siguiente != null) {
                actual = pivote.siguiente;
                while (actual != null) {
                    if (pivote.cliente.bw > actual.cliente.bw) {
                        aux = pivote.cliente;
                        pivote.cliente = actual.cliente;
                        actual.cliente = aux;
                    }
                    actual = actual.siguiente;
                }
                pivote = pivote.siguiente;
            }
        }

        actual = primero;
        if (actual == null) {
            System.out.println("No hay nada que mostrar");
        } else {
            int iterador = 0;
            while (actual != null) {
                nodos += "cliente" + actual.cliente.hashCode() + "[label=\"" + actual.cliente.Nombre + "\\nID: " + actual.cliente.id + "\\nIMG_Color: " + actual.cliente.color + "\\nIMG_BW: " + actual.cliente.bw + "\\nPasos: " + actual.cliente.pasos + "\\nVentanilla: " + actual.cliente.ventanilla + "\", width=1.5,height=1.5];\n";
                if (actual.siguiente != null && iterador != 4) {
                    estructura += "cliente" + actual.cliente.hashCode() + "->" + "cliente" + actual.siguiente.cliente.hashCode() + ";\n";
                }
                actual = actual.siguiente;
                iterador++;
                if (iterador == 5) {
                    break;
                }
            }
        }
        dot += nodos + estructura + "\n}";
        //System.out.println(dot);
        try {
            FileWriter f = new FileWriter("top2.dot");
            BufferedWriter bufer = new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p = new ProcessBuilder("dot", "-Tpng", "top2.dot", "-o", "top2.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2 = new ProcessBuilder("cmd.exe", "/c", "top2.png");
            p2.redirectErrorStream(true);
            p2.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void graficarmaspasos() {
        Nodoaten actual = primero;
        String dot = "digraph G{\nlabel=\"" + "CLIENTE CON MAS PASOS EN EL SISTEMA" + "\";\nlabelloc = \"t\"\nnode [shape=Mcircle,color=dodgerblue1];\n";
        String nodos = "";
        if (actual == null) {
            System.out.println("No hay nada que mostrar");
        } else {
            if (primero != null) {
                Nodoaten pivote = primero;
                actual = null;
                Cliente aux = null;
                while (pivote.siguiente != null) {
                    actual = pivote.siguiente;
                    while (actual != null) {
                        if (pivote.cliente.pasos < actual.cliente.pasos) {
                            aux = pivote.cliente;
                            pivote.cliente = actual.cliente;
                            actual.cliente = aux;
                        }
                        actual = actual.siguiente;
                    }
                    pivote = pivote.siguiente;
                }
            }
            actual = primero;
            nodos += "cliente" + actual.cliente.hashCode() + "[label=\"" + actual.cliente.Nombre + "\\nID: " + actual.cliente.id + "\\nIMG_Color: " + actual.cliente.color + "\\nIMG_BW: " + actual.cliente.bw + "\\nPasos: " + actual.cliente.pasos + "\\nVentanilla: " + actual.cliente.ventanilla + "\", width=1.5,height=1.5];\n";
        }
        dot += nodos + "\n}";
        //System.out.println(dot);
        try {
            FileWriter f = new FileWriter("Reporte3.dot");
            BufferedWriter bufer = new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p = new ProcessBuilder("dot", "-Tpng", "Reporte3.dot", "-o", "Reporte3.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2 = new ProcessBuilder("cmd.exe", "/c", "Reporte3.png");
            p2.redirectErrorStream(true);
            p2.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void graficarcliente(int id) {
        String buscado = String.valueOf(id);
        Nodoaten actual = primero;
        String dot = "digraph G{\nlabel=\"" + "CLIENTE" + "\";\nlabelloc = \"t\"\nnode [shape=Mcircle,color=dodgerblue1];\n";
        String nodos = "";
        if (actual == null) {
            System.out.println("No hay nada que mostrar");
        } else {
            actual = primero;
            boolean encontrado=false;
            while (actual != null) {
                if (actual.cliente.id.equals(buscado)) {
                    encontrado=true;
                    nodos += "cliente" + actual.cliente.hashCode() + "[label=\"" + actual.cliente.Nombre + "\\nID: " + actual.cliente.id + "\\nIMG_Color: " + actual.cliente.color + "\\nIMG_BW: " + actual.cliente.bw + "\\nPasos: " + actual.cliente.pasos + "\\nVentanilla: " + actual.cliente.ventanilla + "\", width=1.5,height=1.5];\n";
                }
                actual = actual.siguiente;
            }
            if (encontrado==false) {
                System.out.println("Cliente no encontrado por el ID");
            }

        }
        dot += nodos + "\n}";
        //System.out.println(dot);
        try {
            FileWriter f = new FileWriter("Reporte4.dot");
            BufferedWriter bufer = new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p = new ProcessBuilder("dot", "-Tpng", "Reporte4.dot", "-o", "Reporte4.png");
            p.redirectErrorStream(true);
            p.start();
            ProcessBuilder p2 = new ProcessBuilder("cmd.exe", "/c", "Reporte4.png");
            p2.redirectErrorStream(true);
            p2.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
