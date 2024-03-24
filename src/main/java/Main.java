import controleurs.ControleurMenuPrincipal;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import modeles.ModeleMenuPrincipal;
import utils.Utils;
import vues.VueMenuPrincipal;

public class Main extends Application {

    private final Utils utils = new Utils();

    @Override
    public void start(Stage primaryStage) throws Exception {
        Rectangle2D screenSize = utils.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        ModeleMenuPrincipal modele = new ModeleMenuPrincipal("Appuyer sur la touche espace pour accéder à l'écran suivant et choisir une musique");
        VueMenuPrincipal vue = new VueMenuPrincipal(screenWidth, screenHeight);
        ControleurMenuPrincipal controleur = new ControleurMenuPrincipal(modele, vue);

        Scene scene = new Scene(vue.getUI(), screenWidth, screenHeight);

        //primaryStage.setFullScreen(true);
        primaryStage.setTitle("Aralug");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
