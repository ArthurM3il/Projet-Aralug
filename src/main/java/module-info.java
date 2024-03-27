module org.example.demo {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires SI.VOX;
    requires java.desktop;
    requires junit;

    opens game to javafx.fxml;
    exports vues;
    exports elements;
    exports tests;
    opens elements to javafx.fxml;

}