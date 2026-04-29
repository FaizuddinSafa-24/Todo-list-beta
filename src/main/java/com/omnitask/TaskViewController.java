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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


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
    @FXML
    private VBox popup;
    @FXML
    private VBox centerContent;
    @FXML
    private StackPane swapZone;
    @FXML
    private VBox checklistContainer;
    @FXML
    private DatePicker date;

    private String username;
    private List<String[]> tasks;
    private int selectedIndex = -1;

    public void setUsername(String username) {
        this.username = username;
        usernameLabel.setText("User: " + username);
        loadTaskList();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startClock();
    }

    private void startClock() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm:ss");
        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), e
                -> clockLabel.setText(LocalTime.now().format(fmt))
        ));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    private void loadTaskList() {
        taskListView.getItems().clear();
        try {
            tasks = Taskmanager.loadTask(username);
            for (String[] t : tasks) {
                String display = t[0].length() > 30 ? t[0].substring(0, 30) + "..." : t[0];
                taskListView.getItems().add(display);
            }
        } catch (IOException ex) {
            showAlert("Error", "Could not load tasks: " + ex.getMessage());
        }
    }

    @FXML
    public void onTaskSelected() {
        selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0 && tasks != null && selectedIndex < tasks.size()) {
            String[] t = tasks.get(selectedIndex);
            titleField.setText(t[0]);
            textArea.setText(t.length > 1 ? t[1] : "");
        }
    }

    // --- popup ---
    @FXML
    public void showPopup(ActionEvent e) {
        popup.setVisible(true);
        popup.setManaged(true);
    }

    private void hidePopup() {
        popup.setVisible(false);
        popup.setManaged(false);
    }

    @FXML
    public void openTextNote(ActionEvent e) {
        hidePopup();
        textArea.setVisible(true);
        textArea.setManaged(true);
        checklistContainer.setVisible(false);
        checklistContainer.setManaged(false);
        checklistContainer.getChildren().addAll();
        titleField.clear();
        textArea.clear();
        titleField.requestFocus();
    }

    @FXML
    public void openCheckList(ActionEvent e) {
        hidePopup();
        textArea.setVisible(false);
        textArea.setManaged(false);
        checklistContainer.setVisible(true);
        checklistContainer.setManaged(true);
        checklistContainer.getChildren().addAll();
        titleField.clear();
        addNewCheckTask();
    }

    private void addNewCheckTask() {
        CheckBox checkBox = new CheckBox();
        TextField taskField = new TextField();
        taskField.setPromptText("Add a task...");
        HBox.setHgrow(taskField, Priority.ALWAYS);

        taskField.setOnKeyPressed(ev -> {
            if (ev.getCode() == KeyCode.ENTER && !taskField.getText().isEmpty()) {
                addNewCheckTask();
            }
        });

        checkBox.setOnAction(ev -> {
            if (checkBox.isSelected()) {
                taskField.setStyle("-fx-strikethrough: true; -fx-text-fill: gray;");
            } else {
                taskField.setStyle("-fx-strikethrough: false; -fx-text-fill: white;");
            }
        });

        HBox row = new HBox(10, checkBox, taskField);
        row.setAlignment(Pos.CENTER_LEFT);
        checklistContainer.getChildren().add(row);
        taskField.requestFocus();
    }

    // --- task CRUD ---
    @FXML
    public void saveTask(ActionEvent e) {
        String title = titleField.getText().trim();
        String text = textArea.getText().trim()
                .replaceAll("[ \\t]+", " ")
                .replaceAll("\\n{2,}", "\n");
        if (title.isEmpty()) {
            showAlert("Error", "Task title cannot be empty.");
            return;
        }
        try {
            //String username, String title, String text, String dueDate, String type
            Taskmanager.updateTask(username, title, text);
            titleField.clear();
            textArea.clear();
            selectedIndex = -1;
            loadTaskList();
        } catch (IOException ex) {
            showAlert("Error", "Could not save task: " + ex.getMessage());
        }
    }

    @FXML
    public void deleteTask(ActionEvent e) {
        String title = titleField.getText();
        if (selectedIndex < 0) {
            showAlert("Error", "Select a task to delete.");
            return;
        }
        try {
            Taskmanager.deleteTask(username,title);
            titleField.clear();
            textArea.clear();
            selectedIndex = -1;
            loadTaskList();
        } catch (IOException ex) {
            showAlert("Error", "Could not delete task: " + ex.getMessage());
        }
    }

    @FXML
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
