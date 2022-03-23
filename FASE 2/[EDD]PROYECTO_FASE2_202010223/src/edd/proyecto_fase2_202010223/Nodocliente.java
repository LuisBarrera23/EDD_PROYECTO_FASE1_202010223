package edd.proyecto_fase2_202010223;

/**
 *
 * @author angel
 */
public class Nodocliente {
    public Cliente cliente;
    public Nodocliente siguiente=null;
    public Nodocliente(Cliente cliente){
        this.cliente=cliente;
    }
}
