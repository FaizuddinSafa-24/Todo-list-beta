package com.omnitask;

//import javafx.application.Application;

import java.io.IOException;
import java.util.List;

//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//
//import java.io.IOException;

/**
 * JavaFX App
 */
public class App  {
//
//    private static Scene scene;
//
//    @Override
//    public void start(Stage stage) throws IOException {
//        scene = new Scene(loadFXML("primary"), 640, 480);
//        stage.setScene(scene);
//        stage.show();
//    }
//
//    static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }

    public static void main(String[] args) throws IOException {
        //launch();
        TaskManager.addTask("Safa", "WowQuizCancelled", "Quiz cancellation notice makes everyone to enjpy the week, though finals are two weeks later, we get 2 days PL.):", "15-04-2026");
        List<String[]> tasks = TaskManager.loadTask("Safa");
        for (String[] t : tasks) {
            System.out.println(t[0]+ "|"+t[1]+ t[2]+ "|"+t[3]);
        }
    }

}