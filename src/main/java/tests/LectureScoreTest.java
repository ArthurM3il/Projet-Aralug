package tests;

import javafx.stage.Stage;
import org.junit.Test;
import utils.LectureScore;
import vues.VueScore;

public class LectureScoreTest {

    @Test
    public void testLectureScore() {
        StringBuilder stringBuilder = LectureScore.lireScore(1641);
        System.out.println(stringBuilder);
    }
}
