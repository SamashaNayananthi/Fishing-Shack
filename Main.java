package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //set the scene to load the first screen of the program
        Parent root = FXMLLoader.load(getClass().getResource("Screen1.fxml"));
        //set the title
        primaryStage.setTitle("Jeff's Fishing Shack");
        primaryStage.setScene(new Scene(root, 523, 414));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
    }
}
