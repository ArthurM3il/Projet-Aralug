package vues;

import controleurs.ControleurMenuPrincipal;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.LecteurMusique;
import utils.LectureRecord;

import java.awt.*;

public class VueMenuPrincipal {
    private Pane ui;
    private Label label;

    private double largeurEcran;

    private double hauteurEcran;

    private StringBuilder stringBuilder;


    public VueMenuPrincipal(Stage stage) {
        this.largeurEcran = stage.getWidth();
        this.hauteurEcran = stage.getHeight();
        stringBuilder = new StringBuilder();
        Label label = new Label("Appuyer sur la touche espace pour choisir une musique");
        LecteurMusique.sonMenu();
        ui = new Pane();
        ui.setStyle("-fx-background-color: black;");
        if (stage.getScene() != null) {
            changerScene(stage);
        }
        afficherTexte(label.getText());
    }

    public void afficherTexte(String texte) {
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
            } else if(event.getCode().equals(KeyCode.RIGHT) || event.getCode().equals(KeyCode.LEFT)) {
                lancerSynthese();
            }
        });
    }




    public void lancerSynthese(){
        new Thread(LectureRecord::lectureRecords).start();
        afficherTexte(LectureRecord.ecrireRecords());
    }
}
