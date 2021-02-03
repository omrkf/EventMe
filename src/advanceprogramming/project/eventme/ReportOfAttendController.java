/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.DataModel;
import Model.Users;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author okhfl
 */
public class ReportOfAttendController implements Initializable {

    DataModel model = new DataModel();
    @FXML
    private Button buttonofLogout;
    @FXML
    private Button buttonofBack;
    @FXML
    private TableView<Users> table;
    @FXML
    private TableColumn<Users, Integer> col_id;
    @FXML
    private TableColumn<Users, String> col_name;
    @FXML
    private TableColumn<Users, String> col_email;
    @FXML
    private TableColumn<Users, String> col_phone;
    @FXML
    private Label labelAttend;
    @FXML
    private Label labelRegister;

    ObservableList<Users> userList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void init() throws SQLException {

        System.out.println("init report");
        col_id.setCellValueFactory(new PropertyValueFactory<>("uid"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("fname"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        userList = model.attendReport(userList);
        table.setItems(userList);
        labelAttend.setText(String.valueOf(model.countAttend()));
        labelRegister.setText(String.valueOf(model.countRegister()));
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

    @FXML
    private void buttonofBack(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("report.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
        }
        ReportController attFormCont = loader.getController();
        attFormCont.getModel().setModel(model);
        Parent root = loader.getRoot();
        showStage(root);
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
