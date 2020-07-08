package ru.pnu.edu.articledatabase.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pnu.edu.articledatabase.entity.Release;
import ru.pnu.edu.articledatabase.servise.release.ReleaseService;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseCreateArgument;

@Component
@FxmlView("newRelease.fxml")
public class ReleaseController {
    @FXML
    private Label noteLabel;

    @FXML
    private TextField yearLabel;

    @FXML
    private TextField numberLabel;

    @FXML
    private Button acceptBtn;

    @FXML
    private Button cancelBtn;

    @Setter(onMethod_ = @Autowired)
    private ReleaseService releaseService;

    @FXML
    void initialize() {
        acceptBtn.setOnAction(this::add);
        cancelBtn.setOnAction(event ->{
            yearLabel.clear();
            numberLabel.clear();
        });
    }

    private void add(ActionEvent event) {
        if (yearLabel.getLength() == 0 || numberLabel.getLength() == 0){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ограничение на ввод");
            alert.setContentText("Вы не ввели год или номер выпуска!");
            alert.showAndWait();
            return;
        }
        if (Integer.parseInt(numberLabel.getText()) > 24){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Ограничение на ввод");
            alert.setContentText("Номер журнала не должен превышать 24!");
            alert.showAndWait();
            return;
        }

        try {
            ReleaseCreateArgument argument = new ReleaseCreateArgument(Integer.parseInt(yearLabel.getText()),
                    Integer.parseInt(numberLabel.getText()));
            Release release = releaseService.create(argument);
            closeStage(event);
        }
        catch (NumberFormatException exception){
            exception.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Неверно введены данные");
            alert.setContentText("При вводе используйте только числовой формат записи!");
            alert.showAndWait();
        }
    }

    private void closeStage(ActionEvent event) {
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
}
