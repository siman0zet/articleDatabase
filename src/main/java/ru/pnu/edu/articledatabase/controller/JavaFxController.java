package ru.pnu.edu.articledatabase.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.IntegerStringConverter;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.pnu.edu.articledatabase.entity.ExportControl;
import ru.pnu.edu.articledatabase.entity.Magazine;
import ru.pnu.edu.articledatabase.entity.OpenPublication;
import ru.pnu.edu.articledatabase.entity.Release;
import ru.pnu.edu.articledatabase.servise.exportcontrol.ExportControlService;
import ru.pnu.edu.articledatabase.servise.exportcontrol.argument.ExportControlUpdateArgument;
import ru.pnu.edu.articledatabase.servise.magazine.MagazineService;
import ru.pnu.edu.articledatabase.servise.openpublication.OpenPublicationService;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationUpdateArgument;
import ru.pnu.edu.articledatabase.servise.release.ReleaseService;
import ru.pnu.edu.articledatabase.servise.release.argument.ReleaseUpdateArgument;

import java.util.Date;

@Component
@FxmlView("main.fxml")
public class JavaFxController {
    @FXML
    private Tab articlePage;
    @FXML
    private TableView<?> articleTable;


    @FXML
    private Tab magazinePage;
    @FXML
    private TableView<Magazine> magazineTable;
    @FXML
    private TableColumn<Magazine, String> magazineName;
    @FXML
    private TableColumn<Magazine, String> magazineLinkOnPage;
    @FXML
    private TableColumn<Magazine, String> magazineEmailAddresses;
    @FXML
    private TableColumn<Magazine, Integer> magazineReleaseId;
    @FXML
    private TableColumn<Magazine, String> magazinePageRange;
    @FXML
    private TableColumn<Magazine, Integer> magazineId;
    @FXML
    private TableColumn<Magazine, Date> magazineConferenceDate;
    @FXML
    ObservableList<Magazine> magazineData = FXCollections.observableArrayList();
    @Setter(onMethod_ = @Autowired)
    MagazineService magazineService;


    @FXML
    private Tab releasePage;
    @FXML
    private TableView<Release> releaseTable;
    @FXML
    private TableColumn<Release, Integer> releaseYear;
    @FXML
    private TableColumn<Release, Integer> releaseNumber;
    @FXML
    private TableColumn<Release, Integer> releaseId;
    @FXML
    private ObservableList<Release> releaseData = FXCollections.observableArrayList();
    @Setter(onMethod_ = @Autowired)
    private ReleaseService releaseService;


    @FXML
    private Tab openPublicationPage;
    @FXML
    private TableView<OpenPublication> openPublicationTable;
    @FXML
    private TableColumn<OpenPublication, Date> openPublicationDate;
    @FXML
    private TableColumn<OpenPublication, Integer> openPublicationNumber;
    @FXML
    private TableColumn<OpenPublication, Integer> openPublicationId;
    @FXML
    private ObservableList<OpenPublication> openPublicationData = FXCollections.observableArrayList();
    @Setter(onMethod_ = @Autowired)
    private OpenPublicationService openPublicationService;


    @FXML
    private Tab exportControlPage;
    @FXML
    private TableView<ExportControl> exportControlTable;
    @FXML
    private TableColumn<ExportControl, Date> exportControlDate;
    @FXML
    private TableColumn<ExportControl, Integer> exportControlNumber;
    @FXML
    private TableColumn<ExportControl, Integer> exportControlId;
    @FXML
    private ObservableList<ExportControl> exportControlData = FXCollections.observableArrayList();
    @Setter(onMethod_ = @Autowired)
    private ExportControlService exportControlService;


    @FXML
    private Button add;
    @FXML
    private Button delete;
    @FXML
    private Menu createGroup;
    @FXML
    private Menu generateConclusions;
    @FXML
    private Menu generateQuote;
    @FXML
    private Menu generatePublishList;
    @FXML
    private TextField searchLine;


    @Setter(onMethod_ = @Autowired)
    private FxWeaver fxWeaver;


    @FXML
    public void initialize() {
        initArticleTable();
        refreshArticleData();

        initMagazineTable();
        refreshMagazineData();

        initReleaseTable();
        refreshReleaseData();

        initOpenPublicationTable();
        refreshOpenPublicationData();

        initExportControlTable();
        refreshExportControlData();

        add.setOnAction(e -> {
            if (releasePage.isSelected()) {
                loadNewScene(ReleaseController.class);
                refreshReleaseData();
            }
        });

        delete.setOnAction(e -> {
            if (releasePage.isSelected()) {
                Release selectedItem = releaseTable.getSelectionModel().getSelectedItem();
                releaseService.delete(selectedItem.getId());
                refreshReleaseData();
            }
        });
    }

    private void refreshArticleData() {

    }

    private void initArticleTable() {

    }


    private void refreshMagazineData() {
        magazineData.clear();
        magazineData.addAll(magazineService.getAll());
    }

    private void initMagazineTable() {
        magazineId.setCellValueFactory(new PropertyValueFactory<Magazine, Integer>("id"));
        magazineName.setCellValueFactory(new PropertyValueFactory<Magazine, String>("name"));
        magazineLinkOnPage.setCellValueFactory(new PropertyValueFactory<Magazine, String>("link_on_page"));
        magazineEmailAddresses.setCellValueFactory(new PropertyValueFactory<Magazine, String>("email_addresses"));
        magazineReleaseId.setCellValueFactory(new PropertyValueFactory<Magazine, Integer>("release_id"));
        magazinePageRange.setCellValueFactory(new PropertyValueFactory<Magazine, String>("page_range"));
        magazineConferenceDate.setCellValueFactory(new PropertyValueFactory<Magazine, Date>("conference_date"));
//        magazineEmailAddresses.setCellFactory(TextFieldTableCell.forTableColumn());
//        magazineEmailAddresses.setOnEditCommit((TableColumn.CellEditEvent<Magazine, String> event) -> {
//            Magazine editedItem = event.getRowValue();
//            String newEmail = event.getNewValue();
//            MagazineUpdateArgument argument = new MagazineUpdateArgument(
//                    editedItem.getName(),
//                    editedItem.getLinkOnPage(),
//                    newEmail,
//                    editedItem.getReleaseId(),
//                    editedItem.getConferenceDate(),
//                    editedItem.getPageRange());
//            TablePosition<Magazine, String> position = event.getTablePosition();
//            int row = position.getRow();
//            magazineEmailAddresses.getTableView().getItems().get(row).setEmailAddresses(newEmail);
//            magazineService.update(editedItem.getId(), argument);
//        });
        magazineTable.setItems(magazineData);
    }


    private void refreshReleaseData() {
        releaseData.clear();
        releaseData.addAll(releaseService.getAll());
    }

    private void initReleaseTable() {
        releaseTable.setEditable(true);
        releaseYear.setCellValueFactory(new PropertyValueFactory<Release, Integer>("year"));
        releaseYear.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        releaseYear.setOnEditCommit((TableColumn.CellEditEvent<Release, Integer> event) -> {
            Release editedItem = event.getRowValue();
            Integer newYear = event.getNewValue();
            ReleaseUpdateArgument argument = new ReleaseUpdateArgument(
                    newYear,
                    editedItem.getNumber());
            TablePosition<Release, Integer> position = event.getTablePosition();
            int row = position.getRow();
            releaseYear.getTableView().getItems().get(row).setYear(newYear);
            releaseService.update(editedItem.getId(), argument);
        });

        releaseNumber.setCellValueFactory(new PropertyValueFactory<Release, Integer>("number"));
        releaseNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        releaseNumber.setOnEditCommit((TableColumn.CellEditEvent<Release, Integer> event) -> {
            Release editedItem = event.getRowValue();
            Integer newNumber = event.getNewValue();
            ReleaseUpdateArgument argument = new ReleaseUpdateArgument(
                    editedItem.getYear(),
                    newNumber);
            TablePosition<Release, Integer> position = event.getTablePosition();
            int row = position.getRow();
            releaseNumber.getTableView().getItems().get(row).setNumber(newNumber);
            releaseService.update(editedItem.getId(), argument);
        });
        releaseId.setCellValueFactory(new PropertyValueFactory<Release, Integer>("id"));
        releaseTable.setItems(releaseData);
    }


    private void refreshOpenPublicationData() {
        openPublicationData.clear();
        openPublicationData.addAll(openPublicationService.getAll());
    }

    private void initOpenPublicationTable() {
        openPublicationTable.setEditable(true);
        openPublicationNumber.setCellValueFactory(new PropertyValueFactory<OpenPublication, Integer>("number"));
        openPublicationNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        openPublicationNumber.setOnEditCommit((TableColumn.CellEditEvent<OpenPublication, Integer> event) -> {
            OpenPublication editedItem = event.getRowValue();
            Integer newNumber = event.getNewValue();
            OpenPublicationUpdateArgument argument = new OpenPublicationUpdateArgument(
                    newNumber,
                    editedItem.getDate());
            TablePosition<OpenPublication, Integer> position = event.getTablePosition();
            int row = position.getRow();
            openPublicationNumber.getTableView().getItems().get(row).setNumber(newNumber);
            openPublicationService.update(editedItem.getId(), argument);
        });
        openPublicationDate.setCellValueFactory(new PropertyValueFactory<OpenPublication, Date>("date"));
        openPublicationDate.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        openPublicationDate.setOnEditCommit((TableColumn.CellEditEvent<OpenPublication, Date> event) -> {
            OpenPublication editedItem = event.getRowValue();
            Date newDate = event.getNewValue();
            OpenPublicationUpdateArgument argument = new OpenPublicationUpdateArgument(
                    editedItem.getNumber(),
                    newDate);
            TablePosition<OpenPublication, Date> position = event.getTablePosition();
            int row = position.getRow();
            openPublicationNumber.getTableView().getItems().get(row).setDate(newDate);
            openPublicationService.update(editedItem.getId(), argument);
        });
        openPublicationId.setCellValueFactory(new PropertyValueFactory<OpenPublication, Integer>("id"));
        openPublicationTable.setItems(openPublicationData);
    }


    private void refreshExportControlData() {
        exportControlData.clear();
        exportControlData.addAll(exportControlService.getAll());
    }

    private void initExportControlTable() {
        exportControlTable.setEditable(true);
        exportControlNumber.setCellValueFactory(new PropertyValueFactory<ExportControl, Integer>("number"));
        exportControlNumber.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        exportControlNumber.setOnEditCommit((TableColumn.CellEditEvent<ExportControl, Integer> event) -> {
            ExportControl editedItem = event.getRowValue();
            Integer newNumber = event.getNewValue();
            ExportControlUpdateArgument argument = new ExportControlUpdateArgument(
                    newNumber,
                    editedItem.getDate());
            TablePosition<ExportControl, Integer> position = event.getTablePosition();
            int row = position.getRow();
            exportControlNumber.getTableView().getItems().get(row).setNumber(newNumber);
            exportControlService.update(editedItem.getId(), argument);
        });
        exportControlDate.setCellValueFactory(new PropertyValueFactory<ExportControl, Date>("date"));
        exportControlDate.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        exportControlDate.setOnEditCommit((TableColumn.CellEditEvent<ExportControl, Date> event) -> {
            ExportControl editedItem = event.getRowValue();
            Date newDate = event.getNewValue();
            ExportControlUpdateArgument argument = new ExportControlUpdateArgument(
                    editedItem.getNumber(),
                    newDate);
            TablePosition<ExportControl, Date> position = event.getTablePosition();
            int row = position.getRow();
            exportControlNumber.getTableView().getItems().get(row).setDate(newDate);
            exportControlService.update(editedItem.getId(), argument);
        });
        exportControlId.setCellValueFactory(new PropertyValueFactory<ExportControl, Integer>("id"));
        exportControlTable.setItems(exportControlData);
    }


    private void loadNewScene(Class clazz) {
        Parent root = fxWeaver.loadView((Class<Object>) clazz);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
    }
}
