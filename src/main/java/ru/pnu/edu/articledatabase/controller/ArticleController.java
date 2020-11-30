package ru.pnu.edu.articledatabase.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;
import ru.pnu.edu.articledatabase.enumerations.ArticleLevel;
import ru.pnu.edu.articledatabase.enumerations.PublicationType;
import ru.pnu.edu.articledatabase.enumerations.ScienceArea;
import ru.pnu.edu.articledatabase.enumerations.ArticleStage;

@Component
@FxmlView("newArticle.fxml")
public class ArticleController {

    @FXML
    private Button acceptBtn;

    @FXML
    private Button cancelBtn;

    @FXML
    private TextField name;

    @FXML
    private TextField studyYear;

    @FXML
    private TextField authors;

    @FXML
    private TextField release;

    @FXML
    private TextField pageRange;

    @FXML
    private TextField volume;

    @FXML
    private TextField price;

    @FXML
    private DatePicker departureDate;

    @FXML
    private ComboBox<ArticleLevel> articleLevelCmbBox;

    @FXML
    private ComboBox<ScienceArea> scienceAreaCmbBox;

    @FXML
    private ComboBox<ArticleStage> stageCmbBox;

    @FXML
    private ComboBox<PublicationType> publicationTypeCmbBox;

    @FXML
    private ComboBox<?> openPublicationCmbBox;

    @FXML
    private ComboBox<?> exportControlCmbBox;

    @FXML
    private ComboBox<?> magazineCmbBox;

    public void initialize(){
        stageCmbBox.setItems(FXCollections.observableArrayList(ArticleStage.values()));
        scienceAreaCmbBox.setItems((FXCollections.observableArrayList(ScienceArea.values())));
        publicationTypeCmbBox.setItems(FXCollections.observableArrayList(PublicationType.values()));
        articleLevelCmbBox.setItems(FXCollections.observableArrayList(ArticleLevel.values()));
    }

}


