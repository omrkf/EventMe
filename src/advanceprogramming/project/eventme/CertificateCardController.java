/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author okhfl
 */
public class CertificateCardController implements Initializable {

    @FXML
    private Label usernameLabel;
    @FXML
    private Label eventStartLabel;
    @FXML
    private Label courseCodeLabel;
    int userID;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     *
     * @param eventName
     * @param eventDate
     * @param evid
     */
    public void setCertiText(String eventName, String eventDate, int evid) {
        eventStartLabel.setText(eventDate);
        usernameLabel.setText(eventName);
        courseCodeLabel.setText(String.valueOf(evid));
    }

}
