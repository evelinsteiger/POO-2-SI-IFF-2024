module start {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens start to javafx.fxml;
    exports start;
}
