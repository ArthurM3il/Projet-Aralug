package controleurs;

import javafx.stage.Stage;
import vues.VueScore;

public class ControleurJeu {

    private Stage stage;
    public ControleurJeu(Stage primaryStage) {
        this.stage = primaryStage;
    }
    public static void changerVue(Stage stage, int score) {
        VueScore vueScore = new VueScore(stage, score);
        stage.getScene().setRoot(vueScore.getUI());
    }
}
