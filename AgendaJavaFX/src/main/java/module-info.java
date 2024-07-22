module start {
    requires javafx.controls;
    requires javafx.fxml;

    opens controller to javafx.fxml;
    exports controller;
    exports start;
    
}
