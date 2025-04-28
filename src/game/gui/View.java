package game.gui;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class View extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a button
        Button animatedButton = new Button("Hover Over Me!");

        // Set up the scale transition for animation
        ScaleTransition st = new ScaleTransition(Duration.millis(200), animatedButton);
        st.setToX(1.5);  // Scale by 1.5 times in X direction
        st.setToY(1.5);  // Scale by 1.5 times in Y direction
        st.setCycleCount(1);
        st.setAutoReverse(true);

        // Adding mouse event handlers
        animatedButton.setOnMouseEntered(e -> st.playFromStart());  // Play animation on hover
        animatedButton.setOnMouseExited(e -> st.pause());         // Reverse animation on mouse exit

        // Setting up the stage and scene
        StackPane root = new StackPane();
        root.getChildren().add(animatedButton);
        Scene scene = new Scene(root, 300, 250);
        primaryStage.setTitle("JavaFX Animated Button Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
