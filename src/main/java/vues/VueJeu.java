package vues;

import controleurs.ControleurJeu;
import elements.Musique;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.LecteurMusique;
import utils.Utils;

import javax.swing.plaf.multi.MultiInternalFrameUI;
import java.awt.*;

public class VueJeu {
    private Pane ui;

    private final Circle cercleJoueur;

    private final Circle cercleReference;

    private static Timeline gameTimeline;

    private ScaleTransition scaleTransition;

    private long startTimeReferenceCircle = 0;
    private long startTimePlayerCircle = 0;

    private static int erreurCumulees;

    private int score;

    /**
     * Constructeur de la classe VueJeu.
     *
     * @param stage      Stage principal de l'application.
     * @param musique    Instance de la classe Musique.
     * @param difficulte Niveau de difficulté du jeu.
     */
    public VueJeu(Stage stage, Musique musique, int difficulte) {
        new Label("Appuyer sur la touche espace pour accéder à l'écran suivant et choisir une musique");
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        erreurCumulees = 0;
        score = 0;
        LecteurMusique.loadMusic(musique.getPath());
        changerScene(stage, musique, difficulte);
        cercleJoueur = new Circle(400, 200, 50, Color.YELLOW);
        cercleReference = new Circle(100, 200, 50, Color.YELLOW);
        ui.getChildren().addAll(cercleJoueur);
        chargerCercles(stage);
        chargerTimeline(musique);
        lancerMusique(musique);
    }

    public static void endTimeline() {
        gameTimeline.stop();
    }

    public Pane getUI() {
        return ui;
    }

    /**
     * Gestion des key listener et du cas de la défaite du joueur si trop d'erreur ont été cumulées
     * @param stage
     * @param musique
     * @param difficulte
     */
    private void changerScene(Stage stage, Musique musique, int difficulte) {
        stage.getScene().setOnKeyReleased(event -> {
            if (erreurCumulees >= difficulte) {
                LecteurMusique.sonDefaite();
                LecteurMusique.stopMusic();
                ControleurJeu.changerVue(stage, score);
            } else if (event.getCode().toString().equals("SPACE") && gameTimeline.getStatus() == Animation.Status.RUNNING) {
                beatCircle();
                score += (int) calculScore(getDifference(), musique);
                System.out.println("Difference : " + getDifference() + " Score : " + (int)calculScore(getDifference(), musique) + " Nombre d'erreur : " + erreurCumulees + " / " + difficulte);
            }else if (event.getCode().toString().equals("SPACE") && gameTimeline.getStatus() == Animation.Status.STOPPED){
                System.out.println("Vue jeu changement vers score");
                LecteurMusique.stopMusic();
                ControleurJeu.changerVue(stage, score);
            } else if (event.getCode().equals(KeyCode.ESCAPE)) {
                LecteurMusique.stopMusic();
                ControleurJeu.changerVue(stage, score);
            }
        });
    }

    /**
     * Initialise la timeline du jeu.
     *
     * @param musique Instance de la classe Musique.
     */
    public void chargerTimeline(Musique musique) {
        gameTimeline = new Timeline(new javafx.animation.KeyFrame(Duration.seconds(60.0 / musique.getBpm()), event -> {
            initBeatAnimations(cercleReference);
            scaleTransition.playFromStart();
        }));
        gameTimeline.setCycleCount(Timeline.INDEFINITE);
        gameTimeline.play();
        gameTimeline.setOnFinished(actionEvent -> {
            scaleTransition.stop();

        });
    }

    /**
     * Charge les cercles du jeu.
     *
     * @param stage Stage principal de l'application.
     */
    public void chargerCercles(Stage stage) {
        stage.getScene().widthProperty().addListener((obs, oldVal, newVal) -> Utils.updateAndCenterCircle(cercleReference, cercleJoueur, stage.getScene()));
        stage.getScene().heightProperty().addListener((obs, oldVal, newVal) -> Utils.updateAndCenterCircle(cercleReference, cercleJoueur, stage.getScene()));
        Utils.updateAndCenterCircle(cercleReference, cercleJoueur,stage.getScene());
    }

    /**
     * Gère l'animation du cercle donné en paramètre
     * @param circle
     */
    private void initBeatAnimations(Circle circle){
        startTimeReferenceCircle = 0;
        this.scaleTransition = new ScaleTransition(Duration.seconds(0.15), circle);
        // Configurer la transition
        this.scaleTransition.setFromX(1.0);
        this.scaleTransition.setFromY(1.0);
        this.scaleTransition.setToX(1.5);
        this.scaleTransition.setToY(1.5);
        this.scaleTransition.setAutoReverse(true);
        this.scaleTransition.setCycleCount(2);
        startTimeReferenceCircle = System.currentTimeMillis();
    }

    /**
     * Anime le cercle du joueur
     */
    private void beatCircle() {
        startTimePlayerCircle = 0;
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15), cercleJoueur);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(1.5);
        scaleTransition.setToY(1.5);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2); // Effectuer un battement complet (aller-retour)
        startTimePlayerCircle = System.currentTimeMillis();
        scaleTransition.play();
    }

    public long getDifference(){
        return startTimePlayerCircle- startTimeReferenceCircle;
    }

    /**
     * Calcule le score du joueur en fonction de la différence entre les temps des cercles.
     *
     * @param difference Différence entre les temps des cercles.
     * @param musique    Instance de la classe Musique.
     * @return Le score calculé.
     */
    public double calculScore(long difference, Musique musique){
        double battement = (double) 60 /musique.getBpm()*1000;
        if(difference <= 100){
            erreurCumulees = 0;
            return 10.0;
        }else {
            erreurCumulees++;
            lancerErreur();
            double coefDirecteur = (-20/(battement-100));
            double ordonneeALorigine = (10 + (2000/(battement-100)));
            return Math.abs(coefDirecteur*difference + ordonneeALorigine);
        }
    }

    public void lancerErreur(){
        new Thread(LecteurMusique::sonErreur).start();
    }

    /**
     * Lance la musique du jeu.
     *
     * @param musique Instance de la classe Musique.
     */
    public void lancerMusique(Musique musique){
        new Thread(() -> {
            LecteurMusique.playMusic((double) 60 / musique.getBpm() * 1000);
        }).start();

    }

}
