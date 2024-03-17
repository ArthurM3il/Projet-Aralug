package game;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.DataFormat;
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

    private static Difficulte difficulte;

    private static int choixDifficulte;
    private static Music music;

    public SelectionDifficulte(Music music) {
        this.music = music;
    }
    public void start(Stage primaryStage) {

        choixDifficulte = 0;
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
                Jeu jeu = new Jeu(music, getNombreDifficulte());
                Stage stage = new Stage();
                jeu.start(stage);
                primaryStage.close();
                System.out.println("Début du jeu !");
            } else if (event.getCode().equals(KeyCode.RIGHT)){
                changerDifficulte(1, text, message, lecteur);
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                changerDifficulte(0, text, message, lecteur);
            }
        });
    }

    public void changerDifficulte(int changement, Text text, String message, LecteurTexte lecteur) {
        if (changement == 1) {
            choixDifficulte++;
        } else if (changement == 0) {
            choixDifficulte--;
        }
        if (choixDifficulte > 2) {
            choixDifficulte = 0;
        } else if (choixDifficulte < 0) {
            choixDifficulte = 2;
        }

        message = "Difficulté " + getNomDifficulte();
        text.setText(message);
        lecteur.setTexte(message);
        lecteur.play();
    }

    public String getNomDifficulte() {
        if (choixDifficulte == 0) {
            return Difficulte.FACILE.getNom();
        } else if (choixDifficulte == 1) {
            return Difficulte.MOYEN.getNom();
        } else {
            return Difficulte.DIFFICILE.getNom();
        }
    }

    public int getNombreDifficulte() {
        if (choixDifficulte == 0) {
            return Difficulte.FACILE.getNombreErreurCumuluees();
        } else if (choixDifficulte == 1) {
            return Difficulte.MOYEN.getNombreErreurCumuluees();
        } else {
            return Difficulte.DIFFICILE.getNombreErreurCumuluees();
        }
    }
}