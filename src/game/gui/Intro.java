package game.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import java.io.File;

public class Intro extends Application
{


    public void start(Stage primaryStage) throws Exception {
        try {
            String path = "C:\\Users\\amazon\\Desktop\\AOT Green3\\AOT Green\\AOT\\src\\game\\gui\\MenuSound.mp3";  // Adjust path to your audio file
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop indefinitely
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}  