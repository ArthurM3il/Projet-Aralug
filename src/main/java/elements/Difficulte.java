package elements;

public enum Difficulte {
    FACILE("facile", Integer.MAX_VALUE),
    MOYEN("moyen", 20  ),
    DIFFICILE("difficile" , 10);

    private final int nombreErreurCumuluees;

    private final String nom;
    Difficulte(String nom, int nombreErreurCumuluees) {
        this.nom = nom;
        this.nombreErreurCumuluees = nombreErreurCumuluees;
    }

    public String getNom() {
        return nom;
    }
    public int getNombreErreurCumuluees() {
        return nombreErreurCumuluees;
    }

    public static String getNomDifficulte(int choixDifficulte) {
        if (choixDifficulte == 0) {
            return Difficulte.FACILE.getNom();
        } else if (choixDifficulte == 1) {
            return Difficulte.MOYEN.getNom();
        } else {
            return Difficulte.DIFFICILE.getNom();
        }
    }

    public static int getNombreDifficulte(int choixDifficulte) {
        if (choixDifficulte == 0) {
            return Difficulte.FACILE.getNombreErreurCumuluees();
        } else if (choixDifficulte == 1) {
            return Difficulte.MOYEN.getNombreErreurCumuluees();
        } else {
            return Difficulte.DIFFICILE.getNombreErreurCumuluees();
        }
    }
}
