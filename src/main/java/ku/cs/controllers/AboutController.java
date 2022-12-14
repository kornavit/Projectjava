package ku.cs.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import java.io.IOException;
import com.github.saacsos.FXRouter;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ku.cs.services.modelAboutNisitHardCode;
import ku.cs.models.modelAbout;
import ku.cs.models.modelAboutList;

public class AboutController {
    @FXML
    private ListView<modelAbout> auditorList;
    @FXML private Label nameLabel;
    @FXML private Label nicknameLabel;
    @FXML private Label codenameLabel;

    @FXML private ImageView imageAuditor;

    private modelAboutNisitHardCode dataSource;

    private modelAboutList nisitList;

    @FXML
    public void initialize() {
        dataSource = new modelAboutNisitHardCode();
        nisitList = dataSource.getNisitList();
        showListView();
        clearSelectedAbout();
        handleSelectedListView();
    }
    private void handleSelectedListView() {

        auditorList.getSelectionModel().selectedItemProperty().addListener(
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
        auditorList.getItems().addAll(nisitList.getAllNisits());
        auditorList.refresh();
    }

    private void showSelectedAbout(modelAbout nisit) {
        nameLabel.setText(nisit.getName());
        nicknameLabel.setText(nisit.getNickname());
        codenameLabel.setText(nisit.getCodename());
        String url = getClass().getResource("/ku/cs/images/"+nisit.getNickname()+".jpg").toExternalForm();
        imageAuditor.setImage(new Image(url));
    }
    private void clearSelectedAbout() {
        nameLabel.setText("");
        nicknameLabel.setText("");
        codenameLabel.setText("");
        String url = getClass().getResource("/ku/cs/images/default-profile.jpg").toExternalForm();
        imageAuditor.setImage(new Image(url));
    }
    public void handleBackToProjectButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("start");
        } catch (IOException e) {
            System.err.println("??????????????????????????? about ??????????????????");
            System.err.println("?????????????????????????????????????????????????????? route");
        }
    }
}
