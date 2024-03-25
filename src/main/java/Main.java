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

public class Main extends Application {

    private final Utils utils = new Utils();

    @Override
    public void start(Stage primaryStage) throws Exception {

        Rectangle2D screenSize = utils.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        Pane root = new Pane();
        Scene scene = new Scene(root, screenWidth, screenHeight);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aralug");
        VueMenuPrincipal vue = new VueMenuPrincipal(primaryStage);
        primaryStage.getScene().setRoot(vue.getUI());
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
