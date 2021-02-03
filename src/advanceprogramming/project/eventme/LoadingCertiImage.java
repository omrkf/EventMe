/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advanceprogramming.project.eventme;

import Model.DataModel;
import Model.databaseConn;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

/**
 *
 * @author mostafa
 */
public class LoadingCertiImage {

    DataModel model;

    private Pane view;

    public LoadingCertiImage(DataModel d) {
        model = d;
    }

    public LoadingCertiImage() {

    }

    public Pane getPage(String fileName) throws FileNotFoundException, IOException {
        FXMLLoader loader = new FXMLLoader();
        System.out.println("99999999999999");

//        try {
        URL fileUrl = ShowUploadImageController.class.getResource(fileName + ".fxml");
        loader.setLocation(fileUrl);
        System.out.println("00000000000");

        if (fileUrl == null) {
            throw new java.io.FileNotFoundException("FXML file can not be found");
        }
        System.out.println("111111111");
        view = loader.load(fileUrl);
        System.out.println("2222222222222");
        ShowUploadImageController settFromCont = loader.getController();
        System.out.println("wwwwwwwwwwwwww");
        settFromCont.setEvid(model.getEv().getEvid());
        System.out.println("evid loading : " + model.getEv());
//            settFromCont.getModel().setModel(model);
        System.out.println("ffffffffffffff");
//        } catch (Exception e) {
//            System.out.println("No page: " + fileName + " please check FXMLLoader.");
//        }
        return view;
    }

    public DataModel getModel() {
        return model;
    }

    public void setModel(DataModel model) {
        this.model = model;
    }
}
