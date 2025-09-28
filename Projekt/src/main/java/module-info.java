module lab01 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    opens projekt to javafx.fxml;
    exports projekt;
}