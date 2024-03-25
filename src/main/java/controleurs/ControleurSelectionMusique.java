package controleurs;

import javafx.stage.Stage;
import modeles.ModeleMenuPrincipal;
import modeles.ModeleSelectionMusique;
import vues.VueMenuPrincipal;
import vues.VueSelectionDifficulte;
import vues.VueSelectionMusique;

public class ControleurSelectionMusique {


    private static Stage stage;

    public ControleurSelectionMusique(Stage primaryStage) {
        this.stage = primaryStage;
    }

    public static void changerVue(Stage stage) {
        VueSelectionDifficulte vueSelectionDifficulte = new VueSelectionDifficulte(stage);
        stage.getScene().setRoot(vueSelectionDifficulte.getUI());
    }
}
