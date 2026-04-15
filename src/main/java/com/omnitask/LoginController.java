/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.omnitask;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;



/**
 * FXML Controller class
 *
 * @author safa
 */
public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private PasswordField pass;
    String name;
    String password;
    
    // pass username and password to UserManager
    // they will hash the pass and store them in file
    // to do this, i need to getText of what typed here
    
    public void setUsername(KeyEvent e) {
        name = e.getText();
    }
}
