package tests;

import elements.Musique;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.Test;
import vues.VueJeu;

public class VueJeuTest {

    @Test
    public void testCalculScore() {
        Stage stage = new Stage();
        Scene scene = new Scene(null, 0, 0);
        stage.setScene(scene);
        VueJeu vueJeu = new VueJeu(stage,Musique.DP_INSTANTCRUSH,0);
        for (int i = 0 ; i < 300 ; i += 25) {
            //System.out.println(vueJeu.calculScore(i, Musique.DP_INSTANTCRUSH, 0));
        }
    }
}
