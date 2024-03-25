package controleurs;

import javafx.stage.Stage;
import modeles.ModeleJeu;
import modeles.ModeleSelectionDifficulte;
import vues.VueJeu;
import vues.VueScore;
import vues.VueSelectionDifficulte;

public class ControleurJeu {

    Stage stage;
    public ControleurJeu(Stage primaryStage) {
        this.stage = primaryStage;
    }
    public static void changerVue(Stage stage) {
        VueScore vueScore= new VueScore(stage);
        stage.getScene().setRoot(vueScore.getUI());
    }
}
