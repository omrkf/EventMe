/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.databaseConn;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class AttendFormController implements Initializable {

    Model.databaseConn db;
    ResultSet rs;
    public int id;
    @FXML
    private Button buttonofCancel;
    @FXML
    private Label labelForName;
    @FXML
    private Label labelForEmail;
    @FXML
    private Label labelForPhone;
    @FXML
    private Button buttonOfOk;

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
        Parent root = FXMLLoader.load(getClass().getResource("Attend.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void setText(String name, String email, int phone, int id) {
        labelForName.setText(name);
        labelForEmail.setText(email);
        labelForPhone.setText(String.valueOf(phone));
        this.id = id;
    }

    @FXML
    private void buttonOfOk(ActionEvent event) throws IOException, SQLException {
//        ((Node) event.getSource()).getScene().getWindow().hide();
//        Parent root = FXMLLoader.load(getClass().getResource("AttendCard.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage();
//        stage.setScene(scene);
//        stage.show();
System.out.println(this.id);
        rs = db.getStm().executeQuery("select * from events " );
        while(rs.next()){
            if(rs.getInt("evid")==this.id){
                System.out.println(rs.getString("evname"));        
                System.out.println(rs.getDate("evdate_start"));        
                System.out.println(rs.getDate("evdate_end"));        
        System.out.println(labelForName.getText());
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AttendCard.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
        }
        AttendCardController attCardCont = loader.getController();
        attCardCont.setText(rs.getString("evname"),
                rs.getDate("evdate_start") + "-" + rs.getDate("evdate_end"), labelForName.getText());
        Parent root = loader.getRoot();
        showStage(root);
            }
        }

    }
private void showStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}

