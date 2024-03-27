package elements;

public enum Musique {
    DP_INSTANTCRUSH("Daft Punk - Instant Crush",110,"assets/musics/DaftPunkInstantCrush.wav"),
    JUL_LAZONE("Jul - J'fais plaisir à la zone",133,"assets/musics/JulLaZone.wav"),
    SLIMANE_CHEZTOI("Slimane feat Claudio Capéo - Chez toi", 115,"assets/musics/SlimaneChezToi.wav"),
    LUCENZO_KUDURO("Lucenzo feat Don Omar - Danza Kuduro", 130, "assets/musics/LucenzoDanzaKuduro"),
    SOOLKING_CASANOVA("Soolking feat Gazo - Casanova",132,"assets/musics/SoolkingCasanova.wav"),
    SOPRANO_ENFEU("Soprano - En feu", 140, "assets/musics/SopranoEnFeu.wav"),
    LORIE_MEILLEUREAMIE("Lorie - Je serais ta meilleure amie",108,"assets/musics/LorieMeilleureAmie.wav"),
    LOUANE_SECRET("Louane - Secret", 75, "assets/musics/LouaneSecret.wav");

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

