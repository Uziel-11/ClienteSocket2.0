package Main.Controller;


import Main.Model.Datos;
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
    private ComboBox<String> usuarios;
    @FXML
    private Label conexion;

    @FXML
    private TextField txtEnviar;


    @FXML
    void btnConectarOnMouseClicked() {
        Datos datos = new Datos();
        try {
            socket = new Socket(datos.getIp(), datos.getPuerto());
            bufferDeSalida = new DataOutputStream(socket.getOutputStream());
            bufferDeSalida.writeUTF(datos.getNombre());
            bufferDeSalida.flush();

            System.out.println(datos.getNombre()+"  "+ datos.getIp());
            ThreadClient cliente = new ThreadClient(socket, conexion, textArea, usuarios);
            new Thread(cliente).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void btnEnviarOnMouseClicked() {
        Datos datos = new Datos();
        if (usuarios.getValue() == null){
            System.out.println("Seleccione a un destinatario");
        }else{
            try {
                bufferDeSalida.writeUTF(datos.getNombre()+":"+usuarios.getValue()+":"+txtEnviar.getText());
                ThreadClient sms = new ThreadClient();
                sms.misms("Tu: "+txtEnviar.getText(), usuarios.getValue());
                bufferDeSalida.flush();
                txtEnviar.clear();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnConectarOnMouseClicked();
    }


}
