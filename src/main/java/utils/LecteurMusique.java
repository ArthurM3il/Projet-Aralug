package utils;
import game.Jeu;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class LecteurMusique {
    private static Clip currentClip;
    private static Clip victoireClip;

    private static Clip defaiteClip;

    private static MediaPlayer erreurClip;

    public static void playMusic() {
            try {
                currentClip.start();

                // Attendre la fin de la lecture
                while (!currentClip.isRunning())
                    Thread.sleep(10);
                while (currentClip.isRunning())
                    Thread.sleep(10);
                currentClip.close();
                Jeu.endTimeline();
                Thread.sleep(20);
                victoireClip.start();
                Thread.sleep(2500);
                victoireClip.close();
            } catch ( InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void stopMusic(){
        try{
            currentClip.close();
            victoireClip.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadMusic(String filePath) {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            currentClip = AudioSystem.getClip();
            currentClip.open(audioStream);

            File victoireFile = new File("assets/effects/Victoire.wav");
            File defaiteFile = new File("assets/effects/Defaite.wav");
            File erreurFile = new File("assets/effects/Erreur.wav");
            Media media = new Media(erreurFile.toURI().toString());
            erreurClip = new MediaPlayer(media);
            AudioInputStream victoireStream = AudioSystem.getAudioInputStream(victoireFile);
            AudioInputStream defaiteStream = AudioSystem.getAudioInputStream(defaiteFile);
            victoireClip = AudioSystem.getClip();
            victoireClip.open(victoireStream);
            defaiteClip = AudioSystem.getClip();
            defaiteClip.open(defaiteStream);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void sonErreur() {
            erreurClip.play();
            erreurClip.setOnEndOfMedia(() -> {
                erreurClip.stop();
            });
    }

    public static void sonDefaite() {
        try {
            currentClip.close();
            victoireClip.close();
            defaiteClip.start();
            Thread.sleep(4000);
            defaiteClip.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
