package ru.pnu.edu.articledatabase.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("newMagazine.fxml")
public class MagazineController {

    @FXML
    private TextField name;

    @FXML
    private TextField linkOnPage;

    @FXML
    private TextField impact;

    @FXML
    private TextField city;

    @FXML
    private TextField publishing;

    @FXML
    private TextField isbn;

    @FXML
    private TextField issn;

    @FXML
    private TextField emailAddress;

    @FXML
    private Button acceptBtn;

    @FXML
    private Button cancelBtn;

}
