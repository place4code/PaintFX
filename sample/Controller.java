package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {

    @FXML private Canvas canvas_id;
    @FXML private ColorPicker color_picker;
    private GraphicsContext gc;
    private int xPos, yPos;
    private int workshop;
    private Color color;

    @FXML protected void closeClick(ActionEvent event) {
        Platform.exit();
    }

    @FXML void initialize() {
        color = Color.BLACK;
        color_picker.setValue(color);
        workshop = 1;
        gc = canvas_id.getGraphicsContext2D();
    }

    @FXML protected void lineClick(ActionEvent event) {
        workshop = 1;
        System.out.println("workshop: " + workshop);
    }

    @FXML protected void circleClick(ActionEvent event) {
        workshop = 2;
        System.out.println("workshop: " + workshop);
    }

    @FXML protected void squareClick(ActionEvent event) {
        workshop = 3;
        System.out.println("workshop: " + workshop);
    }

    @FXML protected void setColor() {
        gc.setStroke(color = color_picker.getValue());
    }

    @FXML protected void mouseClick(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            xPos = (int)event.getX();
            yPos = (int)event.getY();
        }
        System.out.println(xPos + ", " + yPos);
    }

    @FXML protected void mouseRelease(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY) {
            if (workshop == 1) {
                line((int)event.getX(), (int)event.getY());
            }
            if (workshop == 2) {
                oval((int)event.getX() - xPos, (int)event.getY() - yPos);
            }
            if (workshop == 3) {
                rect((int)event.getX() - xPos, (int)event.getY() - yPos);
            }
        }
    }

    private void line(int x2, int y2) {
        gc.strokeLine(xPos, yPos, x2, y2);
    }

    private void oval(int w, int h) {
        gc.strokeOval(xPos, yPos, w, h);
    }

    private void rect(int w, int h) {
        gc.strokeRect(xPos, yPos, w, h);
    }

}

