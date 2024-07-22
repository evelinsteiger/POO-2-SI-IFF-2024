module start {
    requires javafx.controls;
    requires javafx.fxml;

    opens start to javafx.fxml;
    exports start;
    
    opens controller to javafx.fxml;
    exports controller;
}
