module com.example.assegnamento {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.assegnamento to javafx.fxml;
    exports com.example.assegnamento;
}