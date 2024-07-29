module start {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    
    
    opens model to javafx.fxml;
    exports model;

    opens controller to javafx.fxml;
    exports controller;
    exports start;
}
