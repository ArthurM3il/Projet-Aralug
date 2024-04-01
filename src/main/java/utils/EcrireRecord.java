package utils;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class EcrireRecord {
    public static void ecrire(String record, int difficulte) {
        String nomFichier = "records";

        try {
            // Ouvrir le fichier en mode lecture/écriture
            RandomAccessFile fichier = new RandomAccessFile(nomFichier, "rw");

            // Calculer la position de début de la ligne à écraser
            long positionDebutLigne = 0;
            for (int i = 0; i < difficulte - 1; i++) {
                positionDebutLigne += fichier.readLine().length() + 1; // +1 pour le retour à la ligne
            }

            // Se positionner au début de la ligne à écraser
            fichier.seek(positionDebutLigne);

            // Écrire la nouvelle donnée
            fichier.writeBytes(record);

            // Fermer le fichier
            fichier.close();

            System.out.println("Donnée écrite avec succès sur la ligne " + difficulte);
        } catch (IOException e) {
            System.out.println("Erreur lors de l'écriture sur la ligne spécifiée : " + e.getMessage());
        }
    }
}
