package controleurs;

import javafx.stage.Stage;
import modeles.ModeleSelectionDifficulte;
import vues.VueJeu;
import vues.VueSelectionDifficulte;


public class ControleurSelectionDifficulte {

    private static Stage stage;

    public ControleurSelectionDifficulte(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public static void changerVue(Stage stage) {
        VueJeu vueJeu = new VueJeu(stage);
        stage.getScene().setRoot(vueJeu.getUI());
    }
}
