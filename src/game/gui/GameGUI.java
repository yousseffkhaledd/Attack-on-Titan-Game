package game.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


import java.util.Objects;

public class GameGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("Main.fxml")));
            Scene scene =new Scene(root);

            MainMenu2 mainMenu = new MainMenu2();
            mainMenu.start(primaryStage);
//            Intro intro = new Intro();
//            intro.start(primaryStage);
            Audio audio = new Audio();
            audio.start(primaryStage);
            primaryStage.show();
    }



    public static void main(String[] args) {
        launch(args);
    }


    public void start(Stage primaryStage, String s) {
    }
}
