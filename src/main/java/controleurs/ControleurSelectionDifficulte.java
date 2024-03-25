package controleurs;

import elements.Difficulte;
import elements.Musique;
import javafx.stage.Stage;
import modeles.ModeleSelectionDifficulte;
import vues.VueJeu;
import vues.VueSelectionDifficulte;


public class ControleurSelectionDifficulte {

    private static Stage stage;

    public ControleurSelectionDifficulte(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public static void changerVue(Stage stage, Musique musique, int difficulte) {
        VueJeu vueJeu = new VueJeu(stage, musique, difficulte);
        stage.getScene().setRoot(vueJeu.getUI());
    }
}
