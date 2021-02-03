/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author okhfl
 */
public class AttendCardController implements Initializable {

    @FXML
    private Label labelForEventName;
    @FXML
    private Label labelForEventDate;
    @FXML
    private Label labelForName;
    @FXML
    private Button buttonofBack;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     *
     * @param eventName
     * @param eventDate
     * @param name
     */
    public void setText(String eventName, String eventDate, String name) {
        labelForEventDate.setText(eventDate);
        labelForEventName.setText(eventName);
        labelForName.setText(name);
    }

    /**
     *
     * @param event Event class
     * @throws IOException
     */
    @FXML
    private void buttonofBack(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
