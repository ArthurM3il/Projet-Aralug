package vues;

import javafx.scene.layout.Pane;

import java.awt.*;

public class VueSelectionMenu {

    private Pane ui;
    private Label label;

    public VueSelectionMenu() {
        label = new Label();
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
    }

    public Pane getUI() {
        return ui;
    }
}
