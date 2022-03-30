package edd.proyecto_fase2_202010223;
public class Imagen {
    public Matriz Mimagen;
    public int numero;
    public ABB capas;

    public Imagen(int numero) {
        this.numero = numero;
        this.Mimagen=new Matriz("IMAGEN" + numero);
        this.capas=new ABB();
    }
    
    public void agregarcapa(Capa capa){
        if(capa!=null){
        capas.insertar(capa);
        }
    }
    
}
