/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edd.proyecto_fase2_202010223;

/**
 *
 * @author angel
 */

class NodoCR {
    public Capa capa;
    public NodoCR siguiente=null;
    public NodoCR(Capa capa){
        this.capa=capa;
    }
}
public class ListaCapas {
    NodoCR primero;
    public void insertar(Capa capa){
        NodoCR nuevo=new NodoCR(capa);
        nuevo.siguiente=primero;
        primero=nuevo;
    }
    
    public void mostrar(){
        NodoCR actual=primero;
        while(actual!=null){
            System.out.print(actual.capa.numero+" ");
            actual=actual.siguiente;
        }
        System.out.println(" ");
    }
    
}
