/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.DataModel;
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
public class CertificateFormController implements Initializable {
    DataModel model = new DataModel();

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }
    databaseConn db;
    private int id;
    ResultSet rs, rs1;

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
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Certificate.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
        }
        CertificateController attFormCont = loader.getController();
        attFormCont.getModel().setModel(model);
        Parent root = loader.getRoot();
        showStage(root);
    }

    @FXML
    private void buttonOfOk(ActionEvent event) throws IOException, SQLException {

        rs = db.getStm().executeQuery("select * from users u ,attendees a ,events e where a.evid = e.evid and e.evid ="+model.getEv().getEvid()+" and u.uid = a.uid and u.uid = " + id);
        while (rs.next()) {

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("CertificateCard.fxml"));
            try {
                loader.load();
            } catch (Exception e) {
            }
            System.out.println("inside if");
            CertificateCardController cerFormCont = loader.getController();
            cerFormCont.setCertiText(rs.getString("fname") + "  " + rs.getString("lname"),
                    rs.getString("evdate_start"), rs.getInt("evid"));
            Parent root = loader.getRoot();
            showStage(root);

        }

    }

    public void setText(String name, String email, int phone, int id) {
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
