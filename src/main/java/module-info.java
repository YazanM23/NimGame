module com.example.aihome {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.example.aihome to javafx.fxml;
    exports com.example.aihome;
}