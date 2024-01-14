package com.example.task2_seabattle;

import java.net.URL;
import java.util.ResourceBundle;

import com.example.task2_seabattle.UI.CellView;
import com.example.task2_seabattle.UI.StateAction;
import com.example.task2_seabattle.UI.StateOrientation;
import com.example.task2_seabattle.UI.TypeShipUI;
import com.example.task2_seabattle.field.Field;
import com.example.task2_seabattle.field.StateCell;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class Controller {
    static final int SIZE_FIELD = 10;
    static final int SIZE_CELL = 32;
    int cntShip1, cntShip2, cntShip3, cntShip4, cntMine, cntMineSearcher, cntSubmarine = 0;
    Field gameField;
    StateOrientation stateOrientation = StateOrientation.FOR_X;
    StateAction stateAction = StateAction.ADD;
    TypeShipUI typeShipUI = TypeShipUI.SHIP1;

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
    private Label labelCntMine;

    @FXML
    private Label labelCntMineSearcher;

    @FXML
    private Label labelCntShip1;

    @FXML
    private Label labelCntShip2;

    @FXML
    private Label labelCntShip3;

    @FXML
    private Label labelCntShip4;

    @FXML
    private Label labelCntSubmarine;

    @FXML
    private GridPane mainGridPane;
    @FXML
    void clear() {
        gameField = new Field(true);
        mainGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        for (int row = 0; row < SIZE_FIELD; row++) {
            for (int col = 0; col < SIZE_FIELD; col++) {
                CellView cellView = new CellView(gameField.cells[row][col]);
                mainGridPane.add(cellView, col, row);
            }
        }
        cntShip1 = 4;
        cntShip2 = 3;
        cntShip3 = 2;
        cntShip4 = 1;
        cntMine = 1;
        cntMineSearcher = 1;
        cntSubmarine = 1;
        labelCntMine.setText(String.valueOf(cntMine));
        labelCntMineSearcher.setText(String.valueOf(cntMineSearcher));
        labelCntSubmarine.setText(String.valueOf(cntSubmarine));
        labelCntShip1.setText(String.valueOf(cntShip1));
        labelCntShip2.setText(String.valueOf(cntShip2));
        labelCntShip3.setText(String.valueOf(cntShip3));
        labelCntShip4.setText(String.valueOf(cntShip4));
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
        cntShip1 = cntShip2 = cntShip3 = cntShip4 = cntMine = cntMineSearcher = cntSubmarine = 0;
        labelCntMine.setText("0");
        labelCntMineSearcher.setText("0");
        labelCntSubmarine.setText("0");
        labelCntShip1.setText("0");
        labelCntShip2.setText("0");
        labelCntShip3.setText("0");
        labelCntShip4.setText("0");
    }
    @FXML
    void setOrientation(ActionEvent event) {
        if (btnOrientX.isSelected()){
            stateOrientation = StateOrientation.FOR_X;
        } else{
            stateOrientation = StateOrientation.FOR_Y;
        }
        System.out.println(stateOrientation);
    }
    @FXML
    void setAction(ActionEvent event) {
        if (btnAdd.isSelected()){
            stateAction = StateAction.ADD;
        } else if (btnDelete.isSelected()) {
            stateAction = StateAction.DELETE;
        } else if (btnRotate.isSelected()) {
            stateAction = StateAction.ROTATE;
        }
        System.out.println(stateAction.toString());
    }
    @FXML
    void setShip(ActionEvent event) {
        if (btnShip1.isSelected()){
            typeShipUI = TypeShipUI.SHIP1;
        } else if (btnShip2.isSelected()){
            typeShipUI = TypeShipUI.SHIP2;
        } else if (btnShip3.isSelected()){
            typeShipUI = TypeShipUI.SHIP3;
        } else if (btnShip4.isSelected()){
            typeShipUI = TypeShipUI.SHIP4;
        } else if (btnMine.isSelected()){
            typeShipUI = TypeShipUI.MINE;
        } else if (btnMineSearcher.isSelected()){
            typeShipUI = TypeShipUI.MINE_SEARCHER;
        } else if (btnSubmarine.isSelected()){
            typeShipUI = TypeShipUI.SUBMARINE;
        }
        System.out.println(typeShipUI.toString());
    }

    private boolean borderCheck(int size, int x, int y, StateOrientation orient){
        switch (orient){
            case FOR_X -> {
                return size + x <= 10;
            }
            case FOR_Y -> {
                return size + y <= 10;
            }
        } return false;
    }

    @FXML
    void mouseClickedEvent(MouseEvent event) {
        int colIndex = (int) (event.getX() / SIZE_CELL); // Определяем индекс столбца
        int rowIndex = (int) (event.getY() / SIZE_CELL); // Определяем индекс строки
        if (btnAdd.isSelected()){
            if(btnShip1.isSelected() && (cntShip1 > 0) && !(gameField.getStateCell(rowIndex, colIndex) == StateCell.BORDER)) {
                gameField.addShip(typeShipUI, stateOrientation, colIndex, rowIndex);
                cntShip1--;
                labelCntShip1.setText(String.valueOf(cntShip1));
            } else if(btnShip2.isSelected() && (cntShip2 > 0) && !(gameField.getStateCell(rowIndex, colIndex) == StateCell.BORDER) && borderCheck(2, colIndex, rowIndex, stateOrientation)){
                gameField.addShip(typeShipUI, stateOrientation, colIndex, rowIndex);
                cntShip2--;
                labelCntShip2.setText(String.valueOf(cntShip2));
            } else if(btnShip3.isSelected() && (cntShip3 > 0) && !(gameField.getStateCell(rowIndex, colIndex) == StateCell.BORDER) && borderCheck(3, colIndex, rowIndex, stateOrientation)){
                gameField.addShip(typeShipUI, stateOrientation, colIndex, rowIndex);
                cntShip3--;
                labelCntShip3.setText(String.valueOf(cntShip3));
            } else if(btnShip4.isSelected() && (cntShip4 > 0) && !(gameField.getStateCell(rowIndex, colIndex) == StateCell.BORDER) && borderCheck(4, colIndex, rowIndex, stateOrientation)){
                gameField.addShip(typeShipUI, stateOrientation, colIndex, rowIndex);
                cntShip4--;
                labelCntShip4.setText(String.valueOf(cntShip4));
            } else if(btnMine.isSelected() && (cntMine > 0) && !(gameField.getStateCell(rowIndex, colIndex) == StateCell.BORDER)){
                gameField.addShip(typeShipUI, stateOrientation, colIndex, rowIndex);
                cntMine--;
                labelCntMine.setText(String.valueOf(cntMine));
            } else if(btnMineSearcher.isSelected() && (cntMineSearcher > 0) && !(gameField.getStateCell(rowIndex, colIndex) == StateCell.BORDER)){
                gameField.addShip(typeShipUI, stateOrientation, colIndex, rowIndex);
                cntMineSearcher--;
                labelCntMineSearcher.setText(String.valueOf(cntMineSearcher));
            } else if(btnSubmarine.isSelected() && (cntSubmarine > 0) && !(gameField.getStateCell(rowIndex, colIndex) == StateCell.BORDER)){
                gameField.addShip(typeShipUI, stateOrientation, colIndex, rowIndex);
                cntSubmarine--;
                labelCntSubmarine.setText(String.valueOf(cntSubmarine));
            }
            System.out.println(colIndex + " " + rowIndex);
        }
        mainGridPane.getChildren().clear(); // Очистить все дочерние элементы из mainGridPane
        for (int row = 0; row < SIZE_FIELD; row++) {
            for (int col = 0; col < SIZE_FIELD; col++) {
                CellView cellView = new CellView(gameField.cells[row][col]);
                mainGridPane.add(cellView, col, row);
            }
        }
    }
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
