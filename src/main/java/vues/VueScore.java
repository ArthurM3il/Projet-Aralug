package vues;

import controleurs.ControleurScore;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import utils.EcrireRecord;
import utils.LectureRecord;
import utils.LectureScore;

import java.awt.*;

public class VueScore {

    private Pane ui;
    private Label label;

    private double largeurEcran;

    private double hauteurEcran;


    public VueScore(Stage stage, int score, int difficulte) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        label = new Label("Votre score est de " + score);
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        testerRecords(score, difficulte);
        lancerSynthese(score);
        afficherTexte(label.getText());
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

    private void changerScene(Stage stage) {
        stage.getScene().setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                System.out.println("Vue score changement vers menu principal");
                ControleurScore.changerVue(stage);
            }
        });
    }

    public void lancerSynthese(int score){
        new Thread(() -> {
            LectureScore.lireScore(score);
        }).start();
    }


    private void testerRecords(int score, int difficulte) {
        int[] records = LectureRecord.lireRecords();
        if (score > records[difficulte]) {
            EcrireRecord.ecrire(String.valueOf(score), difficulte + 1);
        }
    }
    public Pane getUI() {
        return ui;
    }
}
