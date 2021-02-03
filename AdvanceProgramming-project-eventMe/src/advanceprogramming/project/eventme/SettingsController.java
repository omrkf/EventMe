/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.DataModel;
import Model.databaseConn;
import com.mysql.jdbc.Connection;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import javafx.scene.image.Image;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Blob;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author okhfl
 */
public class SettingsController implements Initializable {

    private DataModel model = new DataModel();
    @FXML
    private Button buttonofLogout;
    @FXML
    private Button buttonofBack;
    @FXML
    private Button uploadImage;
    @FXML
    private ImageView imageView;
    private PreparedStatement store, retrieve;
    private databaseConn db;

    @FXML
    private Button loadImage;
    @FXML
    private Button UploadCardImage;
    @FXML
    private Button LoadCardImage;
    @FXML
    private ImageView CardImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        db = new databaseConn();

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
        Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void buttonUploadImages(ActionEvent event) throws IOException, SQLException {
        try {
            FileChooser filechooser = new FileChooser();
            File file = filechooser.showOpenDialog(uploadImage.getScene().getWindow());
            String fileName = file.getName();
            FileInputStream fileInputStream = new FileInputStream(file);

            if (fileName.endsWith(".jpg") || fileName.endsWith(".JPG") || fileName.endsWith(".PNG") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".JPEG")) {
                String storeStatement = "update  events set certi_photo=? where evid =?";
                store = db.getConn().prepareStatement(storeStatement);
                filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpg"));
                filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.png"));
                filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpeg"));
                
                store.setBinaryStream(1, fileInputStream, fileInputStream.available());
                store.setInt(2, model.getEv().getEvid());
                store.execute();
                
//            image = new Image(file.toURI().toString());
//            store.setBinaryStream(1, (InputStream) fileInputStream, (int) file.length());
                


//            Image image = new Image(fileInputStream);
//            imageView.setImage(image);
                store.close();

            } else {
                JOptionPane.showMessageDialog(null, "Please Select Image File", "Try Again", 1);
            }
        } catch (Exception e) {
            System.out.println("Image too big");
        }
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
                imageView.setImage(image);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SettingsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void buttonUploadCardImage(ActionEvent event) throws FileNotFoundException, SQLException, IOException {
        try {
            FileChooser filechooser = new FileChooser();
            File file = filechooser.showOpenDialog(UploadCardImage.getScene().getWindow());
            String fileName = file.getName();
            FileInputStream fileInputStream = new FileInputStream(file);

            if (fileName.endsWith(".jpg") || fileName.endsWith(".JPG") || fileName.endsWith(".PNG") || fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".JPEG")) {
                String storeStatement = "update  events set card_photo=? where evid =?";
                store = db.getConn().prepareStatement(storeStatement);
                filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpg"));
                filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.png"));
                filechooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image files", "*.jpeg"));

                store.setBinaryStream(1, fileInputStream, fileInputStream.available());
                store.setInt(2, model.getEv().getEvid());
                store.execute();
                store.close();

            } else {
                JOptionPane.showMessageDialog(null, "Please Select Image File", "Try Again", 1);
            }
        } catch (Exception e) {
            System.out.println("Image too big");
        }

    }

    @FXML
    private void buttonLoadCardImage(ActionEvent event) {
        try {
            String retrieveStatement = "select card_photo from events where evid=?";
            retrieve = db.getConn().prepareStatement(retrieveStatement);
            System.out.println("evid : " + model.getEv().getEvid());
            retrieve.setInt(1, model.getEv().getEvid());
            ResultSet resultset = retrieve.executeQuery();

            if (resultset.first()) {
                Blob blob = resultset.getBlob(1);
                InputStream inputstream = blob.getBinaryStream();

                Image image = new Image(inputstream);
                CardImageView.setImage(image);
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
