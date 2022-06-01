package Main.Model;

public class Datos {

    static private int puerto;
    static private String ip;
    static private String nombre;

    public Datos(){

    }

    public Datos(int puerto, String ip, String nombre) {
        this.puerto = puerto;
        this.ip = ip;
        this.nombre = nombre;
    }


    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
