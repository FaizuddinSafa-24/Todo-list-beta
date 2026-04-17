package com.omnitask;



import java.io.IOException;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {
//
    private static Scene scene;

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/fxml/Login.fxml"));
        Scene scene = new Scene(root);
        
        //scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.show();
    }

//    public static void setRoot(String fxml) throws IOException {
//        scene.setRoot(loadFXML(fxml));
//    }
//
//    private static Parent loadFXML(String fxml) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
//        return fxmlLoader.load();
//    }

    /**
     *
     * @param args
     * @throws IOException
     */

    public static void main(String[] args) throws IOException {
        
        launch(args);
//        TaskManager.addTask("Safa", "WowQuizCancelled", "Quiz cancellation notice makes everyone to enjpy the week, though finals are two weeks later, we get 2 days PL.):", "15-04-2026");
//        List<String[]> tasks = TaskManager.loadTask("Safa");
//        for (String[] t : tasks) {
//            System.out.println(t[0]+ "|"+t[1]+"|"+ t[2]+ "|"+t[3]);
//        }
// this was a test program, now it's commented.
    }

}