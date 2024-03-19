package utils;

import game.Jeu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class LecteurMusique {
    private static Clip currentClip;
    private static Clip victoireClip;

    private static Clip defaiteClip;

    private static Clip erreurClip;

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
            AudioInputStream victoireStream = AudioSystem.getAudioInputStream(victoireFile);
            AudioInputStream defaiteStream = AudioSystem.getAudioInputStream(defaiteFile);
            AudioInputStream erreurStream = AudioSystem.getAudioInputStream(erreurFile);
            victoireClip = AudioSystem.getClip();
            victoireClip.open(victoireStream);
            defaiteClip = AudioSystem.getClip();
            defaiteClip.open(defaiteStream);
            erreurClip = AudioSystem.getClip();
            erreurClip.open(erreurStream);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void sonErreur() {
        try{
            erreurClip.start();
            Thread.sleep(1000);
            Jeu.endErreurThread();
            erreurClip.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
