package Main.Controller;

import Main.Model.Datos;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class IngresarID {
    @FXML
    private TextField nombre;
    @FXML
    Label sms;

    private String id;

    public void iniciar(ActionEvent eventbtn) throws IOException {

        if (nombre.getText().isEmpty()) {
            sms.setText("Es necesario agregar un Nombre para Iniciar");
        }else {
            id = nombre.getText();
            System.out.println(id);
            Datos datos = new Datos();
            datos.setNombre(id);
            datos.setIp("localhost");
            datos.setPuerto(3000);
            //btnConectarOnMouseClicked();
            Stage st;
            Parent root = FXMLLoader.load(getClass().getResource("../View/vistaPrincipal.fxml"));

            Scene scene = new Scene(root);

            st = (Stage) ((Node) eventbtn.getSource()).getScene().getWindow();

            st.setScene(scene);
            st.setTitle("WhatsApp 213.221.2");
            st.show();

//            Controller controller = new Controller();
//            controller.btnConectarOnMouseClicked();
        }

    }

}
