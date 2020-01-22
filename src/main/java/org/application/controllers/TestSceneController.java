package org.application.controllers;

import com.mya.colorLab.model.LabField;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import org.application.model.ArrayPairOfIndexes;
import org.application.model.LabFabric;

public class TestSceneController {

    private final int CELL_SIZE = 10;
    private int[][] labArray;


    @FXML
    ToggleGroup labSizeToggleGroup;


    @FXML
    ToggleGroup cellSizeToggleGroup;
    @FXML
    private ScrollPane scrollPane;

    @FXML
    Canvas canvas;

    @FXML
    public void initialize() {
        System.out.println("Scene init");
        System.out.println("Canvas " + canvas);
        final LabFabric instance = LabFabric.getInstance();
        final GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        labArray = new LabField().generateLab(20, 20);
        instance.drawLab(graphicsContext2D, labArray, CELL_SIZE);
        graphicsContext2D.setFill(Color.RED);
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                eventExecutor(event, graphicsContext2D);
            }
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                eventExecutor(event, graphicsContext2D);
            }
        });
    }

    private void eventExecutor(MouseEvent event, GraphicsContext graphicsContext2D) {
        Point2D cS2L = canvas.sceneToLocal(new Point2D(event.getSceneX(), event.getSceneY()));
        double sceneX = cS2L.getX();
        double sceneY = cS2L.getY();
        ArrayPairOfIndexes collision = LabFabric.INSTANCE.isCollision(labArray, sceneX, sceneY, CELL_SIZE);
        if (collision != null) {
            if (labArray[collision.getField()][collision.getColumn()] != 1) {
                if (event.isPrimaryButtonDown()) {
                    LabFabric.INSTANCE.drawSquare(CELL_SIZE, collision.getColumn(), collision.getField(), graphicsContext2D, Color.LIGHTSALMON);
                } else if (event.isSecondaryButtonDown()) {
                    LabFabric.INSTANCE.drawSquare(CELL_SIZE, collision.getColumn(), collision.getField(), graphicsContext2D, Color.GRAY);
                }

            }
        }
    }

    public void restart() {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        Toggle selectedToggle = labSizeToggleGroup.getSelectedToggle();
        Integer width = Integer.valueOf(selectedToggle.getProperties().get("width").toString());
        Integer height = Integer.valueOf(selectedToggle.getProperties().get("height").toString());
        Integer cellSize = Integer.valueOf(cellSizeToggleGroup.getSelectedToggle().getProperties().get("cellSize").toString());
        graphicsContext2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        canvas.setWidth(width*cellSize+cellSize);
        canvas.setHeight(height*cellSize+cellSize);

        LabFabric labFabric = LabFabric.getInstance();
        labArray = new LabField().generateLab(width, height);
        labFabric.drawLab(graphicsContext2D, labArray, Integer.valueOf(cellSize));
    }

    public void about(){
        System.out.println("press");
        Alert dialog = new Alert(Alert.AlertType.INFORMATION);
        dialog.setTitle("about");
        dialog.setHeaderText("Licence");
        dialog.setContentText("Created for Time Killing Only \n Version 0.001");
        dialog.show();
    }


}

