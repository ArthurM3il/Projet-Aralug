package utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class LectureDifficulte {

    public static void lireDifficulte(String difficulte) {
        MediaPlayer clip = new MediaPlayer(new Media(new File("assets/voices/" + difficulte + ".wav").toURI().toString()));
        clip.setOnEndOfMedia(() -> {
            clip.stop();
        });
        try {
            clip.play();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
