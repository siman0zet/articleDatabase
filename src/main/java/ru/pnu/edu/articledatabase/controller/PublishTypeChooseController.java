package ru.pnu.edu.articledatabase.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("publishTypeChoose.fxml")
public class PublishTypeChooseController {

    @FXML
    private Button magazineBtn;

    @FXML
    private Button conferenceBtn;

    @Setter(onMethod_ = @Autowired)
    private FxWeaver fxWeaver;

    @FXML
    public void initialize() {
        magazineBtn.setOnAction(e -> {
            loadNewScene(MagazineController.class);
            closeStage(e);
        });

        conferenceBtn.setOnAction(e -> {
            loadNewScene(ConferenceController.class);
            closeStage(e);
        });
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }

    private void loadNewScene(Class clazz) {
        Parent root = fxWeaver.loadView((Class<Object>) clazz);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Добавление элемента");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
