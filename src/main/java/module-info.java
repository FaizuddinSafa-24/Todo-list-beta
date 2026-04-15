module com.omnitask {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.omnitask to javafx.fxml;
    exports com.omnitask;
    requires jbcrypt;
    requires java.base;
}
