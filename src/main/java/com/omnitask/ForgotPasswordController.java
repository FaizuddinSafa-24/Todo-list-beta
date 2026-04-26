package com.omnitask;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 *
 * @author safa
 */
public class ForgotPasswordController implements Initializable {
    private Stage stage;
    private Scene scene;

    @FXML 
    private TextField usernameField;
    @FXML 
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmField;
    @FXML
    private ListView<String> hintList;
    @FXML
    private TextField favAnswerField;

    private static final String[] HINTS = {
        "What is your favourite movie?",
        "What is your favourite book?",
        "What is your favourite character?",
        "What is your favourite sports team?",
        "What is the name of your first pet?",
        "What is your mother's maiden name?"
    };

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hintList.setItems(FXCollections.observableArrayList(HINTS));
        hintList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    public void passReset(ActionEvent e) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirm = confirmField.getText();
        String favAnswer = favAnswerField.getText().trim();
        String hint = hintList.getSelectionModel().getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty() || favAnswer.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return; //return must,  otherwise keep going through following if-else
        }
        if (!UserManager.isUserExists(username)) {
            showAlert("Error", "Username is not exists.");
            return; // same
        }
        if (!hint.equals(UserManager.getPassHint(username))) {
            showAlert("Error", " password hint does not match.");
            return; // same
        }
        if(!favAnswer.equals(UserManager.getFavAnswer(username))) {
            showAlert("Error", " favourite answer does not match.");
            return; // same
        }

        boolean ok = UserManager.resetPassword(username, password);
        if (ok) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setContentText("Password changed! Please log in.");
            a.showAndWait();
            goLogin(e);
        } else {
            showAlert("Error", "Could not change old password.");
        }
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    public void goLogin(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}