package edd.proyecto_fase2_202010223;
public class EDDPROYECTO_FASE2_202010223 {
    static ListaClientes clientes=new ListaClientes();
    public static void main(String[] args) {
        Login ventana= new Login();
        ventana.setVisible(true);
        Cliente unico=new Cliente(202010223,"Luis Barrera","prueba");
        clientes.insertar(unico);
    }
    
}
