/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase2_202010223;

/**
 *
 * @author Alex Rose
 */
public class RamaB {

    boolean hoja;
    int contador;
    NodoB primero;
    int nivel;

    public RamaB() {
        this.primero = null;
        this.hoja = true;
        this.contador = 0;
        this.nivel=0;
    }

    public void insertar(NodoB nuevo) {
        if (primero == null) {
            primero = nuevo;
            contador++;
        } else {
            NodoB aux = primero;
            while (aux != null) {
                if (aux.cliente.getDpi() == nuevo.cliente.getDpi()) {
                    break;
                } else {
                    if (aux.cliente.getDpi() > nuevo.cliente.getDpi()) {
                        if (aux == primero) {
                            aux.anterior = nuevo;
                            nuevo.siguiente = aux;
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;
                            primero = nuevo;
                            contador++;
                            break;
                        } else {
                            nuevo.siguiente = aux;
                            aux.izquierda = nuevo.derecha;
                            nuevo.derecha = null;

                            nuevo.anterior = aux.anterior;
                            aux.anterior.siguiente = nuevo;
                            aux.anterior = nuevo;
                            contador++;
                            break;
                        }
                    } else if (aux.siguiente == null) {
                        aux.siguiente = nuevo;
                        nuevo.anterior = aux;
                        contador++;
                        break;
                    }
                }
                aux = aux.siguiente;
            }
        }
    }

}
