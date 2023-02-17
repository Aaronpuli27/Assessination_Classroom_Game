module com.example.assessination_classroom {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.assessination_classroom to javafx.fxml;
    exports com.example.assessination_classroom;
    exports com.example.assessination_classroom.clases;
    opens com.example.assessination_classroom.clases to javafx.fxml;


}