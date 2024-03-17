package game;

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

public class SelectionDifficulte extends Application {
    private final Utils utils = new Utils();

    private static int difficulte = 0;

    private static Music music;

    public SelectionDifficulte(Music music) {
        this.music = music;
    }
    public void start(Stage primaryStage) {
        //Récupération de la taille de l'écran
        Rectangle2D screenSize = utils.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        // Création du texte
        String message = new String("Difficulté facile");
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
        primaryStage.setTitle("Sélection difficulté");
        primaryStage.show();

        //Initialisation du lecteur de texte SI_VOX
        LecteurTexte lecteur = new LecteurTexte();
        lecteur.setTexte(message);

        primaryStage.setOnShown(windowEvent -> lecteur.play());


        // Définir le raccourci clavier pour démarrer le jeu
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                Jeu jeu = new Jeu(music, difficulte);
                Stage stage = new Stage();
                jeu.start(stage);
                primaryStage.close();
                System.out.println("Début du jeu !");
            } else if (event.getCode().equals(KeyCode.RIGHT)){
                changerDiffuculte(1, text, message, lecteur);
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                changerDiffuculte(0, text, message, lecteur);
            }
        });
    }

    public void changerDiffuculte(int changement, Text text, String message, LecteurTexte lecteur) {
        if (changement == 1) {
            this.difficulte++;
        } else if (changement == 0) {
            this.difficulte--;
        }
        if (difficulte > 2) {
            this.difficulte = 0;
        } else if (difficulte < 0) {
            this.difficulte = 2;
        }
        if (difficulte == 2) {
            message = "Difficulté difficile";
        } else if (difficulte == 1) {
            message = "Difficulté moyen";
        } else if (difficulte == 0) {
            message = "Difficulté facile";
        }
        System.out.println(difficulte);
        text.setText(message);
        lecteur.setTexte(message);
        lecteur.play();
    }
}
