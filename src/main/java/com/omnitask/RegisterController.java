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

public class RegisterController implements Initializable {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmField;
    @FXML private ListView<String> hintList;
    @FXML private TextField favAnswerField;

    private static final String[] HINTS = {
        "What is your favourite movie?",
        "What is your favourite book?",
        "What is your favourite character?",
        "What is your favourite sports team?",
        "What is the name of your first pet?",
        "What is your mother's maiden name?"
    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        hintList.setItems(FXCollections.observableArrayList(HINTS));
        hintList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    @FXML
    public void register(ActionEvent e) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();
        String confirm = confirmField.getText();
        String favAnswer = favAnswerField.getText().trim();
        String hint = hintList.getSelectionModel().getSelectedItem();

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty() || favAnswer.isEmpty()) {
            showAlert("Error", "All fields are required.");
            return;
        }
        if (!password.equals(confirm)) {
            showAlert("Error", "Passwords do not match.");
            return;
        }
        if (hint == null) {
            showAlert("Error", "Please select a password hint.");
            return;
        }

        boolean ok = UserManager.register(username, password, hint, favAnswer);
        if (ok) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Success");
            a.setContentText("Account created! Please log in.");
            a.showAndWait();
            goLogin(e);
        } else {
            showAlert("Error", "Username already exists or is invalid.");
        }
    }

    @FXML
    public void goLogin(ActionEvent e) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) usernameField.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}