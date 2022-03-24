package edd.proyecto_fase2_202010223;

public class ListaClientes {
    public Nodocliente primero;
    
    public void insertar(Cliente cliente){
        Nodocliente nuevo=new Nodocliente(cliente);
        
        if(primero==null){
            primero=nuevo;
        }else{
            Nodocliente actual=primero;
            while(actual.siguiente!=null){
                actual=actual.siguiente;
            }
            actual.siguiente=nuevo;
        }
    }
    
    public void eliminar(int dpi){
        Nodocliente actual=primero;
        Nodocliente anterior=null;
        while(actual!=null&&actual.cliente.getDpi()!=dpi){
            anterior=actual;
            actual=actual.siguiente;
        }
        if(anterior==null&&primero!=null){
            primero=actual.siguiente;
            actual.siguiente=null;
        }else if(actual!=null){
            anterior.siguiente=actual.siguiente;
            actual.siguiente=null;
        }
        
        
    }
    
    public void mostrar(){
        Nodocliente actual=primero;
        while(actual!=null){
            System.out.println(actual.cliente.toString());
            actual=actual.siguiente;
        }
    }
    
    public Cliente logueo(long dpi,String contra){
        Nodocliente actual=primero;
        while(actual!=null){
            if(actual.cliente.getDpi()==dpi&&actual.cliente.getPassword().equalsIgnoreCase(contra)){
                return actual.cliente;
            }
            actual=actual.siguiente;
        }
        return null;
    }
}
