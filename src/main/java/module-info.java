module ikana {
    requires javafx.controls;
    requires javafx.fxml;


    opens ikana to javafx.fxml;
    exports ikana;
}