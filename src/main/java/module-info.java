module com.example.quehoravousair {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.quehoravousair to javafx.fxml;
    exports com.example.quehoravousair;
}