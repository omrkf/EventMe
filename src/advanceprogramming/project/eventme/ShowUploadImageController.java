/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.DataModel;
import Model.databaseConn;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author mostafa
 */
public class ShowUploadImageController implements Initializable {

    private DataModel model = new DataModel();

    private databaseConn db;
    private PreparedStatement store, retrieve;
    int evid;
    @FXML
    private ImageView dbImage;
    @FXML
    private Button uploadCertificate;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new databaseConn();
        model = new DataModel();
    }

    @FXML
    private void buttonUploadImages(ActionEvent event) throws IOException, SQLException {

//        try {
        FileChooser filechooser = new FileChooser();
        File file = filechooser.showOpenDialog(uploadCertificate.getScene().getWindow());
        String fileName = file.getName();
        FileInputStream fileInputStream = new FileInputStream(file);

        if (fileName.endsWith(".jpg") || fileName.endsWith(".JPG") || fileName.endsWith(".PNG") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".JPEG")) {
            String storeStatement = "update  events set certi_photo=? where evid =?";
            store = db.getConn().prepareStatement(storeStatement);
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpg"));
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.png"));
            filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpeg"));

            store.setBinaryStream(1, fileInputStream, fileInputStream.available());
            store.setInt(2, evid);
            store.execute();

//            image = new Image(file.toURI().toString());
//            store.setBinaryStream(1, (InputStream) fileInputStream, (int) file.length());
//            Image image = new Image(fileInputStream);
//            imageView.setImage(image);
            store.close();

        } else {
            JOptionPane.showMessageDialog(null, "Please Select Image File", "Try Again", 1);
        }
//        } catch (Exception e) {
//            System.out.println("Image too big");
//        }
    }

    public void setEvid(int ev) {
        System.out.println("aasfefaefe");
        evid = ev;
    }

    @FXML
    private void buttonLoadImage(ActionEvent event) {
        try {
            String retrieveStatement = "select certi_photo from events where evid=?";
            retrieve = db.getConn().prepareStatement(retrieveStatement);
            System.out.println("evid : " + model.getEv().getEvid());
            retrieve.setInt(1, model.getEv().getEvid());
            ResultSet resultset = retrieve.executeQuery();

            if (resultset.first()) {
                Blob blob = resultset.getBlob(1);
                InputStream inputstream = blob.getBinaryStream();

                Image image = new Image(inputstream);
                dbImage.setImage(image);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }

}
