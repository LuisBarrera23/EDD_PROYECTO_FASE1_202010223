package edd.proyecto_fase2_202010223;

/**
 *
 * @author angel
 */
public class NodoB {
    public Cliente cliente;
    public RamaB derecha=null;
    public RamaB izquierda=null;
    public NodoB siguiente=null;
    public NodoB anterior=null;
    
    public NodoB(Cliente cliente){
        this.cliente=cliente;
    }
}
