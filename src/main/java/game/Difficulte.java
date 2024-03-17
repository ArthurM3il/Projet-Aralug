package game;

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
}
