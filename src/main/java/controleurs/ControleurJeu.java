package controleurs;

import javafx.stage.Stage;
import vues.VueScore;

public class ControleurJeu {

    private Stage stage;
    public ControleurJeu(Stage primaryStage) {
        this.stage = primaryStage;
    }
    public static void changerVue(Stage stage, int score, int difficulte) {
        VueScore vueScore = new VueScore(stage, score, difficulte);
        stage.getScene().setRoot(vueScore.getUI());
    }
}
