import controleurs.ControleurMenuPrincipal;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import modeles.ModeleMenuPrincipal;
import utils.Utils;
import vues.VueMenuPrincipal;

import javax.swing.*;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) {
        VueMenuPrincipal vue = new VueMenuPrincipal(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
