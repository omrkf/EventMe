/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.Admins;
import Model.DataModel;
import Model.databaseConn;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author okhfl
 */
public class SignInController implements Initializable {

    private boolean isFound = false;
    public Label wrongInput = new Label();
    private DataModel model = new DataModel();
    private Label label;
    private final int EVID = 1;
    @FXML
    private TextField textFofUserName;
    @FXML
    private PasswordField textFofPassword;
    @FXML
    private Button buttonofSignin;
    @FXML
    private Button Pass;
    @FXML
    private Label FailLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        textFofUserName.setPromptText("Username");
        textFofPassword.setPromptText("Password");
    }

    @FXML
    private void buttonofLogin(ActionEvent event) throws IOException, SQLException {
        try {
            // TODO
            model.initModel();
        } catch (SQLException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (model.login(textFofUserName.getText(), textFofPassword.getText())) {

            ((Node) event.getSource()).getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("Home.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
            }
            HomeController home = loader.getController();
            home.getModel().setModel(model);
            Parent root = loader.getRoot();
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } else {
            FailLabel.setText("Username or Password are incorrect!");
            System.out.println("Wrong Input");
        }
    }

    @FXML
    private void pass(ActionEvent event) throws IOException, SQLException {
        try {
            // TODO
            model.initModel();
        } catch (SQLException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
        }
        HomeController home = loader.getController();
        model.setEventByDatabase(EVID);
        model.setUserArrayListByDatabase(model.getConn());
        model.setUnattendUserArrayListByDatabase(model.getConn());
        home.getModel().setModel(model);
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
