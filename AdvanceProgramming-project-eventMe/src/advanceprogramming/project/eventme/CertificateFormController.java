/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.databaseConn;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
public class CertificateFormController implements Initializable {
    databaseConn db;
    private int id;
    @FXML
    private Button buttonofCancel;
    @FXML
    private Button buttonOfOk;
    @FXML
    private Label labelForName;
    @FXML
    private Label labelForEmail;
    @FXML
    private Label labelForPhone;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db = new databaseConn();
    }

    @FXML
    private void buttonofCancel(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Certificate.fxml"));
        showStage(root);
    }

    @FXML
    private void buttonOfOk(ActionEvent event) throws IOException, SQLException {
//    ((Node) event.getSource()).getScene().getWindow().hide();
//        db.setRs(db.getStm().executeQuery("select * from events"));
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("CertificateCard"));
//        try {
//            loader.load();
//        } catch (Exception e) {
//        }
        
        Parent root = FXMLLoader.load(getClass().getResource("CertificateCard.fxml"));
        showStage(root);
    }
    
    public void setText(String name , String email ,int phone,int id){
        labelForEmail.setText(email);
        labelForName.setText(name);
        labelForPhone.setText(String.valueOf(phone));
        this.id = id;
    }
    
    private void showStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
