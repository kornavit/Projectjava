package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import java.io.IOException;
import com.github.saacsos.FXRouter;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.models.modelAbout.AboutNisitHardCode;
import ku.cs.models.modelAbout.modelAbout;
import ku.cs.models.modelAbout.modelAboutList;

public class AboutController {
    @FXML
    private ListView<modelAbout> Auditorlist;
    @FXML private Label nameLabel;
    @FXML private Label nicknameLabel;
    @FXML private Label codenameLabel;

    @FXML private ImageView Imageauditor;

    private AboutNisitHardCode dataSource;

    private modelAboutList nisitList;

    @FXML
    public void initialize() {
        dataSource = new AboutNisitHardCode();
        nisitList = dataSource.getnisitList();
        showListView();
        clearSelectedAbout();
        handleSelectedListView();
    }
    private void handleSelectedListView() {

        Auditorlist.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<modelAbout>() {
                    @Override
                    public void changed(ObservableValue<? extends modelAbout>
                                                observable,
                                        modelAbout oldValue, modelAbout newValue) {
                        System.out.println(newValue + " is selected");
                        showSelectedAbout(newValue);
                    }
                });
    }
    private void showListView() {
        Auditorlist.getItems().addAll(nisitList.getAllnisits());
        Auditorlist.refresh();
    }

    private void showSelectedAbout(modelAbout nisit) {
        nameLabel.setText(nisit.getName());
        nicknameLabel.setText(nisit.getNickname());
        codenameLabel.setText(nisit.getCodename());
        String url = getClass().getResource("/ku/cs/images/"+nisit.getNickname()+".jpg").toExternalForm();
        Imageauditor.setImage(new Image(url));
    }
    private void clearSelectedAbout() {
        nameLabel.setText("");
        nicknameLabel.setText("");
        codenameLabel.setText("");
        String url = getClass().getResource("/ku/cs/images/default-profile.jpg").toExternalForm();
        Imageauditor.setImage(new Image(url));
    }
    public void handleBackToProjectButtom(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("start");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า about ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
