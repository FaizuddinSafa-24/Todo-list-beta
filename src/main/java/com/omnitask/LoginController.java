/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.omnitask;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author safa
 */
public class LoginController {

    @FXML
    private TextField signin;
    @FXML
    private PasswordField pass;
    @FXML
    private Button login;
    String name;
    String password;
    private Stage stage;
    private Scene scene;
    private Parent root;

    // pass username and password to UserManager
    // they will hash the pass and store them in file
    // to do this, i need to getText of what typed here
    //after storing, pass the values to usermanager.checklogin() methd and return bool
    // depending on bool value, y -> taskview.fxml open, n-> show alert message and add a methd called "showAndWait().get() == ButtonType.OK

    /**
     *
     * @param e
     * @throws IOException
     */
    public void login(ActionEvent e) throws IOException {

        name = signin.getText();
        password = pass.getText();
        boolean checkID = UserManager.checkLoginCredentials(name, password);
        if (checkID) {
            FXMLLoader loader = FXMLLoader.load(getClass().getResource("TaskView.fxml"));
            root = loader.load();
            scene = new Scene(root); 
            //scene = new Scene(loadFXML("Login"));
            stage.setScene(scene);
            stage.show();
        } else {
            FXMLLoader loader = FXMLLoader.load(getClass().getResource("Register.fxml"));
            root = loader.load();
            scene = new Scene(root);
            //scene = new Scene(loadFXML("Login"));
            stage.setScene(scene);
            stage.show();
        }
    }
}
