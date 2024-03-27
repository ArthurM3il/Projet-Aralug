package vues;

import controleurs.ControleurMenuPrincipal;
import controleurs.ControleurSelectionDifficulte;
import elements.Difficulte;
import elements.Musique;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import t2s.son.LecteurTexte;
import utils.Utils;

import java.awt.*;

public class VueSelectionDifficulte {

    private Pane ui;

    private Text texte;

    private double largeurEcran;

    private double hauteurEcran;

    private int difficulte;

    private LecteurTexte lecteurTexte;

    public VueSelectionDifficulte(Stage stage, Musique musique) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        texte = new Text("Difficulté facile");
        lecteurTexte = new LecteurTexte();
        lecteurTexte.setTexte("Sélectionnez la difficulté");
        stage.setOnShown(event -> lancerSynthese(lecteurTexte));
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        changerScene(stage, musique);
        afficherTexte(texte.getText());
    }

    public void afficherTexte(String chaine) {
        lecteurTexte.setTexte(chaine);
        lancerSynthese(lecteurTexte);
        texte.setText(chaine);
        texte.setFill(Color.YELLOW);
        texte.setWrappingWidth(largeurEcran);
        texte.setFont(Font.loadFont("file:assets/fonts/Luciole-Bold.ttf",largeurEcran * 0.05));
        texte.setX((largeurEcran - texte.getLayoutBounds().getWidth()) / 2);
        texte.setY((hauteurEcran - texte.getLayoutBounds().getHeight()) / 2);
        ui.getChildren().add(texte);
    }

    public Pane getUI() {
        return ui;
    }

    private void changerScene(Stage stage, Musique musique) {
        stage.getScene().setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                System.out.println("Vue selection difficulte changement vers jeu");
                ControleurSelectionDifficulte.changerVue(stage, musique, Difficulte.getNombreDifficulte(difficulte));
            } else if (event.getCode().equals(KeyCode.RIGHT)){
                difficulte = Utils.changerIndex(Utils.DROITE, 3, difficulte);
                afficherTexte("Difficulté " + Difficulte.getNomDifficulte(difficulte));
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                difficulte = Utils.changerIndex(Utils.GAUCHE, 3, difficulte);
                afficherTexte("Difficulté " + Difficulte.getNomDifficulte(difficulte));
            }
        });
    }

    public void lancerSynthese(LecteurTexte lecteur){
        new Thread(() -> {
            lecteur.play();
        }).start();
    }
}
