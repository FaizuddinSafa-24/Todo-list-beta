package com.omnitask;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.util.Duration;
import javafx.stage.Stage;

/**
 *
 * @author safa
 */
public class TaskViewController implements Initializable {
    
    private Stage stage;
    private Scene scene;

    @FXML
    private ListView<String> taskListView;
    @FXML
    private TextField titleField;
    @FXML
    private TextArea textArea;
    @FXML
    private Label usernameLabel;
    @FXML
    private Label clockLabel;

    private String username;
    private List<String[]> tasks;
    private int selectedIndex = -1;

    // Called by LoginController right after load()

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText("User: " + username);
        loadTaskList();
    }

    /**
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startClock();
    }

    private void startClock() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e ->
            clockLabel.setText(LocalTime.now().format(fmt))
        ));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void loadTaskList() {
        taskListView.getItems().clear();
        try {
            List<String[]>tasks = TaskManager.loadTask(username);
            for (String[] t : tasks) {
                // Show first 4-6 words of title
                String display = t[0].length() > 30 ? t[0].substring(0, 30) + "..." : t[0];
                taskListView.getItems().add(display);
            }
        } catch (IOException ex) {
            showAlert("Error", "Could not load tasks: " + ex.getMessage());
        }
    }

    /**
     *
     */
    public void onTaskSelected() {
        selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && tasks != null && selectedIndex < tasks.size()) {
            String[] t = tasks.get(selectedIndex);
            titleField.setText(t[0]);
            textArea.setText(t.length > 1 ? t[1] : "");
        }
    }

    /**
     *
     * @param e
     */
    public void saveTask(ActionEvent e) {
        String title = titleField.getText().trim();
        String text = textArea.getText().trim();
        if (title.isEmpty()) {
            showAlert("Error", "Task title cannot be empty.");
            return;
        }
        try {
            TaskManager.addTask(username, title, text, "");
            titleField.clear();
            textArea.clear();
            selectedIndex = -1;
            loadTaskList();
        } catch (IOException ex) {
            showAlert("Error", "Could not save task: " + ex.getMessage());
        }
    }

    /**
     *
     * @param e
     */
    public void deleteTask(ActionEvent e) {
        if (selectedIndex < 0) {
            showAlert("Error", "Select a task to delete.");
            return;
        }
        try {
            TaskManager.deleteTask(username, selectedIndex);
            titleField.clear();
            textArea.clear();
            selectedIndex = -1;
            loadTaskList();
        } catch (IOException ex) {
            showAlert("Error", "Could not delete task: " + ex.getMessage());
        }
    }

    /**
     *
     * @param e
     * @throws IOException
     */
    public void logout(ActionEvent e) throws IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Logout");
        alert.setContentText("You have been logged out.");
        alert.showAndWait();

        titleField.clear();
        textArea.clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Login.fxml"));
        Parent root = loader.load();
        stage = (Stage) titleField.getScene().getWindow();
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