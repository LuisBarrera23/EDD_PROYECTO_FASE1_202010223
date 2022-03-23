package edd.proyecto_fase2_202010223;

public class Cliente {
    private String dpi;
    private String nombre;
    private String password;

    public Cliente(String dpi, String nombre, String password) {
        this.dpi = dpi;
        this.nombre = nombre;
        this.password = password;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
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
