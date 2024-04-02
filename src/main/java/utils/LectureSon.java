package utils;

import elements.Difficulte;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class LectureSon {

    private static String[] titres = { "DaftPunkInstantCrush", "JulLaZone", "SlimaneChezToi", "LucenzoDanzaKuduro", "SoolkingCasanova" , "SopranoEnFeu", "LorieMeilleureAmie", "LouaneSecret"};

    public static void sonMenu() {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/voices/EspacePourCommencer.wav").toURI().toString()));
        menuClip.play();
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
    }

    public static void lireRegles() {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/voices/Règles.wav").toURI().toString()));
        menuClip.play();
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
    }

    public static void lireNavigation() {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/voices/Navigation.wav").toURI().toString()));
        menuClip.play();
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
    }

    public static void choixMusique() {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/voices/ChoixMusique.wav").toURI().toString()));
        try {
            menuClip.play();
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
        new Thread(() -> {
            titreMusique(0);
        }).start();
    }

    public static void choixDifficulte(int difficulte) {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/voices/ChoixDifficulte.wav").toURI().toString()));
        try {
            menuClip.play();
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
        new Thread(() -> {
            LectureDifficulte.lireDifficulte(Difficulte.getNomDifficulte(difficulte));
        }).start();
    }
    public static void difficulte() {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/voices/Difficulte.wav").toURI().toString()));
        menuClip.play();
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
    }

    public static void votreScoreEstDe(int score) {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/voices/VotreScoreEstDe.wav").toURI().toString()));
        menuClip.play();
        try {
            Thread.sleep(1700);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
        new Thread(() -> {
            LectureScore.lireScore(score);
        }).start();
    }

    public static void titreMusique(int index) {
        MediaPlayer menuClip = new MediaPlayer(new Media(new File("assets/titres/" + titres[index] + ".wav").toURI().toString()));
        menuClip.play();
        menuClip.setOnEndOfMedia(() -> {
            menuClip.stop();
        });
    }
}
