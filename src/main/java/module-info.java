module com.omnitask {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;
    requires java.base;

    opens com.omnitask to javafx.fxml;
    exports com.omnitask;
}
