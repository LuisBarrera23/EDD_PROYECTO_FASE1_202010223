package edd.proyecto_fase2_202010223;
public class EDDPROYECTO_FASE2_202010223 {
    static Matriz m=new Matriz("CAPA2");
    public static void main(String[] args) {
        //Login ventana= new Login();
        //ventana.setVisible(true);
        Cliente cl=new Cliente();
        cl.setVisible(true);
        m.mostrarmatriz();
        m.insertarNodo(4, 5, "black");
        m.insertarNodo(4, 6, "red");
        m.insertarNodo(3, 5, "red");
        m.insertarNodo(3, 5, "yellow");
        m.insertarNodo(3, 5, "black");
        m.mostrarmatriz();
        //m.graficardot_matriz();
        m.graficar_matriz();
    }
    
}
