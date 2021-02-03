/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asano
 */
public class AttendFormDoctorVersionController implements Initializable {

    private DataModel model = new DataModel();
    @FXML
    private Label labelForName;
    @FXML
    private Label labelForEmail;
    @FXML
    private Label labelForPhone;
    @FXML
    private Button buttonofCancel;
    @FXML
    private Button buttonOfAttend;
    @FXML
    private ListView<Users> eventUsersListView;
    @FXML
    private Label eventName;
    @FXML
    private ListView<Users> attendListView;
    @FXML
    private Button buttonOfUnAttend;
    @FXML
    private TextField SearchOfEventUsers;
    @FXML
    private TextField SearchOfAttended;
    @FXML
    private Button buttonShow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        SearchOfAttended.setPromptText("Search...");
        SearchOfEventUsers.setPromptText("Search...");
    }

    public void init() throws SQLException {
        eventUsersListView.getItems().addAll(model.getUserUnAttendList());
        eventUsersListView.setCellFactory(lv -> new ListCell<Users>() {
            public void updateItem(Users user, boolean empty) {
                super.updateItem(user, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(user.getUid() + ": " + user.getFname() + " "
                            + user.getLname() + " " + user.getEmail() + "  " + user.getPhone());
                }
            }
        });
//        model.setUnattendUserArrayListByDatabase(model.getConn());
        attendListView.getItems().addAll(model.getUserAttendList());
        attendListView.setCellFactory(lv -> new ListCell<Users>() {
            public void updateItem(Users user, boolean empty) {
                super.updateItem(user, empty);
                if (empty) {
                    setText(null);
                } else {
                    setText(user.getUid() + ": " + user.getFname() + " "
                            + user.getLname() + " " + user.getEmail() + "  " + user.getPhone());
                }
            }
        });
    }

    public Label getEventName() {
        return eventName;
    }

    public void setEventName(Label eventName) {
        this.eventName = eventName;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

    @FXML
    private void buttonofCancel(ActionEvent event) throws IOException {
        ((Node) event.getSource()).getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Home.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            Logger.getLogger(AttendController.class.getName()).log(Level.SEVERE, null, e);
        }
        HomeController attFormCont = loader.getController();
        attFormCont.getModel().setModel(model);
        Parent root = loader.getRoot();
        showStage(root);
    }

    @FXML
    private void eventUsersListViewOnClicked(MouseEvent event) {
        if (!eventUsersListView.getSelectionModel().isEmpty()) {
            attendListView.getSelectionModel().clearSelection();
            labelForEmail.setText(eventUsersListView.getSelectionModel().getSelectedItem().getEmail());
            labelForName.setText(eventUsersListView.getSelectionModel().getSelectedItem().getFname() + "  "
                    + eventUsersListView.getSelectionModel().getSelectedItem().getLname());
            labelForPhone.setText(eventUsersListView.getSelectionModel().getSelectedItem().getPhone());
        }
    }

    @FXML
    private void attendListViewOnClicked(MouseEvent event) {
        if (!attendListView.getSelectionModel().isEmpty()) {
            eventUsersListView.getSelectionModel().clearSelection();
            labelForEmail.setText(attendListView.getSelectionModel().getSelectedItem().getEmail());
            labelForName.setText(attendListView.getSelectionModel().getSelectedItem().getFname() + "  "
                    + attendListView.getSelectionModel().getSelectedItem().getLname());
            labelForPhone.setText(attendListView.getSelectionModel().getSelectedItem().getPhone());
        }
    }

    @FXML
    private void buttonOfAttend(ActionEvent event) {
        if (!eventUsersListView.getSelectionModel().isEmpty()) {
            Users u = eventUsersListView.getSelectionModel().getSelectedItem();
            model.getUserAttendList().add(u);
            attendListView.getItems().clear();
            attendListView.getItems().addAll(model.getUserAttendList());

            boolean found = model.getUserUnAttendList().remove(u);
            eventUsersListView.getItems().clear();
            eventUsersListView.getItems().addAll(model.getUserUnAttendList());
            model.setAttendTrue(u);
        }
    }

    @FXML
    private void buttonOfUnattend(ActionEvent event) {
        if (!attendListView.getSelectionModel().isEmpty()) {
            Users u = attendListView.getSelectionModel().getSelectedItem();
            model.getUserUnAttendList().add(u);
            eventUsersListView.getItems().clear();
            eventUsersListView.getItems().addAll(model.getUserUnAttendList());

            model.setAttendFalse(u);
            boolean found = model.getUserAttendList().remove(u);
            attendListView.getItems().clear();
            attendListView.getItems().addAll(model.getUserAttendList());
        }
    }

    private void showStage(Parent root) {
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void SearchOfEventUsersOnPressed(KeyEvent event) {
        if (SearchOfEventUsers.getText().isEmpty()) {
            System.out.println("Search Unattend empty");
            eventUsersListView.getItems().clear();
            eventUsersListView.getItems().addAll(model.getUserUnAttendList());
        } else {
            String search = SearchOfEventUsers.getText();
            for (Users u : model.getUserUnAttendList()) {
                if (searchName(u, search) || searchEmail(u, search)
                        || searchPhone(u, search) || searchUid(u, search)) {
                    model.getSearchUnAttendList().add(u);
                }
            }
            eventUsersListView.getItems().clear();
            eventUsersListView.getItems().addAll(model.getSearchUnAttendList());
            model.getSearchUnAttendList().removeAll(model.getSearchUnAttendList());

        }
    }

    @FXML
    private void SearchOfAttendedOnPressed(KeyEvent event) {
        if (SearchOfAttended.getText().isEmpty()) {
            System.out.println("Search attend empty");
            attendListView.getItems().clear();
            attendListView.getItems().addAll(model.getUserAttendList());
        } else {
            String search = SearchOfAttended.getText();
            for (Users u : model.getUserAttendList()) {
                if (searchName(u, search) || searchEmail(u, search)
                        || searchPhone(u, search) || searchUid(u, search)) {
                    model.getSearchAttendList().add(u);
                }
            }
            attendListView.getItems().clear();
            attendListView.getItems().addAll(model.getSearchAttendList());
            model.getSearchAttendList().removeAll(model.getSearchAttendList());
        }
    }

    public boolean searchName(Users u, String s) {
        return u.getFname().startsWith(s) || u.getLname().startsWith(s);
    }

    public boolean searchEmail(Users u, String s) {
        return u.getEmail().startsWith(s);
    }

    public boolean searchPhone(Users u, String s) {
        return u.getPhone().startsWith(s);
    }

    public boolean searchUid(Users u, String s) {
        return String.valueOf(u.getUid()).startsWith(s);
    }

    @FXML
    private void buttonShow(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SendEmail.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
