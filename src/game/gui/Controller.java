package game.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import game.engine.Battle;
import game.engine.lanes.Lane;
import game.engine.titans.*;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;

import static game.gui.MainMenu2.*;
import static javafx.geometry.Pos.TOP_LEFT;

public class Controller {
        public static Battle battleGround;
        Boolean isEasy;
        private static Label label1;
        private static Label label2;
        private static Label label3;
        private static Label label4;
        private static final MainMenu2 mainMenu2 = new MainMenu2();
        private static String s;
        private static Label[] labels2;

        VBox vBox = new VBox();

        HBox hBox = new HBox();
        HBox hBox2 = new HBox();
        static ArrayList<Lane> lanes = new ArrayList<>();

        // Load images for the titans
        private static final Image PURE_TITAN_IMAGE = new Image(Controller.class.getResourceAsStream("PureTitan.png"));
        private static final Image ABNORMAL_TITAN_IMAGE = new Image(Controller.class.getResourceAsStream("ABNORMAL_TITAN-Photoroom.png"));
        private static final Image ARMORED_TITAN_IMAGE = new Image(Controller.class.getResourceAsStream("ARMORED_TITAN.png"));
        private static final Image COLOSSAL_TITAN_IMAGE = new Image(Controller.class.getResourceAsStream("COLOSSAL_TITAN.png"));
        private static final Image GAME_OVER_IMAGE = new Image(Controller.class.getResourceAsStream("GameOver.png"));
        public Controller(Boolean isEasy) {
                try {
                        if (isEasy) {
                                battleGround = new Battle(1, 0, 100, 3, 250);
                        } else {
                                battleGround = new Battle(1, 0, 100, 5, 125);
                        }
                } catch (Exception e) {
                }
                lanes = new ArrayList<>();
                for (Lane lane : battleGround.getLanes()) {
                        lanes.add(lane);
                }
                label1 = new Label("Current score = " + battleGround.getScore());
                label1.setStyle("-fx-background-color: #ab916d; -fx-border-color: #ffffff; -fx-border-width: 1;");
                label1.setFont(Font.font("Arial", 25));
                label2 = new Label("Current turn = " + battleGround.getNumberOfTurns());
                label2.setStyle("-fx-background-color: #ab916d; -fx-border-color: #ffffff; -fx-border-width: 1;");
                label2.setFont(Font.font("Arial", 25));
                label3 = new Label("Current phase = " + battleGround.getBattlePhase());
                label3.setStyle("-fx-background-color: #ab916d; -fx-border-color: #ffffff; -fx-border-width: 1;");
                label3.setFont(Font.font("Arial", 25));
                label4 = new Label("Current Resources = " + battleGround.getResourcesGathered());
                label4.setStyle("-fx-background-color: #ab916d; -fx-border-color: #ffffff; -fx-border-width: 1;");
                label4.setFont(Font.font("Arial", 25));

                int numLanes = battleGround.getOriginalLanes().size();
                labels2 = new Label[numLanes];
                for (int i = 0; i < numLanes; i++) {
                        Lane lane = battleGround.getOriginalLanes().get(i);
                        int x = i + 1;
                        String labelText = "Wall " + x + " Health: " + lane.getLaneWall().getCurrentHealth();
                        labels2[i] = new Label(labelText);
                        labels2[i].setStyle("-fx-background-color: #ab916d; -fx-border-color: #ffffff; -fx-border-width: 1;");
                        labels2[i].setFont(Font.font("Arial", 25));
                }

                hBox.setAlignment(TOP_LEFT);
                hBox.setPadding(new Insets(50));
                hBox.getChildren().addAll(label1, label2, label3, label4);

                hBox2.setAlignment(TOP_LEFT);
                hBox2.setPadding(new Insets(80));
                hBox2.getChildren().addAll(labels2);

                // Add hBox and hBox2 to the parent VBox
                vBox.getChildren().addAll(hBox, hBox2);
        }

        public VBox getLabels() {
                return vBox;
        }

        public static void refreshValues() {
                try {
                        battleGround.passTurn();
                        label1.setText("Current score = " + battleGround.getScore());
                        label2.setText("Current turn = " + battleGround.getNumberOfTurns());
                        label3.setText("Current phase = " + battleGround.getBattlePhase());
                        label4.setText("Current Resources = " + battleGround.getResourcesGathered());

                        for (int i = 0; i < battleGround.getOriginalLanes().size(); i++) {
                                Lane lane = battleGround.getOriginalLanes().get(i);
                                int x = i + 1;
                                if(!lane.isLaneLost())
                                         s = "Active";
                                else {
                                         s = "Not-Active";
                                }

                                String labels = "Lane: " + x + "\n" + "Danger: " + lane.getDangerLevel() + "\n" + s;
                                MainMenu2.lanes[i].setText(labels);
                        }
                        for (int i = 0; i < battleGround.getOriginalLanes().size(); i++) {
                                Lane lane = battleGround.getOriginalLanes().get(i);
                                int x = i + 1;
                                String labelText = "Wall " + x + " Health: " + lane.getLaneWall().getCurrentHealth();
                                labels2[i].setText(labelText);
                        }
                } catch (Exception e) {
                        // handle exception
                }
        }

        public static void moveTitanHard() {
                int count = 0;
                for (Lane l : battleGround.getLanes()) {
                        laneHard[count].getChildren().clear();
                        for (Titan t : l.getTitans()) {
                                ImageView titanImageView = new ImageView();

                                if (t instanceof PureTitan) {
                                        titanImageView.setImage(PURE_TITAN_IMAGE);
                                } else if (t instanceof AbnormalTitan) {
                                        titanImageView.setImage(ABNORMAL_TITAN_IMAGE);
                                } else if (t instanceof ArmoredTitan) {
                                        titanImageView.setImage(ARMORED_TITAN_IMAGE);
                                } else if (t instanceof ColossalTitan) {
                                        titanImageView.setImage(COLOSSAL_TITAN_IMAGE);
                                }

                                titanImageView.setFitWidth(50);
                                titanImageView.setFitHeight(50);
                                titanImageView.setTranslateX(t.getDistance() * 10);
                                laneHard[count].getChildren().add(titanImageView);
                        }
                        count++;
                }
        }

        public static void moveTitanEasy() {
                int count = 0;
                for (Lane l : battleGround.getLanes()) {
                        laneEasy[count].getChildren().clear();
                        for (Titan t : l.getTitans()) {
                                ImageView titanImageView = new ImageView();

                                if (t instanceof PureTitan) {
                                        titanImageView.setImage(PURE_TITAN_IMAGE);
                                } else if (t instanceof AbnormalTitan) {
                                        titanImageView.setImage(ABNORMAL_TITAN_IMAGE);
                                } else if (t instanceof ArmoredTitan) {
                                        titanImageView.setImage(ARMORED_TITAN_IMAGE);
                                } else if (t instanceof ColossalTitan) {
                                        titanImageView.setImage(COLOSSAL_TITAN_IMAGE);
                                }

                                titanImageView.setFitWidth(60);
                                titanImageView.setFitHeight(60);
                                titanImageView.setTranslateX(t.getDistance() * 10);
                                laneEasy[count].getChildren().add(titanImageView);
                        }
                        count++;
                }

        }
}