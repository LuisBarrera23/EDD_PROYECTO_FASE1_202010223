package edd.proyecto_fase2_202010223;

public class Cliente {
    public ABB capas;
    private long dpi;
    private String nombre;
    private String password;

    public Cliente(long dpi, String nombre, String password) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.password = password;
        this.capas=new ABB();
    }

    public long getDpi() {
        return dpi;
    }

    public void setDpi(long dpi) {
        this.dpi = dpi;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Cliente{" + "dpi=" + dpi + ", nombre=" + nombre + ", password=" + password + '}';
    }
    
    
}
