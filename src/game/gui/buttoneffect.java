package game.gui;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class buttoneffect extends Application {

    public static void buttonEffect() {
        try {
            String path = "C:\\Users\\amazon\\Desktop\\AOT Green3\\AOT Green\\AOT\\src\\game\\gui\\punch-gaming-sound-effect-hd_RzlG1GE.mp3";  // Adjust path to your audio file
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
           // mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Loop indefinitely
            mediaPlayer.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        buttonEffect();
    }
}
