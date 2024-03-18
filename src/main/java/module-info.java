module org.example.demo {

    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires com.almasb.fxgl.all;
    requires SI.VOX;
    requires java.desktop;

    opens game to javafx.fxml;
    exports game;
    exports elements;
    opens elements to javafx.fxml;

}