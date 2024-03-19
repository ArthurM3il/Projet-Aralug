package game;

import elements.Musique;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import t2s.son.LecteurTexte;
import utils.Utils;

import java.util.ArrayList;

public class SelectionMusique extends Application {

    private final Utils utils = new Utils();

    private int indexMenu;

    @Override
    public void start(Stage primaryStage) {
        indexMenu = 0;

        //Récupération de la taille de l'écran
        Rectangle2D screenSize = utils.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        ArrayList<Musique> musiques = new ArrayList<>();
        musiques.add(Musique.DP_INSTANTCRUSH);
        musiques.add(Musique.JUL_LAZONE);
        // Création du texte
        String message = new String("Sélectionnez une musique");
        Text text = new Text(message);
        Font luciole = Font.loadFont("file:assets/fonts/Luciole-Bold.ttf",screenWidth * 0.05);
        text.setFont(luciole);
        text.setFill(Color.YELLOW);

        // Création du conteneur pour centrer le texte
        StackPane root = new StackPane();
        root.getChildren().add(text);
        root.setStyle("-fx-background-color: black;");
        StackPane.setAlignment(text, Pos.CENTER);

        //Scaling du texte selon la taille de le fenêtre
        text.setWrappingWidth(screenWidth * 0.8);

        // Création de la scène
        Scene scene = new Scene(root);

        //Ajuster la fenêtre à la taille de l'écran
        primaryStage.setX(screenSize.getMinX());
        primaryStage.setY(screenSize.getMinY());
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);

        // Ajouter un écouteur pour détecter les changements de taille de la fenêtre
        scene.widthProperty().addListener((obs, oldVal, newVal) -> utils.updateAndCenterText(text, scene));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> utils.updateAndCenterText(text, scene));

        primaryStage.setScene(scene);
        primaryStage.setTitle("Sélection musique");
        primaryStage.show();

        //Initialisation du lecteur de texte SI_VOX
        LecteurTexte lecteur = new LecteurTexte();
        lecteur.setTexte(message);

        lecteur.play();
        lireTitreMusique(musiques, text, lecteur);
        // Définir le raccourci clavier pour démarrer le jeu
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                SelectionDifficulte selectionDifficulte = new SelectionDifficulte(musiques.get(indexMenu));
                Stage stage = new Stage();
                selectionDifficulte.start(stage);
                primaryStage.close();
            } else if (event.getCode().equals(KeyCode.RIGHT)){
                indexMenu = Utils.changerIndex(Utils.DROITE, musiques.size(), indexMenu);
                lireTitreMusique(musiques, text, lecteur);
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                indexMenu = Utils.changerIndex(Utils.GAUCHE, musiques.size(), indexMenu);
                lireTitreMusique(musiques, text, lecteur);
            } else {
                lecteur.play();
            }
        });
    }

    public void lireTitreMusique(ArrayList<Musique> musiques, Text text, LecteurTexte lecteur) {
        String message = musiques.get(this.indexMenu).getTitre();
        text.setText(message);
        lecteur.setTexte(message);
        lecteur.play();
    }
}
