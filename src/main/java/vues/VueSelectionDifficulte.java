package vues;

import controleurs.ControleurMenuPrincipal;
import controleurs.ControleurSelectionDifficulte;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class VueSelectionDifficulte {

    private Pane ui;
    private Label label;

    private double largeurEcran;

    private double hauteurEcran;

    public VueSelectionDifficulte(Stage stage) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        label = new Label("Appuyer sur la touche espace pour accéder à l'écran suivant et jouer");
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        changerScene(stage);
    }

    public void afficherTexte(String texte) {
        Text text = new Text(texte);
        text.setFill(Color.YELLOW);
        double taillePolice = Math.min(largeurEcran, hauteurEcran) / 15; // Taille de police proportionnelle à la taille de l'écran
        text.setFont(javafx.scene.text.Font.loadFont(getClass().getResourceAsStream("Luciole.ttf"), taillePolice));
        text.setFont(Font.loadFont("file:assets/fonts/Luciole-Bold.ttf",60));
        text.setX((largeurEcran - text.getLayoutBounds().getWidth()) / 2);
        text.setY((hauteurEcran - text.getLayoutBounds().getHeight()) / 2);
        ui.getChildren().add(text);
    }

    public Pane getUI() {
        return ui;
    }

    private void changerScene(Stage stage) {
        stage.getScene().setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                System.out.println("Vue selection difficulte changement vers jeu");
                ControleurSelectionDifficulte.changerVue(stage);
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
