package ru.pnu.edu.articledatabase;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class EditingDatePickerCell <Article, LocalDate> extends TableCell<Article, LocalDate> {

    @Override
    public void startEdit() {
        if (!isEmpty()) {
            super.startEdit();
            createDatePicker();
            setText(null);
            setGraphic(datePicker);
            datePicker.requestFocus();
        }
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        datePicker.setValue((java.time.LocalDate) getItem());
        setGraphic(null);
        setContentDisplay(ContentDisplay.TEXT_ONLY);
    }

    private DatePicker datePicker;

    public EditingDatePickerCell() {
        if (datePicker == null) {
            createDatePicker();
        }
        setGraphic(datePicker);
        setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                datePicker.requestFocus();
            }
        });
    }

    @Override
    public void updateItem(LocalDate item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
            setText(null);
            setGraphic(null);
        } else {

            if (datePicker != null && item != null) {
                datePicker.setValue((java.time.LocalDate) getLocalDate());
                commitEdit(getLocalDate());
            }
            setGraphic(datePicker);
            setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

        }
    }


    private void createDatePicker() {
        datePicker = new DatePicker();
        datePicker.setMinWidth(this.getWidth() - this.getGraphicTextGap() * 2);
        setGraphic(datePicker);



        datePicker.addEventFilter(KeyEvent.KEY_PRESSED,new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {

                if (t.getCode() == KeyCode.ENTER) {
                    commitEdit(getLocalDate());
                } else if (t.getCode() == KeyCode.ESCAPE) {
                    cancelEdit();
                } else if (t.getCode() == KeyCode.TAB) {
                    commitEdit(getLocalDate());;

                }
            }

        });

        setAlignment(Pos.CENTER);
    }

    private LocalDate getLocalDate() {
        return  getItem();
        ///return datePicker.getValue() != null ? datePicker.getValue() : getItem();
    }
}