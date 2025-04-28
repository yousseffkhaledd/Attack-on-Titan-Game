package game.gui;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class TimedSceneSwitch extends Application {

    @Override
    public void start(Stage primaryStage) {
        // First scene setup
        Button firstButton = new Button("First Scene");
        StackPane firstRoot = new StackPane();
        firstRoot.getChildren().add(firstButton);
        Scene firstScene = new Scene(firstRoot, 300, 250);

        // Second scene setup
        Button secondButton = new Button("Second Scene");
        StackPane secondRoot = new StackPane();
        secondRoot.getChildren().add(secondButton);
        Scene secondScene = new Scene(secondRoot, 300, 250);

        // Set up the stage with the first scene
        primaryStage.setTitle("Timed Scene Switch");
        primaryStage.setScene(firstScene);
        primaryStage.show();

        // Switch to the second scene after a duration (e.g., 5 seconds)
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            primaryStage.setScene(secondScene);
        }));
        timeline.setCycleCount(1); // Ensure the timeline runs only once
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
