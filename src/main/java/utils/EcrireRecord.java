package utils;

import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;

public class EcrireRecord {
    public static void ecrire(int record, int difficulte) {
        try {
            int[] records = LectureRecord.lireRecords();
            records[difficulte - 1] = record;
            // Écrire le contenu modifié dans le fichier
            BufferedWriter ecrivain = new BufferedWriter(new FileWriter(String.valueOf(Paths.get("records.txt"))));
            for (int i = 0 ; i < records.length-1 ; i++) {
                ecrivain.write(records[i] + "\n");
            }
            ecrivain.write(String.valueOf(records[records.length - 1]));
            ecrivain.close();

            System.out.println("Donnée écrite avec succès sur la ligne " + difficulte);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture sur la ligne spécifiée : " + e.getMessage());
        }
    }
}
