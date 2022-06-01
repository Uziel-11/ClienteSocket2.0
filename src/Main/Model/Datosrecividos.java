package Main.Model;

public class Datosrecividos {

    private String id;
    private String sms;

    public Datosrecividos(String id, String sms) {
        this.id = id;
        this.sms = sms;
    }

    public Datosrecividos(){

    }

    public Datosrecividos(String s) {
        this.sms = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }
}
