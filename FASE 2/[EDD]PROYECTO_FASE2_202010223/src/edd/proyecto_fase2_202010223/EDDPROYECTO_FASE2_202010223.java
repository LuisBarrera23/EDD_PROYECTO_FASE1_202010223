package edd.proyecto_fase2_202010223;
public class EDDPROYECTO_FASE2_202010223 {

    public static void main(String[] args) {
        //Login ventana= new Login();
        //ventana.setVisible(true);
        Matriz m=new Matriz("prueba");
        m.mostrarmatriz();
        System.out.println("gg");
        m.insertarNodo(4, 5, "negro");
        m.insertarNodo(4, 6, "celeste");
        m.mostrarmatriz();
    }
    
}
