package controleurs;

import javafx.stage.Stage;
import vues.VueMenuPrincipal;

public class ControleurScore {


    private static Stage stage;

    public ControleurScore(Stage primaryStage) {
        this.stage = primaryStage;

    }

    public static void changerVue(Stage stage) {
        VueMenuPrincipal vueMenuPrincipal = new VueMenuPrincipal(stage);
        stage.getScene().setRoot(vueMenuPrincipal.getUI());
    }
}
