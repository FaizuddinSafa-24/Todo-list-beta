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
        scene = new Scene(root);
        
        
        stage.setScene(scene);
        stage.setTitle("OmniTask");
        stage.show();
    }



    /**
     *
     * @param args
     * @throws IOException
     */

    public static void main(String[] args)  {
        
        launch(args);     
    }

}