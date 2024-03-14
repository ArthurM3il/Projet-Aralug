package utils;

import game.Jeu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer{
    private static Clip currentClip;
    private static Clip achievementClip;

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
                achievementClip.start();
                Thread.sleep(2500);
                achievementClip.close();
            } catch ( InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public static void stopMusic(){
        try{
            currentClip.close();
            achievementClip.close();
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

            File achievementFile = new File("assets/effects/Achievement.wav");
            AudioInputStream achievementStream = AudioSystem.getAudioInputStream(achievementFile);
            achievementClip = AudioSystem.getClip();
            achievementClip.open(achievementStream);

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }
}
