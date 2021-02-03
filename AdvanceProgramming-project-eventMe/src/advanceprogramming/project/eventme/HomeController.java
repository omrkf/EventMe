/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.DataModel;
import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author okhfl
 */
public class HomeController implements Initializable {

    DataModel model = new DataModel();
    @FXML
    private Button buttonofAttend;
    @FXML
    private Button buttonofCertificate;
    @FXML
    private Button buttonofReport;
    @FXML
    private Button buttonofSetting;
    @FXML
    private Button buttonofLogout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void buttonofAttend(ActionEvent event) throws IOException, SQLException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("Attend.fxml"));
        loader.setLocation(getClass().getResource("AttendFormDoctorVersion.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
        }
        AttendFormDoctorVersionController attFormCont = loader.getController();
        attFormCont.getEventName().setText(model.getEv().getEvName());
        attFormCont.getModel().setModel(model);
        attFormCont.init();
        Parent root = loader.getRoot();
        showStage(root);
    }

    @FXML
    private void buttonofCertificate(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Certificate.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonofReport(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("report.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonofSetting(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("settings.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
        }
        SettingsController attFormCont = loader.getController();
        attFormCont.getModel().setModel(model);
        Parent root = loader.getRoot();
        showStage(root);
    }

    @FXML
    private void buttonofLogout(ActionEvent event) throws IOException {
        System.out.println("bbbbbbbbb  " + model.getEv().getEvid());
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Sign-IN.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    private void showStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
