package tests;

import org.junit.Assert;
import org.junit.Test;
import utils.Utils;

public class UtilsTest {

    @Test
    public void testChangerIndexIncrementUneValeur() {
        int resultat = Utils.changerIndex(Utils.DROITE, 3, 1);
        Assert.assertEquals(2, resultat);
    }

    @Test
    public void testChangerIndexIncrementTailleMax() {
        int resultat = Utils.changerIndex(Utils.DROITE, 3, 2);
        Assert.assertEquals(0, resultat);
    }
    @Test
    public void testChangerIndexDecrementUneValeur() {
        int resultat = Utils.changerIndex(Utils.GAUCHE, 3, 2);
        Assert.assertEquals(1, resultat);
    }

    @Test
    public void testChangerIndexDecrementTailleMax() {
        int resultat = Utils.changerIndex(Utils.GAUCHE, 3, 0);
        Assert.assertEquals(3, resultat);
    }
}
