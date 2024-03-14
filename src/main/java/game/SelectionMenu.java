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
import utils.MusicPlayer;
import utils.Utils;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SelectionMenu extends Application {

    private final Utils utils = new Utils();

    private AtomicInteger indexMenu;

    @Override
    public void start(Stage primaryStage) {
        //Récupération de la taille de l'écran
        Rectangle2D screenSize = utils.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        ArrayList<Music> musiques = new ArrayList<>();
        musiques.add(Music.DP_INSTANTCRUSH);
        musiques.add(Music.JUL_LAZONE);
        indexMenu = new AtomicInteger();
        // Création du texte
        AtomicReference<String> message = new AtomicReference<>(musiques.get(indexMenu.get()).getTitre());
        Text text = new Text(message.get());
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
        lecteur.setTexte(message.get());

        primaryStage.setOnShown(windowEvent -> lecteur.play());

        Music musiqueChoisis = musiques.get(indexMenu.get());
        // Définir le raccourci clavier pour démarrer le jeu
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                Jeu jeu = new Jeu(musiqueChoisis);
                Stage stage = new Stage();
                jeu.start(stage);
                primaryStage.close();
                System.out.println("Début du jeu !");
            } else if (event.getCode().equals(KeyCode.RIGHT)){
                changerMusique(1, indexMenu, musiques, text, message, lecteur);
            } else if (event.getCode().equals(KeyCode.LEFT)) {
                changerMusique(0, indexMenu, musiques, text, message, lecteur);
            } else {
                lecteur.play();
            }
        });
    }

    public void changerMusique(int changement, AtomicInteger indexMenu, ArrayList<Music> musiques, Text text, AtomicReference<String> message, LecteurTexte lecteur) {
        if (changement == 1) {
            this.indexMenu.getAndIncrement();
        } else if (changement == 0) {
            this.indexMenu.getAndDecrement();
        }
        if (indexMenu.get() >= musiques.size()) {
            indexMenu.set(0);
        } else if (indexMenu.get() < 0) {
            indexMenu.set(musiques.size());
        }
        message.set(musiques.get(indexMenu.get()).getTitre());
        lecteur.setTexte(message.get());
        text.setText(message.get());
        lecteur.play();
        MusicPlayer.loadMusic(musiques.get(indexMenu.get()).getPath());
    }
}
