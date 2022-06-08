package Main.Controller;


import Main.Model.DatosConexion;
import Main.Model.ThreadClient;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements  Initializable {
    private Socket socket;
    private DataOutputStream bufferDeSalida = null;

    @FXML
    private TextArea textArea;

    @FXML
    private ComboBox<String> usuariosConectados;
    @FXML
    private Label conexion;

    @FXML
    private TextField txtEnviar;


    @FXML
    void conectar_al_Servidor() {
        DatosConexion datos = new DatosConexion();
        try {
            socket = new Socket(datos.getIp(), datos.getPuerto());
            bufferDeSalida = new DataOutputStream(socket.getOutputStream());
            bufferDeSalida.writeUTF(datos.getNombre());
            bufferDeSalida.flush();

            System.out.println(datos.getNombre()+"  "+ datos.getIp());
            ThreadClient cliente = new ThreadClient(socket, conexion, textArea, usuariosConectados);
            new Thread(cliente).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void enviarMensaje() {
        DatosConexion datos = new DatosConexion();
        if (usuariosConectados.getValue() == null){
            System.out.println("Seleccione a un destinatario");
        }else{
            try {
                bufferDeSalida.writeUTF(datos.getNombre()+":"+ usuariosConectados.getValue()+":"+txtEnviar.getText());
                ThreadClient sms = new ThreadClient();
                sms.mostrarMensajes("Tu: "+txtEnviar.getText(), usuariosConectados.getValue());
                bufferDeSalida.flush();
                txtEnviar.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        conectar_al_Servidor();
    }


}
