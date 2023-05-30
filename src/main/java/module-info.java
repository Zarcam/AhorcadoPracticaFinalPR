module com.practicafinal {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.practicafinal to javafx.fxml;
    exports com.practicafinal;
}