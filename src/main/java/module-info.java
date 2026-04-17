module com.omnitask {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;

    opens com.omnitask to javafx.fxml;
    exports com.omnitask;
}
