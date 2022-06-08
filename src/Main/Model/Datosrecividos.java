package Main.Model;

public class Datosrecividos {

    private String id;
    private String mensaje;

    public Datosrecividos(String id, String sms) {
        this.id = id;
        this.mensaje = sms;
    }

    public Datosrecividos(){

    }

    public Datosrecividos(String s) {
        this.mensaje = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
