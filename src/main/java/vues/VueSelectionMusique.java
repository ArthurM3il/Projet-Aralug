package vues;

import controleurs.ControleurMenuPrincipal;
import controleurs.ControleurSelectionMusique;
import elements.Difficulte;
import elements.Musique;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.LectureDifficulte;
import utils.LectureSon;
import utils.Utils;

import java.awt.*;
import java.util.ArrayList;

public class VueSelectionMusique {

    private Pane ui;
    private Text texte;

    private double largeurEcran;

    private double hauteurEcran;

    private int indexMenu;


    public VueSelectionMusique(Stage stage) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        indexMenu = 0;
        lancerSynthese();
        ArrayList<Musique> musiques = new ArrayList<>();
        musiques.add(Musique.DP_INSTANTCRUSH);
        musiques.add(Musique.JUL_LAZONE);
        musiques.add(Musique.SLIMANE_CHEZTOI);
        musiques.add(Musique.LUCENZO_KUDURO);
        musiques.add(Musique.SOOLKING_CASANOVA);
        musiques.add(Musique.SOPRANO_ENFEU);
        musiques.add(Musique.LORIE_MEILLEUREAMIE);
        musiques.add(Musique.LOUANE_SECRET);
        texte = new Text(musiques.get(0).getTitre());
        changerScene(stage, musiques.get(indexMenu), musiques);
        afficherTexte(texte.getText());
    }

    public void afficherTexte(String chaine) {
        texte.setText(chaine);
        texte.setFill(Color.YELLOW);
        texte.setWrappingWidth(largeurEcran);
        texte.setFont(Font.loadFont("file:assets/fonts/Luciole-Bold.ttf",largeurEcran * 0.07));
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
                ControleurSelectionMusique.changerVue(stage, musiques.get(indexMenu));
            } else if (event.getCode().equals(KeyCode.RIGHT)) {
                indexMenu = Utils.changerIndex(Utils.DROITE, musiques.size(), indexMenu);
                LectureSon.titreMusique(indexMenu);
                afficherTexte(musiques.get(indexMenu).getTitre());
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                indexMenu = Utils.changerIndex(Utils.GAUCHE, musiques.size(), indexMenu);
                LectureSon.titreMusique(indexMenu);
                afficherTexte(musiques.get(indexMenu).getTitre());
            }
        });
    }

    public void lancerSynthese(){
        new Thread(LectureSon::choixMusique).start();
    }
}
