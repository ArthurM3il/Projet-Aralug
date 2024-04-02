package utils;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class LectureDifficulte {

    public static void lireDifficulte(String difficulte) {
        MediaPlayer clip = new MediaPlayer(new Media(new File("assets/voices/Difficulte" + difficulte + ".wav").toURI().toString()));
        clip.setOnEndOfMedia(() -> {
            clip.stop();
        });
        clip.play();
    }
}
