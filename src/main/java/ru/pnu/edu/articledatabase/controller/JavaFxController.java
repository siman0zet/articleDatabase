package ru.pnu.edu.articledatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pnu.edu.articledatabase.entity.Release;
import ru.pnu.edu.articledatabase.servise.release.ReleaseService;

import java.util.List;

@Component
@FxmlView("main.fxml")
public class JavaFxController {

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab articlePage;

    @FXML
    private TableView<?> articleTable;

    @FXML
    private TextField searchLine;

    @FXML
    private Label searchLabel;

    @FXML
    private Tab magazinePage;

    @FXML
    private TableView<?> magazineTable;

    @FXML
    private Tab releasePage;

    @FXML
    private Tab openPublicationPage;

    @FXML
    private TableView<?> openPublicationTable;

    @FXML
    private Tab exportControlPage;

    @FXML
    private TableView<?> exportControlTable;

    @FXML
    private Button edit;

    @FXML
    private Button delete;

    @FXML
    private MenuBar menuBar;

    @FXML
    private Menu createGroup;

    @FXML
    private Menu generateConclusions;

    @FXML
    private Menu generateQuote;

    @FXML
    private Menu generatePublishList;

    @FXML
    private ObservableList<Release> releaseData = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Release, Integer> year;

    @FXML
    private TableColumn<Release, Integer> number;

    @FXML
    private TableColumn<Release, Integer> release_id;

    @FXML
    private TableView<Release> releaseTable;

    @FXML
    private Button add;

    @Setter(onMethod_ = @Autowired)
    private ReleaseService releaseService;

    @FXML
    public void initialize() {
        initReleaseTable();
    }

    private void refreshReleaseData(){
        releaseData.addAll(releaseService.getAll());
    }


    private void initReleaseTable(){
        refreshReleaseData();
        year.setCellValueFactory(new PropertyValueFactory<Release, Integer>("year"));
        number.setCellValueFactory(new PropertyValueFactory<Release, Integer>("number"));
        release_id.setCellValueFactory(new PropertyValueFactory<Release, Integer>("id"));
        releaseTable.setItems(releaseData);
    }

}
