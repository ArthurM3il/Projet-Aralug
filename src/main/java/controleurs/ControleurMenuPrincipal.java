package controleurs;

import javafx.stage.Stage;
import modeles.ModeleMenuPrincipal;
import vues.VueMenuPrincipal;
import vues.VueSelectionMusique;

public class ControleurMenuPrincipal {



    private static Stage stage;

    public ControleurMenuPrincipal(Stage primaryStage) {
        this.stage = primaryStage;

    }

    public static void changerVue(Stage stage) {
        VueSelectionMusique vueSelectionMusique = new VueSelectionMusique(stage);
        stage.getScene().setRoot(vueSelectionMusique.getUI());
    }
}
