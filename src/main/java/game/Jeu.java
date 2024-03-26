package game;

import elements.Musique;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;
import utils.LecteurMusique;
import utils.Utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Jeu extends Application {

    private static final double CIRCLE_RADIUS = 50;
    private ScaleTransition scaleTransition;
    private final Circle referenceCircle = createCircle(100, 200);
    private final Circle playerCircle = createCircle(400, 200);
    private static final double SCALE_FACTOR = 1.5;
    private final Musique musique;
    private final Utils utils = new Utils();
    private static Timeline gameTimeline;

    private static Timeline erreurTimeLine;
    private long startTimeReferenceCircle = 0;
    private long startTimePlayerCircle = 0;
    private static int score;

    private static int erreurCumulees = 0;

    private static int difficulte;

    private static Stage primaryStage;
    private static Thread musicThread;

    public Jeu(Musique musique, int difficulte) {
        this.musique = musique;
        this.difficulte = difficulte;
    }

    @Override
    public void start(Stage primaryStage) {
        Pane root = new Pane();
        score = 0;
        erreurCumulees = 0;
        //Récupération de la taille de l'écran
        Rectangle2D screenSize = utils.getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        // Ajouter les cercles au panneau racine
        root.getChildren().addAll(playerCircle);

        //initBeatAnimations(referenceCircle);

        //Ajuster la fenêtre à la taille de l'écran
        primaryStage.setX(screenSize.getMinX());
        primaryStage.setY(screenSize.getMinY());
        primaryStage.setWidth(screenWidth);
        primaryStage.setHeight(screenHeight);

        // Créer la scène et afficher la fenêtre
        Scene scene = new Scene(root, 600, 400, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Circles");
        this.primaryStage = primaryStage;
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> LecteurMusique.stopMusic());

        scene.widthProperty().addListener((obs, oldVal, newVal) -> utils.updateAndCenterCircle(referenceCircle, playerCircle, scene));
        scene.heightProperty().addListener((obs, oldVal, newVal) -> utils.updateAndCenterCircle(referenceCircle, playerCircle, scene));
        utils.updateAndCenterCircle(referenceCircle, playerCircle,scene);

        //Charger la musique
        LecteurMusique.loadMusic(musique.getPath());


        // Caler les battements du cercle sur le tempo de la musique
            gameTimeline = new Timeline(new javafx.animation.KeyFrame(Duration.seconds(60.0 / musique.getBpm()), event -> {
            musicThread = new Thread(LecteurMusique::playMusic);
            musicThread.start();
            initBeatAnimations(referenceCircle);
            scaleTransition.playFromStart();
        }));
        gameTimeline.setCycleCount(Timeline.INDEFINITE);
        gameTimeline.play();
        gameTimeline.setOnFinished(actionEvent -> {
            scaleTransition.stop();

        });


        // Mettre à jour le deuxième cercle lorsqu'on appuie sur la barre d'espace
        root.setOnKeyPressed(event -> {
            if (erreurCumulees >= difficulte) {
                LecteurMusique.sonDefaite();
                defaite();
            } else if (event.getCode().toString().equals("SPACE") && gameTimeline.getStatus() == Animation.Status.RUNNING) {
                beatCircle();
                score += calculScore();
                System.out.println(calculScore() + " " + erreurCumulees + " / " + difficulte);
            }else if (event.getCode().toString().equals("SPACE") && gameTimeline.getStatus() == Animation.Status.STOPPED){
                Score scoreWindow = new Score(score);
                Stage stage = new Stage();
                try {
                    scoreWindow.start(stage);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                primaryStage.close();
            } else if (event.getCode().equals(KeyCode.ESCAPE)) {
                defaite();
            }
        });

        // Assurer que le panneau racine a le focus pour que les événements clavier soient capturés
        root.requestFocus();
    }

    // Méthode utilitaire pour créer un cercle
    private Circle createCircle(double x, double y) {
        return new Circle(x, y, CIRCLE_RADIUS, Color.YELLOW);
    }

    private void initBeatAnimations(Circle circle){
        startTimeReferenceCircle = 0;
        this.scaleTransition = new ScaleTransition(Duration.seconds(0.15), circle);
        // Configurer la transition
        this.scaleTransition.setFromX(1.0);
        this.scaleTransition.setFromY(1.0);
        this.scaleTransition.setToX(SCALE_FACTOR);
        this.scaleTransition.setToY(SCALE_FACTOR);
        this.scaleTransition.setAutoReverse(true);
        this.scaleTransition.setCycleCount(2);
        startTimeReferenceCircle = System.currentTimeMillis();
    }

    private void beatCircle() {
        startTimePlayerCircle = 0;
        ScaleTransition scaleTransition = new ScaleTransition(Duration.seconds(0.15), playerCircle);
        scaleTransition.setFromX(1.0);
        scaleTransition.setFromY(1.0);
        scaleTransition.setToX(SCALE_FACTOR);
        scaleTransition.setToY(SCALE_FACTOR);
        scaleTransition.setAutoReverse(true);
        scaleTransition.setCycleCount(2); // Effectuer un battement complet (aller-retour)
        startTimePlayerCircle = System.currentTimeMillis();
        scaleTransition.play();
    }

    public static void endTimeline(){
        gameTimeline.stop();
    }


    public long getDifference(){
        return startTimePlayerCircle- startTimeReferenceCircle;
    }

    public double calculScore(){
        if(getDifference() <= 100){
            erreurCumulees = 0;
            return 10.0;
        }else{
            erreurCumulees++;
            lancerErreur();
            return Math.round(getDifference()/1000.0 * 10/(0.1- (60.0/ musique.getBpm())) + 12.24);//PRENDRE EN COMPTE LE FAIT DAPPUYER TROP TOT
        }
    }

    public void defaite() {
        LecteurMusique.stopMusic();
        Score scoreWindow = new Score(score);
        Stage stage = new Stage();
        try {
            scoreWindow.start(stage);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        primaryStage.close();
    }

    public void lancerErreur(){
        new Thread(LecteurMusique::sonErreur).start();
    }

}
