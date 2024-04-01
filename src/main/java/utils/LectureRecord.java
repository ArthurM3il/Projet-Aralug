package utils;

import elements.Difficulte;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LectureRecord {

    public static int[] lireRecords() {
        int[] records = new int[3];
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get("records.txt"))) {
            String lectureLigne = bufferedReader.readLine();
            Integer numeroLigne = 0;
            do {
                String[] ligneDecoupee = lectureLigne.split("\n");
                String record = ligneDecoupee[0];
                records[numeroLigne] = Integer.parseInt(record);
                lectureLigne = bufferedReader.readLine();
                numeroLigne++;
            } while (lectureLigne != null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return records;
    }

    public static void lireSonRecord() {
        MediaPlayer clip = new MediaPlayer(new Media(new File("assets/voices/VosRecordsSon.wav").toURI().toString()));
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

    public static void lectureRecords() {
        lireSonRecord();
        int[] records = lireRecords();
        for (int i = 0 ; i < records.length ; i++) {
            LectureDifficulte.lireDifficulte(Difficulte.getNomDifficulte(i));
            LectureScore.lireScore(records[i]);
        }
    }

    public static String ecrireRecords() {
        int[] records = lireRecords();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Vos records sont : ");
        for (int i = 0 ; i < records.length ; i++) {
            stringBuilder.append(Difficulte.getNomDifficulte(i));
            stringBuilder.append(" " + records[i] + " ");
        }
        return String.valueOf(stringBuilder);
    }
}
