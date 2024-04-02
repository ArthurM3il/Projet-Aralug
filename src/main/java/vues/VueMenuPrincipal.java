package vues;

import controleurs.ControleurMenuPrincipal;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import utils.LectureRecord;
import utils.LectureSon;
import utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class VueMenuPrincipal {
    private BorderPane ui;
    private Label label;

    private double largeurEcran;

    private double hauteurEcran;

    private StringBuilder stringBuilder;

    private Scene scene;

    private Stage stage; // a voir si tu l'utilise vraiment ;

    private final Utils utils = new Utils();


    public VueMenuPrincipal(Stage stage) {
        Rectangle2D screenSize = utils.getScreenSize();
        this.stage = stage;
        this.largeurEcran = screenSize.getWidth();
        this.hauteurEcran = screenSize.getHeight();
        stringBuilder = new StringBuilder();
        label = new Label("Appuyer sur la touche espace pour choisir une musique");
        LectureSon.sonMenu();
        ui = new BorderPane();
        ui.setStyle("-fx-background-color: black;");
        ui.setCenter(label);
        afficherTexte();
        scene = new Scene(ui, largeurEcran, hauteurEcran);
        stage.setScene(scene);
        stage.setTitle("Aralug");
        changerScene(stage);
        stage.show();
    }

    private void afficherTexte() {
        label.setTextFill(Color.YELLOW);
        label.setWrapText(true);
        try {
            label.setFont(Font.loadFont(new FileInputStream("assets/fonts/Luciole-Bold.ttf"), largeurEcran*0.07));
        } catch (FileNotFoundException e) {
            label.setFont(Font.getDefault());
        }
    }
    public Pane getUI() {
        return ui;
    }

    private void changerScene(Stage stage) {
        scene.setOnKeyReleased(event -> {
            if (event.getCode().equals(KeyCode.SPACE)) {
                System.out.println("Vue menu principal changement vers selection musique");
                ControleurMenuPrincipal.changerVue(stage);
            } else if(event.getCode().equals(KeyCode.RIGHT) || event.getCode().equals(KeyCode.LEFT)) {
                lancerSynthese();
            } else if(event.getCode().equals(KeyCode.ENTER)) {
                LectureSon.lireRegles();
            } else {
                LectureSon.lireNavigation();
            }
        });
    }

    public void lancerSynthese(){
        new Thread(LectureRecord::lectureRecords).start();
        label.setText(LectureRecord.ecrireRecords());
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }
}
