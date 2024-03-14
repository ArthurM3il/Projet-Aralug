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

public class MainMenu extends Application {

    private final Utils utils = new Utils();

    @Override
    public void start(Stage primaryStage) {
        //Récupération de la taille de l'écran
        Rectangle2D screenSize = utils.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();
        MusicPlayer.loadMusic("assets/voices/EspacePourCommencer.wav");

        // Création du texte
        String message = "Appuyez sur espace pour commencer";
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
        primaryStage.setOnShown(event -> MusicPlayer.playMusic());
        primaryStage.setTitle("Menu Principal");
        primaryStage.show();

        //Initialisation du lecteur de texte SI_VOX
        LecteurTexte lecteur = new LecteurTexte();
        lecteur.setTexte(message);


        // Définir le raccourci clavier pour démarrer le jeu
        scene.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                SelectionMenu menuSelection = new SelectionMenu();
                Stage stage = new Stage();
                menuSelection.start(stage);
                primaryStage.close();
                System.out.println("Début du jeu !");
            }else lecteur.play();

        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
