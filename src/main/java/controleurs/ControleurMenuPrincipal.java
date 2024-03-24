package controleurs;

import modeles.ModeleMenuPrincipal;
import vues.VueMenuPrincipal;
import vues.VueSelectionMenu;

public class ControleurMenuPrincipal {

    private ModeleMenuPrincipal modeleMenuPrincipal;
    private static VueMenuPrincipal vueMenuPrincipal;

    public ControleurMenuPrincipal(ModeleMenuPrincipal modeleMenuPrincipal, VueMenuPrincipal vueMenuPrincipal) {
        this.modeleMenuPrincipal = modeleMenuPrincipal;
        this.vueMenuPrincipal = vueMenuPrincipal;

        vueMenuPrincipal.afficherTexte(modeleMenuPrincipal.getTexte());
    }

    public static void changerVue() {
        VueSelectionMenu vueSelectionMenu = new VueSelectionMenu();
        vueMenuPrincipal.getUI().getScene().setRoot(vueSelectionMenu.getUI());
    }
}
