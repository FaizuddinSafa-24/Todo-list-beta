module com.omnitask {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbcrypt;

    exports com.omnitask;
    exports com.omnitask.controller;
    exports com.omnitask.task;
    exports com.omnitask.user;

    opens com.omnitask.controller to javafx.fxml;
    opens com.omnitask.task to javafx.fxml;
    opens com.omnitask.user to javafx.fxml;
}