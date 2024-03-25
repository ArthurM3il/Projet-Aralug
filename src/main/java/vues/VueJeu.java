package vues;

import controleurs.ControleurJeu;
import controleurs.ControleurSelectionMusique;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class VueJeu {
    private Pane ui;
    private Label label;

    private double largeurEcran;

    private double hauteurEcran;
    public VueJeu(Stage stage) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        label = new Label("Appuyer sur la touche espace pour accéder à l'écran suivant et choisir une musique");
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        changerScene(stage);
    }
    public Pane getUI() {
        return ui;
    }

    private void changerScene(Stage stage) {
        stage.getScene().setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                System.out.println("Vue jeu changement vers score");
                ControleurJeu.changerVue(stage);
            }
        });
    }

    public double getLargeurEcran() {
        return largeurEcran;
    }

    public double getHauteurEcran() {
        return hauteurEcran;
    }
}
