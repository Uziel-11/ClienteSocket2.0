
package Main.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;
import java.util.Observable;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadClient extends Observable implements Runnable {
    private Socket socket;
    private DataInputStream bufferDeEntrada = null;
    private Label log;
    private TextArea textArea;
    private ComboBox<String> conectados;
    Datosrecividos dat = new Datosrecividos();
    ObservableList<String> obd = FXCollections.observableArrayList();
    ObservableList<Datosrecividos> list = FXCollections.observableArrayList();
    boolean aux = false;

    public ThreadClient(Socket socket, Label log, TextArea mens, ComboBox conectados) {
        this.socket = socket;
        this.log = log;
        this.textArea = mens;
        this.conectados = conectados;
    }

    public ThreadClient(){}

    public void run() {

        try {
            bufferDeEntrada = new DataInputStream(socket.getInputStream());

            String st = "";
            do {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextLong(1000L)+100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    st = bufferDeEntrada.readUTF();

                    String[] array = st.split(":");

                    if (array[0].equals("Conectado")){
                        DatosConexion datos = new DatosConexion();
                        if (array[2].equals(datos.getNombre())){
                            System.out.println(array[1]);
                            dat.setId(datos.getNombre());
                            dat.setMensaje(array[1]);
                            list.add(dat);
                            textArea.setText(list.get(0).getMensaje());
                            obd.clear();
                            for (int i =4; i < Arrays.stream(array).count(); i++){
                                if (datos.getNombre().equals(array[i])){
                                }else{
                                    obd.add(array[i]);
                                    conectados.setItems(obd);
                                }
                            }

                        }else {
                            System.out.println(array[2]);
                            String mensaje = list.get(0).getMensaje();
                            dat.setId(datos.getNombre());
                            dat.setMensaje(mensaje+"\n"+"Se a Conectado: "+array[2]);
                            list.add(dat);
                            textArea.setText(list.get(0).getMensaje());
                            obd.clear();
                            for (int i =4; i < Arrays.stream(array).count(); i++){
                                if (datos.getNombre().equals(array[i])){
                                }else{
                                    obd.add(array[i]);
                                    conectados.setItems(obd);
                                }
                            }
                        }
                    }else {
                        System.out.println("Insertando en su respectiva celda --> "+st);
                        for (int i=0; i < list.size(); i++) {
                            if (list.get(i).getId().equals(array[0])) {
                                String mensaje = list.get(i).getMensaje() + "\n" + array[0] + ": " + array[2];
                                dat.setId(array[0]);
                                dat.setMensaje(mensaje);
                                list.add(i, dat);
                                textArea.setText(list.get(i).getMensaje());
                                aux = true;
                                i = list.size();
                            }

                            if (aux == false){
                                dat.setId(array[0]);
                                dat.setMensaje(array[0]+": "+array[2]);
                                list.add(dat);
                                textArea.setText(list.get(i).getMensaje());
                                aux = true;
                                i = list.size();
                            }
                        }
                    }
                    this.setChanged();
                    this.notifyObservers(st);

                } catch (IOException e) {
                    //e.printStackTrace();
                }
            }while (!st.equals("FIN"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void mostrarMensajes(String miMensaje, String usuario){
        for (int i=0; i<list.size(); i++){
            if (list.get(i).getId().equals(usuario)){
                String sms = list.get(i).getMensaje()+"\n"+miMensaje;
                dat.setId(usuario);
                dat.setMensaje(sms);
                list.add(i, dat);
                textArea.setText(list.get(i).getMensaje());
                i = list.size();
            }
        }

    }
}
