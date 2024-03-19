package utils;

import elements.Musique;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

import java.util.ArrayList;

public class Utils {

    public static final String DROITE = "DROITE";
    public static final String GAUCHE = "GAUCHE";
    private final Rectangle2D screenSize;

    public Utils(){
        screenSize = Screen.getPrimary().getVisualBounds();;
    }

    public Rectangle2D getScreenSize(){
        return screenSize;
    }

    public void updateTextSize(Text text, Scene scene) {
        double screenWidth = scene.getWidth();

        // Calculer la taille de la police en fonction de la taille de la fenêtre
        double fontSize = screenWidth * 0.05;

        Font luciole = Font.loadFont("file:assets/fonts/Luciole-Bold.ttf",fontSize);
        text.setFont(luciole);

        // Mettre à jour la largeur maximale du texte
        text.setWrappingWidth(screenWidth * 0.8);
    }

    public void updateAndCenterText(Text text, Scene scene){
        updateTextSize(text, scene);
        StackPane.setAlignment(text, Pos.CENTER);
    }

    public void updateCircleSize(Circle referenceCircle,Circle playerCircle, Scene scene){
        double radius = (scene.getWidth() + scene.getHeight() )/ 10.0 ;
        referenceCircle.setRadius(radius);
        playerCircle.setRadius(radius);
    }

    public void updateAndCenterCircle(Circle referenceCircle, Circle playerCircle, Scene scene){
        updateCircleSize(referenceCircle,playerCircle,scene);
        //Centrer les cercles
        referenceCircle.centerXProperty().bind(scene.widthProperty().divide(4));
        referenceCircle.centerYProperty().bind(scene.heightProperty().divide(2));
        playerCircle.centerXProperty().bind(scene.widthProperty().multiply(3).divide(4));
        playerCircle.centerYProperty().bind(scene.heightProperty().divide(2));
    }

    public static int changerIndex(String changement, int taille, int indexMenu) {
        if (DROITE.equals(changement)) {
            indexMenu++;
        } else if (GAUCHE.equals(changement)) {
            indexMenu--;
        }
        if (indexMenu >= taille) {
            indexMenu = 0;
        } else if (indexMenu < 0) {
            indexMenu = taille;
        }
        return indexMenu;
    }
}
