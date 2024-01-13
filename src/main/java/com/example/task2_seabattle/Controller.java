package com.example.task2_seabattle;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.task2_seabattle.UI.CellView;
import com.example.task2_seabattle.UI.StateAction;
import com.example.task2_seabattle.UI.StateOrientation;
import com.example.task2_seabattle.UI.StateShipUI;
import com.example.task2_seabattle.field.Field;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {
    static final int SIZE_FIELD = 10;
    Field gameField;
    StateOrientation stateOrientation = StateOrientation.FOR_X;
    StateAction stateAction = StateAction.ADD;
    StateShipUI stateShipUI = StateShipUI.SHIP1;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ToggleGroup ActionsShip;

    @FXML
    private ToggleGroup orientation;

    @FXML
    private ToggleGroup SelectShip;

    @FXML
    private RadioButton btnAdd;

    @FXML
    private RadioButton btnDelete;
    @FXML
    private RadioButton btnRotate;

    @FXML
    private RadioButton btnMine;

    @FXML
    private RadioButton btnMineSearcher;

    @FXML
    private RadioButton btnOrientX;

    @FXML
    private RadioButton btnOrientY;

    @FXML
    private Button btnRandom;

    @FXML
    private RadioButton btnShip1;

    @FXML
    private RadioButton btnShip2;

    @FXML
    private RadioButton btnShip3;

    @FXML
    private RadioButton btnShip4;

    @FXML
    private RadioButton btnSubmarine;

    @FXML
    private RadioButton btnTranslate;

    @FXML
    private Label cntMine;

    @FXML
    private Label cntMineSearcher;

    @FXML
    private Label cntShip1;

    @FXML
    private Label cntShip2;

    @FXML
    private Label cntShip3;

    @FXML
    private Label cntShip4;

    @FXML
    private Label cntSubmarine;

    @FXML
    private GridPane mainGridPane;
    @FXML
    void clear() {
        mainGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        for (int row = 0; row < SIZE_FIELD; row++) {
            for (int col = 0; col < SIZE_FIELD; col++) {
                CellView cellView = new CellView();
                mainGridPane.add(cellView, col, row);
            }
        }
        cntMine.setText("1");
        cntMineSearcher.setText("1");
        cntSubmarine.setText("1");
        cntShip1.setText("4");
        cntShip2.setText("3");
        cntShip3.setText("2");
        cntShip4.setText("1");
    }

    @FXML
    void random() {
        this.gameField = new Field();
        mainGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        for (int row = 0; row < SIZE_FIELD; row++) {
            for (int col = 0; col < SIZE_FIELD; col++) {
                CellView cellView = new CellView(gameField.cells[row][col]);
                mainGridPane.add(cellView, col, row);
            }
        }
        cntMine.setText("0");
        cntMineSearcher.setText("0");
        cntSubmarine.setText("0");
        cntShip1.setText("0");
        cntShip2.setText("0");
        cntShip3.setText("0");
        cntShip4.setText("0");
    }
    @FXML
    void setOrientation(ActionEvent event) {
        orientation.selectedToggleProperty().removeListener(orientationChangeListener); // Удаляем предыдущего слушателя
        orientation.selectedToggleProperty().addListener(orientationChangeListener); // Добавляем нового слушателя
    }

    private final ChangeListener<Toggle> orientationChangeListener = (observable, oldValue, newValue) -> {
        if (orientation.getSelectedToggle() != null) {
            RadioButton selectedRadioButton = (RadioButton) orientation.getSelectedToggle();
            System.out.println("Выбрана кнопка: " + selectedRadioButton.getText());
        }
    };

    @FXML
    void initialize() {
        btnDelete.setToggleGroup(ActionsShip);
        btnAdd.setToggleGroup(ActionsShip);
        btnRotate.setToggleGroup(ActionsShip);
        btnOrientX.setToggleGroup(orientation);
        btnOrientY.setToggleGroup(orientation);
        btnShip1.setToggleGroup(SelectShip);
        btnShip2.setToggleGroup(SelectShip);
        btnShip3.setToggleGroup(SelectShip);
        btnShip4.setToggleGroup(SelectShip);
        btnMine.setToggleGroup(SelectShip);
        btnMineSearcher.setToggleGroup(SelectShip);
        btnSubmarine.setToggleGroup(SelectShip);
        gameField = new Field();
        mainGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        for (int row = 0; row < SIZE_FIELD; row++) {
            for (int col = 0; col < SIZE_FIELD; col++) {
                CellView cellView = new CellView(gameField.cells[row][col]);
                mainGridPane.add(cellView, col, row);
            }
        }
        assert btnRandom != null : "fx:id=\"btnRandom\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert mainGridPane != null : "fx:id=\"mainGridPane\" was not injected: check your FXML file 'hello-view.fxml'.";
    }

}
