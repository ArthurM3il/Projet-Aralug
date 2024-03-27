package vues;

import controleurs.ControleurMenuPrincipal;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import t2s.son.LecteurTexte;

import java.awt.*;

public class VueMenuPrincipal {
    private Pane ui;
    private Label label;

    private double largeurEcran;

    private double hauteurEcran;

    private LecteurTexte lecteurTexte;

    public VueMenuPrincipal(Stage stage) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        Label label = new Label("Appuyer sur la touche espace pour choisir une musique");
        lecteurTexte = new LecteurTexte();
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        if (stage.getScene() != null) {
            changerScene(stage);
        }
        afficherTexte(label.getText());
    }

    public void afficherTexte(String texte) {
        lecteurTexte.setTexte(texte);
        lancerSynthese(lecteurTexte);
        Text text = new Text(texte);
        text.setFill(Color.YELLOW);
        text.setWrappingWidth(largeurEcran);
        text.setFont(Font.loadFont("file:assets/fonts/Luciole-Bold.ttf",largeurEcran*0.07));
        text.setX((largeurEcran - text.getLayoutBounds().getWidth()) / 2);
        text.setY((hauteurEcran - text.getLayoutBounds().getHeight()) / 2);
        ui.getChildren().add(text);
    }

    public Pane getUI() {
        return ui;
    }

    private void changerScene(Stage stage) {
        stage.getScene().setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                System.out.println("Vue menu principal changement vers selection musique");
                ControleurMenuPrincipal.changerVue(stage);
            }
        });
    }

    public void lancerSynthese(LecteurTexte lecteur){
        new Thread(() -> {
            lecteur.play();
        }).start();
    }
}
