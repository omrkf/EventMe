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
import java.sql.Statement;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author okhfl
 */
public class AttendController implements Initializable {

    private Model.databaseConn db;
    private Statement stm;
    private ResultSet rs;
    private boolean isFound = false;
    private int checkCode = 0;

    @FXML
    private TextField textFofEnterYourCode;
    @FXML
    private Button buttonofBack;
    @FXML
    private Button buttonofAttendinAttend;
    @FXML
    private Button buttonofPrintinAttend;
    @FXML
    private Button buttonofLogoutinAttend;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        textFofEnterYourCode.setPromptText("Code of Event: ");
        db = new databaseConn();
        try {
            stm = db.getConn().createStatement();
        } catch (SQLException ex) {
            System.out.println("Create Statement Exeption");
            System.out.println(ex);
        }

    }

    @FXML
    private void buttonofAttendinAttend(ActionEvent event) throws IOException, SQLException {

        rs = stm.executeQuery("select * from users");
        try {
            while (rs.next()) {
                if (rs.getInt("uid") == Integer.parseInt(textFofEnterYourCode.getText())) {
                    ((Node) event.getSource()).getScene().getWindow().hide();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("AttendForm.fxml"));
                    try {
                        loader.load();
                    } catch (IOException e) {
                        Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
                    }
                    AttendFormController attFormCont = loader.getController();
                    attFormCont.setText(rs.getString("fname") + "  " + rs.getString("lname"),
                            rs.getString("email"),
                            rs.getInt("phone"),
                            rs.getInt("uid"));
                    Parent root = loader.getRoot();
                    showStage(root);
                    isFound = true;
                    checkCode = 1;
                    break;
                }
            }
        } catch (Exception e) {
            System.out.println("while catch");
            System.out.println(e);
        }

        if (!isFound) {

            //if (textFofEnterYourCode.getText().contains("[a-zA-Z]+"))   
            if (textFofEnterYourCode.getText().matches("[0-9]+")) {

                System.out.println("Wrong Input");
                showDialog(textFofEnterYourCode.getText() + " Is wrong code", "Please check the code again", "Wrong");
            } else {
                showDialog("Please enter code number", null, "Wrong");
                System.out.println("Wrong Input");

            }
        }
    }

    private void showStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML

    private void showDialog(String info, String header, String title) {
        if (checkCode == 1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText(info);
            alert.setHeaderText(header);
            alert.setTitle(title);
            alert.showAndWait();
        } else {

            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText(info);
            alert1.setHeaderText(header);
            alert1.setTitle(title);
            alert1.showAndWait();

        }
    }

    @FXML
    private void buttonofBack(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void buttonofPrintinAttend(ActionEvent event) {
    }

    @FXML
    private void buttonofLogout(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("Sign-IN.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
