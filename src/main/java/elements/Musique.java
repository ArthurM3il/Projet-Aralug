package elements;

public enum Musique {
    DP_INSTANTCRUSH("Daft Punk - Instant Crush",110,"assets/musics/DaftPunkInstantCrush.aiff"),
    JUL_LAZONE("Jul - J'fais plaisir à la zone",133,"assets/musics/JulLaZone.wav");

    public int getBpm() {
        return bpm;
    }

    public String getPath() {
        return path;
    }

    private int bpm;
    private String path;
    private String titre;

    Musique(String titre, int bpm, String path) {
        this.bpm = bpm;
        this.path = path;
        this.titre = titre;
    }

    public String getTitre() {
        return titre;
    }
}

