package ru.pnu.edu.articledatabase.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.*;
import lombok.Setter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Component;
import ru.pnu.edu.articledatabase.entity.*;
import ru.pnu.edu.articledatabase.enumerations.ArticleLevel;
import ru.pnu.edu.articledatabase.enumerations.ArticleStage;
import ru.pnu.edu.articledatabase.enumerations.PublicationType;
import ru.pnu.edu.articledatabase.enumerations.ScienceArea;
import ru.pnu.edu.articledatabase.servise.arcticle.ArticleService;
import ru.pnu.edu.articledatabase.servise.arcticle.argument.ArticleUpdateArgument;
import ru.pnu.edu.articledatabase.servise.exportcontrol.ExportControlService;
import ru.pnu.edu.articledatabase.servise.exportcontrol.argument.ExportControlUpdateArgument;
import ru.pnu.edu.articledatabase.servise.magazine.MagazineService;
import ru.pnu.edu.articledatabase.servise.magazine.argument.MagazineUpdateArgument;
import ru.pnu.edu.articledatabase.servise.openpublication.OpenPublicationService;
import ru.pnu.edu.articledatabase.servise.openpublication.argument.OpenPublicationUpdateArgument;

import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Date;

@Component
@FxmlView("main.fxml")
public class JavaFxController {
    @FXML
    private Tab articlePage;
    @FXML
    private TableView<Article> articleTable;
    @FXML
    private TableColumn<Article, String> articleName;
    @FXML
    private TableColumn<Article, Integer> articleStudyYear;
    @FXML
    private TableColumn<Article, String> articleAuthors;
    @FXML
    private TableColumn<Article, ArticleLevel> articleLevel;
    @FXML
    private TableColumn<Article, ArticleStage> articleStage;
    @FXML
    private TableColumn<Article, Magazine> articleMagazineId;
    @FXML
    private TableColumn<Article, String> articlePageRange;
    @FXML
    private TableColumn<Article, Integer> articleVolume;
    @FXML
    private TableColumn<Article, Date> articleDepartureDate;
    @FXML
    private TableColumn<Article, ScienceArea> articleScienceArea;
    @FXML
    private TableColumn<Article, PublicationType> articlePublicationType;
    @FXML
    private TableColumn<Article, Double> articlePrice;
    @FXML
    private TableColumn<Article, Integer> articleRelease;
    @FXML
    private TableColumn<Article, OpenPublication> articleOpenPublicationId;
    @FXML
    private TableColumn<Article, ExportControl> articleExportControlId;
    @FXML
    private TableColumn<Article, Integer> articleId;
    @FXML
    ObservableList<Article> articleData = FXCollections.observableArrayList();
    @Setter(onMethod_ = @Autowired)
    ArticleService articleService;


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
    private TableColumn<Magazine, Integer> magazineId;
    @FXML
    private TableColumn<Magazine, Date> magazineConferenceDate;
    @FXML
    private TableColumn<Magazine, String> magazinePublishType;
    @FXML
    private TableColumn<Magazine, String> magazinePublish;
    @FXML
    private TableColumn<Magazine, String> magazineCity;
    @FXML
    ObservableList<Magazine> magazineData = FXCollections.observableArrayList();
    @Setter(onMethod_ = @Autowired)
    MagazineService magazineService;


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
    private TextField searchLine;


    @Setter(onMethod_ = @Autowired)
    private FxWeaver fxWeaver;


    @FXML
    public void initialize() {
        initArticleTable();
        refreshArticleData();

        initMagazineTable();
        refreshMagazineData();


        initOpenPublicationTable();
        refreshOpenPublicationData();

        initExportControlTable();
        refreshExportControlData();

        add.setOnAction(e -> {
            if (magazinePage.isSelected()) {
                loadNewScene(PublishTypeChooseController.class);
                refreshMagazineData();
            }
            if (openPublicationPage.isSelected()){
                loadNewScene(OpenPublicationController.class);
                refreshOpenPublicationData();
            }
            if (exportControlPage.isSelected()) {
                loadNewScene(ExportControlController.class);
                refreshExportControlData();
            }
            if (articlePage.isSelected()){
                loadNewScene(ArticleController.class);
                refreshArticleData();
            }
        });

    }

    private ArticleUpdateArgument createArticleUpdateArgument(TableColumn.CellEditEvent<Article, ?> articleStringCellEditEvent){
        ArticleUpdateArgument argument = new ArticleUpdateArgument(
                articleStringCellEditEvent.getRowValue().getName(),
                articleStringCellEditEvent.getRowValue().getStudy_year(),
                articleStringCellEditEvent.getRowValue().getAuthors(),
                articleStringCellEditEvent.getRowValue().getArticle_level(),
                articleStringCellEditEvent.getRowValue().getStage(),
                articleStringCellEditEvent.getRowValue().getMagazine_id(),
                articleStringCellEditEvent.getRowValue().getPage_range(),
                articleStringCellEditEvent.getRowValue().getPrice(),
                articleStringCellEditEvent.getRowValue().getPublication_type(),
                articleStringCellEditEvent.getRowValue().getVolume(),
                articleStringCellEditEvent.getRowValue().getRelease(),
                articleStringCellEditEvent.getRowValue().getDeparture_date(),
                articleStringCellEditEvent.getRowValue().getScience_area(),
                articleStringCellEditEvent.getRowValue().getOpen_publication_id(),
                articleStringCellEditEvent.getRowValue().getExport_control_id()
        );
        return argument;
    }

    private void refreshArticleData() {
        articleData.clear();
        articleData.addAll(articleService.getAll());
    }

    private void initArticleTable() {
        articleTable.setEditable(true);
        articleName.setCellValueFactory(new PropertyValueFactory<Article, String>("name"));
        articleName.setCellFactory(TextFieldTableCell.forTableColumn());
        articleName.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, String> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setName(articleStringCellEditEvent.getNewValue());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setName(articleStringCellEditEvent.getNewValue());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        articleStudyYear.setCellValueFactory(new PropertyValueFactory<Article, Integer>("study_year"));
        articleStudyYear.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        articleStudyYear.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, Integer> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setStudy_year(articleStringCellEditEvent.getNewValue());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setStudy_year(articleStringCellEditEvent.getNewValue());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));


        articleScienceArea.setCellValueFactory(new PropertyValueFactory<Article, ScienceArea>("science_area"));
        ObservableList<ScienceArea> scienceAreaObservableList = FXCollections.observableArrayList(ScienceArea.values());
        articleScienceArea.setCellFactory(ComboBoxTableCell.forTableColumn(scienceAreaObservableList));
        articleScienceArea.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, ScienceArea>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, ScienceArea> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setScience_area(articleStringCellEditEvent.getNewValue().toString());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setScience_area(articleStringCellEditEvent.getNewValue().toString());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        articleMagazineId.setCellValueFactory(new PropertyValueFactory<Article, Magazine>("magazine_id"));

        articleLevel.setCellValueFactory(new PropertyValueFactory<Article, ArticleLevel>("article_level"));
        ObservableList<ArticleLevel> articleLevelObservableList = FXCollections.observableArrayList(ArticleLevel.values());
        articleLevel.setCellFactory(ComboBoxTableCell.forTableColumn(articleLevelObservableList));
        articleLevel.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, ArticleLevel>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, ArticleLevel> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setArticle_level(articleStringCellEditEvent.getNewValue().toString());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setArticle_level(articleStringCellEditEvent.getNewValue().toString());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        articlePublicationType.setCellValueFactory(new PropertyValueFactory<Article, PublicationType>("publication_type"));
        ObservableList<PublicationType> publicationTypeObservableList = FXCollections.observableArrayList(PublicationType.values());
        articlePublicationType.setCellFactory(ComboBoxTableCell.forTableColumn(publicationTypeObservableList));
        articlePublicationType.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, PublicationType>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, PublicationType> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setPublication_type(articleStringCellEditEvent.getNewValue().toString());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setPublication_type(articleStringCellEditEvent.getNewValue().toString());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        articleStage.setCellValueFactory(new PropertyValueFactory<Article, ArticleStage>("stage"));
        ObservableList<ArticleStage> articleStageObservableList = FXCollections.observableArrayList(ArticleStage.values());
        articleStage.setCellFactory(ComboBoxTableCell.forTableColumn(articleStageObservableList));
        articleStage.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, ArticleStage>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, ArticleStage> articleArticleStageCellEditEvent) {
                articleArticleStageCellEditEvent.getRowValue().setStage(articleArticleStageCellEditEvent.getNewValue().toString());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleArticleStageCellEditEvent);
                articleArticleStageCellEditEvent.getTableView().getItems()
                        .get(articleArticleStageCellEditEvent.getTablePosition().getRow())
                        .setStage(articleArticleStageCellEditEvent.getNewValue().toString());
                articleService.update(articleArticleStageCellEditEvent.getRowValue().getId(), argument);
            }
        }));


        articleAuthors.setCellValueFactory(new PropertyValueFactory<Article, String>("authors"));
        articleAuthors.setCellFactory(TextFieldTableCell.forTableColumn());
        articleAuthors.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, String> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setAuthors(articleStringCellEditEvent.getNewValue());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setAuthors(articleStringCellEditEvent.getNewValue());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        articleVolume.setCellValueFactory(new PropertyValueFactory<Article, Integer>("volume"));
        articleVolume.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        articleVolume.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, Integer> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setVolume(articleStringCellEditEvent.getNewValue());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setVolume(articleStringCellEditEvent.getNewValue());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));



        articleDepartureDate.setCellValueFactory(new PropertyValueFactory<Article, Date>("departure_date"));
        articleDepartureDate.setCellFactory(TextFieldTableCell.forTableColumn());
//        articleDepartureDate.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, LocalDate>>() {
//            @Override
//            public void handle(TableColumn.CellEditEvent<Article, LocalDate> articleDatePickerCellEditEvent) {
//                articleDatePickerCellEditEvent.getRowValue().setDeparture_date(articleDatePickerCellEditEvent.getNewValue()));
//                ArticleUpdateArgument argument = createArticleUpdateArgument(articleDatePickerCellEditEvent);
//                articleDatePickerCellEditEvent.getTableView().getItems()
//                        .get(articleDatePickerCellEditEvent.getTablePosition().getRow())
//                        .setDeparture_date(articleDatePickerCellEditEvent.getNewValue());
//                articleService.update(articleDatePickerCellEditEvent.getRowValue().getId(), argument);
//            }
//        }));


        articlePrice.setCellValueFactory(new PropertyValueFactory<Article, Double>("price"));
        articlePrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        articlePrice.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, Double>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, Double> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setPrice(articleStringCellEditEvent.getNewValue());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setPrice(articleStringCellEditEvent.getNewValue());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        articleOpenPublicationId.setCellValueFactory(new PropertyValueFactory<Article, OpenPublication>("open_publication_id"));

        articleExportControlId.setCellValueFactory(new PropertyValueFactory<Article, ExportControl>("export_control_id"));

        articleRelease.setCellValueFactory(new PropertyValueFactory<Article, Integer>("release"));
        articleRelease.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        articleRelease.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, Integer>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Article, Integer> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setRelease(articleStringCellEditEvent.getNewValue());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setRelease(articleStringCellEditEvent.getNewValue());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        articlePageRange.setCellValueFactory(new PropertyValueFactory<Article, String>("page_range"));
        articlePageRange.setCellFactory(TextFieldTableCell.forTableColumn());
        articlePageRange.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Article, String>>(){
            @Override
            public void handle(TableColumn.CellEditEvent<Article, String> articleStringCellEditEvent) {
                articleStringCellEditEvent.getRowValue().setPage_range(articleStringCellEditEvent.getNewValue());
                ArticleUpdateArgument argument = createArticleUpdateArgument(articleStringCellEditEvent);
                articleStringCellEditEvent.getTableView().getItems()
                        .get(articleStringCellEditEvent.getTablePosition().getRow())
                        .setPage_range(articleStringCellEditEvent.getNewValue());
                articleService.update(articleStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));


        articleId.setVisible(false);
        articleTable.setItems(articleData);
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }


    private void refreshMagazineData() {
        magazineData.clear();
        magazineData.addAll(magazineService.getAll());
    }

    private MagazineUpdateArgument createMagazineUpdateArgument(TableColumn.CellEditEvent<Magazine, ?> magazineStringCellEditEvent){
        MagazineUpdateArgument argument = new MagazineUpdateArgument(
                magazineStringCellEditEvent.getRowValue().getName(),
                magazineStringCellEditEvent.getRowValue().getLink_on_page(),
                magazineStringCellEditEvent.getRowValue().getEmail_addresses(),
                magazineStringCellEditEvent.getRowValue().getConference_date(),
                magazineStringCellEditEvent.getRowValue().getImpact(),
                magazineStringCellEditEvent.getRowValue().getIsbn(),
                magazineStringCellEditEvent.getRowValue().getIssn(),
                magazineStringCellEditEvent.getRowValue().getCity(),
                magazineStringCellEditEvent.getRowValue().getPublishing()
        );
        return argument;
    }

    private void initMagazineTable() {
        magazineTable.setEditable(true);
        magazineId.setVisible(false);

        magazineName.setCellValueFactory(new PropertyValueFactory<Magazine, String>("name"));
        magazineName.setCellFactory(TextFieldTableCell.forTableColumn());
        magazineName.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Magazine, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Magazine, String> magazineStringCellEditEvent) {
                magazineStringCellEditEvent.getRowValue().setName(magazineStringCellEditEvent.getNewValue());
                MagazineUpdateArgument argument = createMagazineUpdateArgument(magazineStringCellEditEvent);
                magazineStringCellEditEvent.getTableView().getItems()
                        .get(magazineStringCellEditEvent.getTablePosition().getRow())
                        .setName(magazineStringCellEditEvent.getNewValue());
                magazineService.update(magazineStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        magazineLinkOnPage.setCellValueFactory(new PropertyValueFactory<Magazine, String>("link_on_page"));
        magazineLinkOnPage.setCellFactory(TextFieldTableCell.forTableColumn());
        magazineLinkOnPage.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Magazine, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Magazine, String> magazineStringCellEditEvent) {
                magazineStringCellEditEvent.getRowValue().setLink_on_page(magazineStringCellEditEvent.getNewValue());
                MagazineUpdateArgument argument = createMagazineUpdateArgument(magazineStringCellEditEvent);
                magazineStringCellEditEvent.getTableView().getItems()
                        .get(magazineStringCellEditEvent.getTablePosition().getRow())
                        .setLink_on_page(magazineStringCellEditEvent.getNewValue());
                magazineService.update(magazineStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        magazineEmailAddresses.setCellValueFactory(new PropertyValueFactory<Magazine, String>("email_addresses"));
        magazineEmailAddresses.setCellFactory(TextFieldTableCell.forTableColumn());
        magazineEmailAddresses.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Magazine, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Magazine, String> magazineStringCellEditEvent) {
                magazineStringCellEditEvent.getRowValue().setEmail_addresses(magazineStringCellEditEvent.getNewValue());
                MagazineUpdateArgument argument = createMagazineUpdateArgument(magazineStringCellEditEvent);
                magazineStringCellEditEvent.getTableView().getItems()
                        .get(magazineStringCellEditEvent.getTablePosition().getRow())
                        .setEmail_addresses(magazineStringCellEditEvent.getNewValue());
                magazineService.update(magazineStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));

        magazineConferenceDate.setCellValueFactory(new PropertyValueFactory<Magazine, Date>("conference_date"));
        magazineConferenceDate.setCellFactory(TextFieldTableCell.forTableColumn(new DateStringConverter()));
        magazineConferenceDate.setOnEditCommit((new EventHandler<TableColumn.CellEditEvent<Magazine, Date>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Magazine, Date> magazineStringCellEditEvent) {
                magazineStringCellEditEvent.getRowValue().setConference_date(magazineStringCellEditEvent.getNewValue());
                MagazineUpdateArgument argument = createMagazineUpdateArgument(magazineStringCellEditEvent);
                magazineStringCellEditEvent.getTableView().getItems()
                        .get(magazineStringCellEditEvent.getTablePosition().getRow())
                        .setConference_date(magazineStringCellEditEvent.getNewValue());
                magazineService.update(magazineStringCellEditEvent.getRowValue().getId(), argument);
            }
        }));
        magazineTable.setItems(magazineData);
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
        openPublicationId.setVisible(false);
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
        exportControlId.setVisible(false);
        exportControlTable.setItems(exportControlData);
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
