package com.omnitask;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author safa
 */
public class LoginController {

    @FXML
    private TextField signin;
    @FXML
    private PasswordField pass;
    //must add private Scene scene for removing redundancy
    private Stage stage;
    private Scene scene;
    
    /**
     *
     * @param e
     * @throws IOException
     */
    public void login(ActionEvent e) throws IOException {
        String username = signin.getText().trim();
        String password = pass.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Username and password cannot be empty.");
            return;
        }

        boolean ok = UserManager.checkLoginCredentials(username, password);
        if (ok) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/TaskView.fxml"));
            Parent root = loader.load();
            TaskViewController ctrl = loader.getController();
            ctrl.setUsername(username);
            stage = (Stage) signin.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);    
            stage.show();
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    public void goForgotPassword(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ForgotPassword.fxml"));
        Parent root = loader.load();
        stage = (Stage) signin.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);    // scene obj can be used
        stage.show();
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    public void goRegister(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Register.fxml"));
        Parent root = loader.load();
        stage = (Stage) signin.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);    // scene obj can be used
        stage.show();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}