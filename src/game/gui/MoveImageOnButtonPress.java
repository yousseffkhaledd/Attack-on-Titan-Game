package game.gui;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MoveImageOnButtonPress extends Application {
    // Step size
    private static final double STEP_SIZE = 50;
    // Initial position
    private double currentX = 150.0;
    // Image size
    private static final double IMAGE_SIZE = 100.0;
    // Scene width
    private static final double SCENE_WIDTH = 1000;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the image
        Image image = new Image(getClass().getResourceAsStream("Titan.png"));

        // Create ImageView
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(IMAGE_SIZE); // Set desired width
        imageView.setFitHeight(IMAGE_SIZE); // Set desired height

        // Set the initial position of the image
        imageView.setLayoutX(currentX);
        imageView.setLayoutY(70.0);

        // Create a button to move the image
        Button moveButton = new Button("Move Image");
        moveButton.setLayoutX(50);
        moveButton.setLayoutY(300);

        // Event handler for the button to move the image
        moveButton.setOnAction(e -> {
            // Calculate the new position
            double newX = currentX + STEP_SIZE;

            // Check boundaries
            if (newX + IMAGE_SIZE <= SCENE_WIDTH) {
                // Move the image to the right
                currentX = newX;
                imageView.setLayoutX(currentX);
            }

            // Apply a color adjustment effect
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setBrightness(0.5); // Increase brightness
           // imageView.setEffect(colorAdjust);
        });

        // Configuring group and scene
        Group root = new Group();
        root.getChildren().addAll(imageView, moveButton);
        Scene scene = new Scene(root, SCENE_WIDTH, 350, Color.WHEAT);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Move Image on Button Press");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
