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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author okhfl
 */
public class CertificateController implements Initializable {
    databaseConn db ;
    ResultSet rs;
    @FXML
    private TextField textFofEnterYourCode;
    @FXML
    private Button buttonofAttendinAttend;
    @FXML
    private Button buttonofLogout;
    @FXML
    private Button buttonofBack;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        db =new databaseConn();
    }

    @FXML
    private void buttonofSendinCert(ActionEvent event) throws IOException, SQLException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        rs = db.getStm().executeQuery("select * from users");
        while(rs.next()){
            if(rs.getInt("uid")==Integer.parseInt(textFofEnterYourCode.getText())){   
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("CertificateForm.fxml"));
        try {
            loader.load();
        } catch (Exception e) {
        }
                System.out.println("inside if");
        CertificateFormController cerFormCont = loader.getController();
        cerFormCont.setText(rs.getString("fname")+"  "+rs.getString("lname")
                ,rs.getString("email"), rs.getInt("phone"),rs.getInt("uid"));
        Parent root = loader.getRoot();
        showStage(root);
            }
        }
    }

    @FXML
    private void buttonofLogout(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Sign-IN.fxml"));
        showStage(root);
    }

    @FXML
    private void buttonofBack(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
        showStage(root);
    }

    
    private void showStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
