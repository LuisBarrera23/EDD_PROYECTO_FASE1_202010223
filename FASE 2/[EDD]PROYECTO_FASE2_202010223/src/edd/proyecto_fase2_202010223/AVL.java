package edd.proyecto_fase2_202010223;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author Luis Barrera
 */
class NodoAVL {

    Imagen imagen;
    NodoAVL izquierda;
    NodoAVL derecha;
    int altura;

    NodoAVL(Imagen imagen) {
        this.imagen = imagen;
        this.izquierda = null;
        this.derecha = null;
        this.altura = 1;
    }
}

public class AVL {

    NodoAVL raiz = null;
    String estructura;
    Imagen buscada = null;
    Listaimagenes imagenes;

    void insertar(Imagen img) {
        raiz = insertar(raiz, img);
    }

    NodoAVL insertar(NodoAVL temp, Imagen img) {
        if (temp == null) {
            return (new NodoAVL(img));
        } else if (img.numero < temp.imagen.numero) {
            temp.izquierda = insertar(temp.izquierda, img);
        } else if (img.numero > temp.imagen.numero) {
            temp.derecha = insertar(temp.derecha, img);
        } else {
            return temp;
        }

        temp.altura = maximo(altura(temp.izquierda), altura(temp.derecha)) + 1;
        int factorequilibrio = factorEquilibrio(temp);

        if (factorequilibrio > 1 && img.numero < temp.izquierda.imagen.numero) {
            return srr(temp); // se realiza una simple rotation right o rotacion simple a la derecha
        }

        if (factorequilibrio < -1 && img.numero > temp.derecha.imagen.numero) {
            return srl(temp); // se realiza una simple rotation left o rotacion simple a la izquierda
        }

        if (factorequilibrio > 1 && img.numero > temp.izquierda.imagen.numero) {
            temp.izquierda = srl(temp.izquierda);
            return srr(temp);
        }
        if (factorequilibrio < -1 && img.numero < temp.derecha.imagen.numero) {
            temp.derecha = srr(temp.derecha);
            return srl(temp);
        }
        return temp;
    }

    NodoAVL srr(NodoAVL temp) {
        NodoAVL nuevo = temp.izquierda;
        NodoAVL aux = nuevo.derecha;
        nuevo.derecha = temp;
        temp.izquierda = aux;
        temp.altura = maximo(altura(temp.izquierda), altura(temp.derecha)) + 1;
        nuevo.altura = maximo(altura(nuevo.izquierda), altura(nuevo.derecha)) + 1;
        return nuevo;
    }

    NodoAVL srl(NodoAVL temp) {
        NodoAVL nuevo = temp.derecha;
        NodoAVL aux = nuevo.izquierda;
        nuevo.izquierda = temp;
        temp.derecha = aux;
        temp.altura = maximo(altura(temp.izquierda), altura(temp.derecha)) + 1;
        nuevo.altura = maximo(altura(nuevo.izquierda), altura(nuevo.derecha)) + 1;
        return nuevo;
    }

    int factorEquilibrio(NodoAVL temp) {
        if (temp == null) {
            return 0;
        }
        return altura(temp.izquierda) - altura(temp.derecha);
    }

    int altura(NodoAVL temp) {
        if (temp == null) {
            return 0;
        }
        return temp.altura;
    }

    private int maximo(int valor1, int valor2) {
        if (valor1 > valor2) {
            return valor1;
        } else {
            return valor2;
        }
    }

    void preorder(NodoAVL temp) {
        if (temp != null) {
            System.out.print(temp.imagen.numero + " ");
            preorder(temp.izquierda);
            preorder(temp.derecha);
        }
    }

    void enorder(NodoAVL temp) {
        if (temp != null) {
            enorder(temp.izquierda);
            System.out.print(temp.imagen.numero + " ");
            enorder(temp.derecha);
        }
    }

    void postorder(NodoAVL temp) {
        if (temp != null) {
            postorder(temp.izquierda);
            postorder(temp.derecha);
            System.out.print(temp.imagen.numero + " ");
        }
    }

    void modificado(NodoAVL temp) {
        if (temp != null) {
            if (temp.izquierda != null) {
                estructura += temp.imagen.numero + "->" + temp.izquierda.imagen.numero + "\n";
            }
            if (temp.derecha != null) {
                estructura += temp.imagen.numero + "->" + temp.derecha.imagen.numero + "\n";
            }
            modificado(temp.izquierda);
            modificado(temp.derecha);
        }
    }

    String graficar() {
        estructura = "";
        modificado(raiz);
        String dot = "digraph G{\nnode [shape=circle,color=dodgerblue1,style = filled];\n";

        dot += estructura + "\n}";
        //System.out.println(dot);
        return dot;

    }

    public void comboimagenes(NodoAVL temp, Vcliente vent) {
        if (temp != null) {
            vent.ComboImagenes.addItem(String.valueOf(temp.imagen.numero));
            comboimagenes(temp.izquierda, vent);
            comboimagenes(temp.derecha, vent);
        }
    }

    void buscar(NodoAVL temp, int n) {
        if (temp != null) {
            buscar(temp.izquierda, n);
            buscar(temp.derecha, n);
            if (temp.imagen.numero == n) {
                buscada = temp.imagen;
            }
        }
    }

    void graficarimagen(int num) {
        buscada = null;
        buscar(raiz, num);
        if (buscada != null) {
            buscada.Mimagen.graficar_imagen();
        }
    }

    Imagen buscar_retornar(int numero) {
        buscada = null;
        buscar(raiz, numero);
        return buscada;
    }

    public void eliminar(Imagen img) {
        raiz = eliminar(raiz, img);
    }

    public NodoAVL eliminar(NodoAVL temp, Imagen img) {
        if (temp == null) {
            return temp;
        }

        if (img.numero < temp.imagen.numero) {
            temp.izquierda = eliminar(temp.izquierda, img);
        } else if (img.numero > temp.imagen.numero) {
            temp.derecha = eliminar(temp.derecha, img);
        } else {
            if ((temp.izquierda == null) || (temp.derecha == null)) {
                NodoAVL aux = null;
                if (aux == temp.izquierda) {
                    aux = temp.derecha;
                } else {
                    aux = temp.izquierda;
                }
                if (aux == null) {
                    temp = null;
                } else {
                    temp = aux;
                }
            } else {
                NodoAVL aux = nodomaximo(temp.izquierda);
                temp.imagen = aux.imagen;
                temp.izquierda = eliminar(temp.izquierda, aux.imagen);
            }
        }
        if (temp == null) {
            return temp;
        }
        temp.altura = maximo(altura(temp.izquierda), altura(temp.derecha)) + 1;

        int factorEquilibrio = factorEquilibrio(temp);

        if (factorEquilibrio > 1 && factorEquilibrio(temp.izquierda) >= 0) {
            return srr(temp);
        }
        if (factorEquilibrio < -1 && factorEquilibrio(temp.derecha) <= 0) {
            return srl(temp);
        }
        if (factorEquilibrio > 1 && factorEquilibrio(temp.izquierda) < 0) {
            temp.izquierda = srl(temp.izquierda);
            return srr(temp);
        }
        if (factorEquilibrio < -1 && factorEquilibrio(temp.derecha) > 0) {
            temp.derecha = srr(temp.derecha);
            return srl(temp);
        }

        return temp;
    }

    public NodoAVL nodomaximo(NodoAVL temp) {
        NodoAVL actual = temp;
        while (actual.derecha != null) {
            actual = actual.derecha;
        }
        return actual;
    }

    public void TopImagenesCapas(Vcliente vent) {
        imagenes = new Listaimagenes();
        generarlista(raiz);
        imagenes.topcapas();
        String dot="digraph G{\nnode [shape=plaintext ];\nlabel=\"Top imagenes con mas capas\";\n";
        String nodos1="";
        nodos1+="Nodounico[ label=<<table border=\"0\" cellborder=\"1\" cellspacing=\"0\" width=\"100%\" height=\"100%\">\n";
        
        NodoImg actual=imagenes.primero;
        int iterador=0;
        while(actual!=null&&iterador!=5){
            nodos1+="<tr>\n";
            nodos1+="<td >No. "+String.valueOf(iterador+1)+"</td>\n";
            nodos1+="<td >Imagen "+actual.imagen.numero+"</td>\n";
            nodos1+="<td >Capas: "+actual.imagen.capas.totalcapas()+"</td>\n";
            nodos1+="</tr>\n";
            actual=actual.siguiente;
            iterador++;
        }
        nodos1+="</table>>];\n";
        dot+=nodos1+"\n}";
        //System.out.println(dot);
        try{
            FileWriter f=new FileWriter("grafico.dot");
            BufferedWriter bufer=new BufferedWriter(f);
            bufer.write(dot);
            bufer.close();
            ProcessBuilder p=new ProcessBuilder("dot","-Tpng","grafico.dot","-o","reporte.png");
            p.redirectErrorStream(true);
            p.start();
            java.util.concurrent.TimeUnit.SECONDS.sleep(1);
            File archivo = new File("reporte.png");
            BufferedImage buffer = ImageIO.read(archivo);
            ImageIcon imagen = new ImageIcon(buffer);
            Icon icono = new ImageIcon(imagen.getImage());
            vent.visualizador.setIcon(null);
            vent.visualizador.setIcon(icono);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    void generarlista(NodoAVL temp) {
        if (temp != null) {
            imagenes.insertar(temp.imagen);
            generarlista(temp.izquierda);
            generarlista(temp.derecha);
        }
    }

}
