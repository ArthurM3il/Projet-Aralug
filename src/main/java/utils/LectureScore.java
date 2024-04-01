package utils;

import elements.Difficulte;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class LectureScore {

    private static MediaPlayer clip;
    private static String[] chiffres = {"Vide", "Un", "Deux", "Trois", "Quatre", "Cinq", "Six", "Sept", "Huit", "Neuf"};

    private static String[] nombres = {"Vide", "Onze", "Douze", "Treize", "Quatorze", "Quinze", "Seize"};

    private static String[] dizaines = {"Vide", "Dix", "Vingt", "Trente", "Quarante", "Cinquante", "Soixante", "Soixante-dix", "Quatre-vingts", "Quatre-vingt-dix"};

    private static String cent = "Cent";

    private static String mille = "Mille";


    public static StringBuilder lireScore(int score) {
        StringBuilder stringBuilder = new StringBuilder();
        //1526
        //tester si le nombre est supérieur à 1000
        int millier = score / 1000;
        int centaines = (score - 1000 * millier) / 100;
        int dizaine = (score - 1000 * millier - 100 * centaines) / 10;
        int unite = score - 1000 * millier - 100 * centaines - 10 * dizaine;
        if (score >= 1000) {
            if (millier != 1) {
                jouerSon(chiffres[millier]);
                stringBuilder.append(chiffres[millier]);
                stringBuilder.append(" ");
            }
            jouerSon(mille);
            stringBuilder.append(mille);
            stringBuilder.append(" ");
        }
        if (score >= 100) {
            if (centaines != 1) {
                jouerSon(chiffres[centaines]);
                stringBuilder.append(chiffres[centaines]);
                stringBuilder.append(" ");
            }
            jouerSon(cent);
            stringBuilder.append(cent);
            stringBuilder.append(" ");
        }
        if (score >= 10) {
            if (dizaine * 10 + unite <= 96 && dizaine * 10 + unite >= 91 || dizaine * 10 + unite <= 76 && dizaine * 10 + unite >= 71) {
                jouerSon(dizaines[dizaine - 1]);
                stringBuilder.append(dizaines[dizaine - 1]);
                stringBuilder.append(" ");
                jouerSon(nombres[unite]);
                stringBuilder.append(nombres[unite]);
                stringBuilder.append(" ");
            } else {
                if (dizaine * 10 + unite <= 16 && dizaine * 10 + unite >= 11) {
                    jouerSon(nombres[unite]);
                    stringBuilder.append(nombres[unite]);
                    stringBuilder.append(" ");
                } else {
                    jouerSon(dizaines[dizaine]);
                    stringBuilder.append(dizaines[dizaine]);
                    stringBuilder.append(" ");
                    jouerSon(chiffres[unite]);
                    stringBuilder.append(chiffres[unite]);
                }
            }
        } else {
            jouerSon(chiffres[unite]);
        }
        return stringBuilder;
    }

    private static void jouerSon(String path) {
        clip = new MediaPlayer(new Media(new File("assets/nombres/" + path + ".wav").toURI().toString()));
        clip.setOnEndOfMedia(() -> {
            clip.stop();
        });
        try {
            clip.play();
            Thread.sleep(1200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
