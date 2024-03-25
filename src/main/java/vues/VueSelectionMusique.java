package vues;

import controleurs.ControleurMenuPrincipal;
import controleurs.ControleurSelectionMusique;
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
import java.util.ArrayList;

public class VueSelectionMusique {

    private Pane ui;
    private Text texte;

    private double largeurEcran;

    private double hauteurEcran;

    private int indexMenu;

    private LecteurTexte lecteurTexte;

    public VueSelectionMusique(Stage stage) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        ui = new Pane();

        lecteurTexte = new LecteurTexte();
        lecteurTexte.setTexte("Sélectionnez une musique");
        lecteurTexte.play();
        ui.setStyle("-fx-background-color: black;");
        indexMenu = 0;
        ArrayList<Musique> musiques = new ArrayList<>();
        musiques.add(Musique.DP_INSTANTCRUSH);
        musiques.add(Musique.JUL_LAZONE);
        texte = new Text(musiques.get(0).getTitre());
        changerScene(stage, musiques.get(indexMenu), musiques);
        afficherTexte(texte.getText());
    }

    public void afficherTexte(String chaine) {
        lecteurTexte.setTexte(chaine);
        lecteurTexte.play();
        texte.setText(chaine);
        texte.setFill(Color.YELLOW);
        texte.setFont(Font.loadFont("file:assets/fonts/Luciole-Bold.ttf",60));
        texte.setX((largeurEcran - texte.getLayoutBounds().getWidth()) / 2);
        texte.setY((hauteurEcran - texte.getLayoutBounds().getHeight()) / 2);
        ui.getChildren().add(texte);
    }

    public Pane getUI() {
        return ui;
    }

    private void changerScene(Stage stage, Musique musique, ArrayList<Musique> musiques) {
        stage.getScene().setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                System.out.println("Vue selection musique changement vers selection difficulte");
                ControleurSelectionMusique.changerVue(stage, musique);
            } else if (event.getCode().equals(KeyCode.RIGHT)) {
                indexMenu = Utils.changerIndex(Utils.DROITE, musiques.size(), indexMenu);
                afficherTexte(musiques.get(indexMenu).getTitre());
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                indexMenu = Utils.changerIndex(Utils.GAUCHE, musiques.size(), indexMenu);
                afficherTexte(musiques.get(indexMenu).getTitre());
            }
        });
    }
}
