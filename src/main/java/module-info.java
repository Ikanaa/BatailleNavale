module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens ikana to javafx.fxml;
    exports ikana;
}