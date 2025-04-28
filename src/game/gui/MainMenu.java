package game.gui;

import game.engine.Battle;
import game.engine.weapons.WeaponRegistry;
import game.engine.weapons.factory.WeaponFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;

public class MainMenu extends Application {
    public static Labeled[] lanes;
    private BorderPane root;
    private StackPane backgroundPane;
    private ImageView backgroundImageView;
    private Text title;
    private Label modeDescription;
    private Button modeToggle;
    private Button startButton;
    private Button backButton;
    private Button hardButton;
    private Button instructionsButton;
    private boolean isEasyMode = true;
    private Stage Stage;
    private Scene scene1, scene2;
    private Button rulesButton ;
    private Button laneButton,laneButton1,laneButton2,laneButton3,laneButton4,laneButton5 , passTurn;
    public Battle battle = Controller.battleGround;
    public WeaponRegistry weaponRegistry;
    public WeaponFactory weaponFactory;

    @Override
    public void start(Stage primaryStage) {
        try {
            this.Stage = primaryStage;
            //showStartScreen();

            // Create the root pane and set dimensions
            StackPane root = new StackPane();
            // Background Pane
            StackPane backgroundPane = new StackPane();
            backgroundImageView = new ImageView();
            backgroundImageView.setFitWidth(800);
            backgroundImageView.setFitHeight(600);
            backgroundImageView.setPreserveRatio(true);
            backgroundPane.getChildren().add(backgroundImageView);


            // Background Image (animated GIF or slideshow)
            Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Background2.png"))); // Update path
            ImageView bgImageView = new ImageView(bgImage);
            bgImageView.setFitWidth(1535);
            bgImageView.setFitHeight(2160);
            bgImageView.setPreserveRatio(true);

            startButton = new Button("Start Game");
            startButton.setFont(Font.font("Arial", 18));
            startButton.setStyle("-fx-base: #4CAF50; -fx-text-fill: white;");
             startButton.setOnAction(e -> buttonEffectSound());
            startButton.setOnAction(e -> switchToScene2());



            backButton = new Button("Exit");
            backButton.setFont(Font.font("Arial", 18));
            backButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
            backButton.setOnAction(e -> Exit(primaryStage));

            instructionsButton = new Button("Instructions");
            instructionsButton.setFont(Font.font("Arial", 18));
            instructionsButton.setStyle("-fx-base: #666; -fx-text-fill: white;");
            instructionsButton.setOnAction(e -> displayInstructions());

            // Layout for buttons
            HBox buttonBox = new HBox(10, startButton, instructionsButton, backButton);
            buttonBox.setPadding(new Insets(120));
            buttonBox.setAlignment(Pos.BOTTOM_CENTER);

            // Add all elements to the root
            root.getChildren().addAll(bgImageView, buttonBox);
            root.setBackground(backgroundPane.getBackground());

            Audio.playBackgroundMusic();
            Stage.setFullScreen(true);

            Scene scene = new Scene(root);
            primaryStage.setTitle("Attack on Titan: Utopia");
            //primaryStage.setFullScreen(true);
            primaryStage.setScene(scene);
            primaryStage.show();
            primaryStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("LOGO.png"))));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void Exit(Stage primaryStage) {
        Stage Exit = new Stage();
        Exit.setTitle("Exit");
        Label a = new Label("Are You Sure? and" +"\n"+
                " seeking for the Bonus....!?");
        a.setFont(Font.font("Arial", 18));
        a.setTextFill(Color.FIREBRICK);
        a.setEffect(new DropShadow(5, Color.GRAY));
        Button yesButton = new Button("Yes");
        yesButton.setFont(Font.font("Arial", 18));
        yesButton.setStyle("-fx-base: #4CAF50; -fx-text-fill: white;");
        yesButton.setOnAction(actionEvent -> {
            primaryStage.close();
            Exit.close();
        });

        Button noButton = new Button("No");
        noButton.setFont(Font.font("Arial", 18));
        noButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
        noButton.setOnAction(actionEvent -> {
            Exit.close();
        });

        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(a,yesButton,noButton);
        Scene scene = new Scene(layout, 500,300);
        Exit.setScene(scene);
        Exit.show();
        Exit.getIcons().add(new Image(getClass().getResourceAsStream("exit.png")));

    }
    private void displayInstructions() {
        Stage instructionsStage = new Stage();
        instructionsStage.setTitle("Instructions Menu");

        String instructionsText = "Welcome to 'Attack on Titan: Utopia'! As the last line of defense against the titan onslaught, your mission is to protect the Utopia District and prevent the titans from breaching Wall Rose. You'll navigate through an endless battle across multiple lanes, each representing a section of the wall under attack. Deploy your arsenal of anti-titan weapons strategically to fend off the advancing titans. Keep a keen eye on the battlefield, monitoring the health of the walls, the approaching titans, and the resources at your disposal. Choose your actions wisely each turn, whether it's purchasing and deploying weapons or observing the titan movements. Defeating titans earns you valuable resources and increases your score. But beware, losing all wall sections in a lane marks it as lost, reducing your defensive capabilities. Stay vigilant and adapt to the changing battle phases as the intensity of the titan onslaught increases over time. Your ultimate goal is survival; how long can you withstand the titan onslaught and defend humanity's last bastion of hope?";
        Label l1 = new Label("INSTRUCTIONS");
        l1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        l1.setTextFill(Color.FIREBRICK);
        l1.setEffect(new DropShadow(5, Color.GRAY));
        l1.setWrapText(true);
        l1.setAlignment(Pos.TOP_CENTER);
        l1.setLineSpacing(5);

        Label l2 = new Label(instructionsText);
        l2.setFont(Font.font("Arial", 18));
        l2.setTextFill(Color.BLACK);
        l2.setEffect(new DropShadow(5, Color.GRAY));
        l2.setWrapText(true);
        l2.setAlignment(Pos.CENTER);
        l2.setLineSpacing(5);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(l1,l2);
        layout.setAlignment(Pos.CENTER);

        layout.setPadding(new Insets(20));
        layout.setSpacing(30);

        Scene scene = new Scene(layout, 700, 500);
        instructionsStage.setScene(scene);
        instructionsStage.show();
        instructionsStage.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("stove.png"))));

    }
    private void switchToScene2() {

        StackPane root = new StackPane();
        Stage stage = new Stage();
        // Background Pane
        StackPane backgroundPane = new StackPane();
        backgroundImageView = new ImageView();
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setPreserveRatio(true);
        backgroundPane.getChildren().add(backgroundImageView);
        // Background Image (animated GIF or slideshow)
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Scene2.png"))); // Update path
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1535);
        bgImageView.setFitHeight(2160);
        bgImageView.setPreserveRatio(true);
        startButton = new Button("Play");
        startButton.setFont(Font.font("Arial", 18));
        startButton.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        startButton.setOnAction(e -> modeScene());

        rulesButton = new Button("Game Clarification and Rules");
        rulesButton.setFont(Font.font("Arial", 18));
        rulesButton.setStyle("-fx-base: #666; -fx-text-fill: white;");
        rulesButton.setOnAction(e -> rules());

        backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 18));
        backButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
        backButton.setOnAction(e -> start(Stage) );
        // Layout for buttons
        HBox buttonBox = new HBox(10, startButton,rulesButton, backButton);
        buttonBox.setPadding(new Insets(120));
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(bgImageView, buttonBox );
        root.setBackground(backgroundPane.getBackground());
        Stage.setMaximized(true);
        Scene scene2 = new Scene(root);
        //Stage.setFullScreenExitKeyCombination(null);
        Stage.setScene(scene2);
        Stage.show();
    }
    private void buttonEffectSound(){
        buttoneffect.buttonEffect();
    }
    private void modeScene(){
        StackPane root = new StackPane();
        // Background Pane
        StackPane backgroundPane = new StackPane();
        backgroundImageView = new ImageView();
        backgroundImageView.setPreserveRatio(true);
        backgroundPane.getChildren().add(backgroundImageView);
        // Background Image (animated GIF or slideshow)
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("Mode Scene.png"))); // Update path
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1535);
        bgImageView.setFitHeight(2160);
        bgImageView.setPreserveRatio(true);
        modeToggle = new Button("Easy Mode");
        modeToggle.setFont(Font.font("italic", 18));
        modeToggle.setStyle("-fx-base: #666; -fx-text-fill: white;");
        modeToggle.setOnAction(e -> battelGroundEasy());

        hardButton = new Button("Hard Mode");
        hardButton.setFont(Font.font("italic", 18));
        hardButton.setStyle("-fx-base: #666; -fx-text-fill: white;");
        hardButton.setOnAction(e -> battelGroundHard());

        backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 18));
        backButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
        backButton.setOnAction(e -> switchToScene2() );
        // Layout for buttons
        HBox buttonBox = new HBox(10, modeToggle , hardButton, backButton);
        buttonBox.setPadding(new Insets(120));
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(bgImageView, buttonBox);
        root.setBackground(backgroundPane.getBackground());
        Stage.setFullScreen(true);
        Scene modeScene = new Scene(root);
        Stage.setScene(modeScene);
        Stage.show();
    }
    private void weaponShopScenehard(){
        //showStartScreen();
        Stage stage = new Stage();
        StackPane root = new StackPane();
        // Background Pane
        StackPane backgroundPane = new StackPane();
        backgroundImageView = new ImageView();
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setPreserveRatio(true);
        backgroundPane.getChildren().add(backgroundImageView);
        // Background Image (animated GIF or slideshow)
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("weaponShop.png"))); // Update path
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1535);
        bgImageView.setFitHeight(2160);
        bgImageView.setPreserveRatio(true);

        backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 18));
        backButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
        backButton.setOnAction(e -> battelGroundHard() );

        Button PiercingCannon = new Button("Piercing Cannon");
        Button SniperCannon = new Button("Sniper Cannon");
        Button VolleySpreadCannon  = new Button("VolleySpread Cannon");
        Button WallTrap = new Button("WallTrap");
        PiercingCannon.setFont(Font.font("Arial", 18));
        SniperCannon.setFont(Font.font("Arial", 18));
        VolleySpreadCannon.setFont(Font.font("Arial", 18));
        WallTrap.setFont(Font.font("Arial", 18));
        TextArea textArea = new TextArea();
        textArea.setPrefSize(150,150);
        textArea.setOpacity(1);


        String infoPiercingCannon = "Weapon Type: PiercingCannon\nWeapon Name: Anti-Titan Shell\n" + "Price: 25" + "\n" +
                "The Damage: 10" ;
        String infoSniperCannon = "Weapon Type: SniperCannon\nWeapon Name: Long Range Spear\n" + "Price: 25" +"\n" +
                "The Damage: 35 " ;
        String infoVolleySpreadCannon = "Weapon Type: VolleySpreadCannon\nWeapon Name: Volley Spread Cannon\n" + "Price: 100 "  + "\n" +
                "The Damage: 5" + "\n" + "Min Range: 20" + "\n"
                + "Max Range: 50 " ;
        String infoWallTrap = "Weapon Type: WallTrap\nWeapon Name: Proximity Trap\n" + "Price: 75"  + "\n" +
                "The Damage: 100" ;

        textArea.setEditable(false);
        PiercingCannon.setOnAction(e -> textArea.setText(infoPiercingCannon));
        SniperCannon.setOnAction(e -> textArea.setText(infoSniperCannon));
        VolleySpreadCannon.setOnAction(e -> textArea.setText(infoVolleySpreadCannon));
        WallTrap.setOnAction(e -> textArea.setText(infoWallTrap));


        HBox buttonBox = new HBox(10, backButton ,PiercingCannon,SniperCannon,VolleySpreadCannon,WallTrap);
        VBox box = new VBox(textArea , buttonBox);
        buttonBox.setPadding(new Insets(120));
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(bgImageView ,box );
        root.setBackground(backgroundPane.getBackground());
        Stage.setFullScreen(true);
        Scene weaponShop = new Scene(root);
        Stage.setScene(weaponShop);
        Stage.show();
    }
    private void weaponShopSceneeasy(){
      //  this.battle = ;
        //showStartScreen();
        StackPane root = new StackPane();
        // Background Pane
        StackPane backgroundPane = new StackPane();
        backgroundImageView = new ImageView();
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setPreserveRatio(true);
        backgroundPane.getChildren().add(backgroundImageView);
        // Background Image (animated GIF or slideshow)
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("weaponShop.png"))); // Update path
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1535);
        bgImageView.setFitHeight(2160);
        bgImageView.setPreserveRatio(true);

        backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 18));
        backButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
        backButton.setOnAction(e -> battelGroundEasy() );

        Button PiercingCannon = new Button("Piercing Cannon");
        Button SniperCannon = new Button("Sniper Cannon");
        Button VolleySpreadCannon  = new Button("VolleySpread Cannon");
        Button WallTrap = new Button("WallTrap");
        PiercingCannon.setFont(Font.font("Arial", 18));
        SniperCannon.setFont(Font.font("Arial", 18));
        VolleySpreadCannon.setFont(Font.font("Arial", 18));
        WallTrap.setFont(Font.font("Arial", 18));
        TextArea textArea = new TextArea();
        textArea.setPrefSize(150,150);
        textArea.setOpacity(1);


            String infoPiercingCannon = "Weapon Type: PiercingCannon\nWeapon Name: Anti-Titan Shell\n" + "Price: 25" + "\n" +
                    "The Damage: 10" ;
            String infoSniperCannon = "Weapon Type: SniperCannon\nWeapon Name: Long Range Spear\n" + "Price: 25" +"\n" +
                    "The Damage: 35 " ;
            String infoVolleySpreadCannon = "Weapon Type: VolleySpreadCannon\nWeapon Name: Volley Spread Cannon\n" + "Price: 100 "  + "\n" +
                    "The Damage: 5" + "\n" + "Min Range: 20" + "\n"
                    + "Max Range: 50 " ;
            String infoWallTrap = "Weapon Type: WallTrap\nWeapon Name: Proximity Trap\n" + "Price: 75"  + "\n" +
                    "The Damage: 100" ;
            textArea.setEditable(false);
            PiercingCannon.setOnAction(e -> textArea.setText(infoPiercingCannon));
            SniperCannon.setOnAction(e -> textArea.setText(infoSniperCannon));
            VolleySpreadCannon.setOnAction(e -> textArea.setText(infoVolleySpreadCannon));
            WallTrap.setOnAction(e -> textArea.setText(infoWallTrap));


        HBox buttonBox = new HBox(10, backButton ,PiercingCannon,SniperCannon,VolleySpreadCannon,WallTrap);
        VBox box = new VBox(textArea , buttonBox);
        buttonBox.setPadding(new Insets(120));
        buttonBox.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(bgImageView ,box );
        root.setBackground(backgroundPane.getBackground());
        Stage.setFullScreen(true);
        Scene weaponShop = new Scene(root);
        Stage.setScene(weaponShop);
        Stage.show();
    }
    private void rules() {
        Stage rules = new Stage();
        rules.setTitle(" Clarification and Rules ");

        String ClarificationandRules = "• This is a tower defence game, players’ main goal is protect their lane walls from the\n" +
                "approaching titans by buying weapons into the lanes to attack the titans present in it.\n" +
                "• The game must be initialized with an initial score of 0.\n" +
                "• The number of turns must start with 1.\n" +
                "• The game will have two modes: Easy and Hard.\n" +
                "• The modes will differ according to the following:\n" +
                "Initial Number of Lanes is  3 if Easy ,but  5 if Hard\n" +
                "Initial Resources per Lane is 250 if Easy ,but 125 if Hard\n" +
                "• Each Lane should have a wall at one end and a titan spawn point at the other. You\n" +
                "should specify titan spawn distance according to your screen and chosen orientation of your game.\n" ;
        Label l1 = new Label("Game Clarification and Rules");
        l1.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, 30));
        l1.setTextFill(Color.BLACK);
        l1.setEffect(new DropShadow(5, Color.GRAY));
        l1.setWrapText(true);
        l1.setAlignment(Pos.TOP_CENTER);
        l1.setLineSpacing(5);

        Label l2 = new Label(ClarificationandRules);
        l2.setFont(Font.font("Arial", 18));
        l2.setTextFill(Color.BLACK);
        l2.setEffect(new DropShadow(5, Color.GRAY));
        l2.setWrapText(true);
        l2.setAlignment(Pos.CENTER);
        l2.setLineSpacing(5);

        VBox layout = new VBox(10);
        layout.getChildren().addAll(l1,l2);
        layout.setAlignment(Pos.CENTER);

        layout.setPadding(new Insets(20));
        layout.setSpacing(30);

        Scene scene = new Scene(layout);
        rules.setScene(scene);
        rules.show();
        rules.getIcons().add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("game.png"))));
    }
    private void battelGroundEasy() {
       // showStartScreen();
        StackPane root = new StackPane();
        Controller controller = new Controller(true);
        // Background Pane
        StackPane backgroundPane = new StackPane();
        backgroundImageView = new ImageView();
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setPreserveRatio(true);
        backgroundPane.getChildren().add(backgroundImageView);
        // Background Image (animated GIF or slideshow)
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BattelGround.png"))); // Update path
        ImageView bgImageView = new ImageView(bgImage);
        bgImageView.setFitWidth(1535);
        bgImageView.setFitHeight(2160);
        bgImageView.setPreserveRatio(true);

        backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 18));
        backButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
        backButton.setOnAction(e -> modeScene() );
        backgroundPane.getChildren().add(controller.getLabels());

        startButton = new Button("Weapon Shop");
        startButton.setFont(Font.font("Arial", 18));
        startButton.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        startButton.setOnAction(e -> weaponShopSceneeasy());

        laneButton1 = new Button("Lane 1 ");
        laneButton1.setFont(Font.font("Arial", 18));
        laneButton1.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton1.setOnAction(e -> weaponShopScenehard());

        laneButton2 = new Button("Lane 2");
        laneButton2.setFont(Font.font("Arial", 18));
        laneButton2.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton2.setOnAction(e -> weaponShopScenehard());

        laneButton3 = new Button("Lane 3");
        laneButton3.setFont(Font.font("Arial", 18));
        laneButton3.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton3.setOnAction(e -> weaponShopScenehard());

        passTurn = new Button("Pass Turn");
        passTurn.setFont(Font.font("Arial", 18));
        passTurn.setStyle("-fx-base: #793114; -fx-text-fill: white;");

        VBox lanesBottuns = new VBox(33,laneButton1,laneButton2,laneButton3);
        lanesBottuns.setAlignment(Pos.CENTER_RIGHT);

        VBox lanesContainer = new VBox(10); // Container for lanes with spacing of 10px
        int numberOfLanes = 3; // Number of lanes
        lanesContainer.setAlignment(Pos.CENTER);

        VBox wall = new VBox(10);
        wall.setAlignment(Pos.CENTER);
        wall.setPrefSize(700, 50);

        HBox buttonBox = new HBox(10 ,passTurn, startButton,backButton);
        for (int i = 0; i < numberOfLanes; i++) {
            Pane lane = new Pane();
            lane.setPrefSize(700, 50); // Preferred size for each lane
            lane.setStyle("-fx-background-color: #ab916d; -fx-border-color: #ffffff; -fx-border-width: 1;");
            lanesContainer.getChildren().add(lane);
        }
        buttonBox.setPadding(new Insets(50));
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().addAll(bgImageView ,controller.getLabels(),lanesContainer,lanesBottuns, buttonBox );////////////////////////////////////
        root.setBackground(backgroundPane.getBackground());
        Stage.setFullScreen(true);
        Scene battleEasy = new Scene(root);
        Stage.setScene(battleEasy);
        Stage.show();
    }
    private void battelGroundHard() {
        //showStartScreen();
        StackPane root = new StackPane();
        Controller controller = new Controller(false);
        // Background Pane
        StackPane backgroundPane = new StackPane();
        backgroundImageView = new ImageView();
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(600);
        backgroundImageView.setPreserveRatio(true);
        backgroundPane.getChildren().add(backgroundImageView);
        backgroundPane.getChildren().add(controller.getLabels());
        // Background Image (animated GIF or slideshow)
        Image bgImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("BattelGround.png"))); // Update path
        ImageView bgImageView = new ImageView(bgImage);
       // bgImageView.setSmooth(true);
        bgImageView.setFitWidth(1535);
        bgImageView.setFitHeight(2160);
        bgImageView.setPreserveRatio(true);

        backButton = new Button("Back");
        backButton.setFont(Font.font("Arial", 18));
        backButton.setStyle("-fx-base: #f44336; -fx-text-fill: white;");
        backButton.setOnAction(e -> modeScene());

        startButton = new Button("Weapon Shop");
        startButton.setFont(Font.font("Arial", 18));
        startButton.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        startButton.setOnAction(e -> weaponShopScenehard());

        laneButton1 = new Button("Lane 1 ");
        laneButton1.setFont(Font.font("Arial", 18));
        laneButton1.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton1.setOnAction(e -> weaponShopScenehard());

        laneButton2 = new Button("Lane 2");
        laneButton2.setFont(Font.font("Arial", 18));
        laneButton2.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton2.setOnAction(e -> weaponShopScenehard());

        laneButton3 = new Button("Lane 3");
        laneButton3.setFont(Font.font("Arial", 18));
        laneButton3.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton3.setOnAction(e -> weaponShopScenehard());

        laneButton4 = new Button("Lane 4");
        laneButton4.setFont(Font.font("Arial", 18));
        laneButton4.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton4.setOnAction(e -> weaponShopScenehard());

        laneButton5 = new Button("Lane 5");
        laneButton5.setFont(Font.font("Arial", 18));
        laneButton5.setStyle("-fx-base: #793114; -fx-text-fill: white;");
        //laneButton5.setOnAction(e -> weaponShopScenehard());

        passTurn = new Button("Pass Turn");
        passTurn.setFont(Font.font("Arial", 18));
        passTurn.setStyle("-fx-base: #793114; -fx-text-fill: white;");

        VBox lanesBottuns = new VBox(40,laneButton1,laneButton2,laneButton3,laneButton4,laneButton5);
        lanesBottuns.setAlignment(Pos.CENTER_RIGHT);

        VBox lanesContainer = new VBox(25); // Container for lanes with spacing of 10px
        int numberOfLanes = 5; // Number of lanes
        lanesContainer.setAlignment(Pos.CENTER);


        HBox buttonBox = new HBox(10 ,passTurn,startButton, backButton);
        for (int i = 0; i < numberOfLanes; i++) {
            Pane lane = new Pane();
            lane.setPrefSize(350, 50); // Preferred size for each lane
            lane.setStyle("-fx-background-color: #ab916d; -fx-border-color: #ffffff; -fx-border-width: 1;");
            lanesContainer.getChildren().add(lane);
        }
        buttonBox.setPadding(new Insets(50));
        buttonBox.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().addAll(bgImageView ,controller.getLabels(),lanesContainer ,lanesBottuns,buttonBox  );
        root.setBackground(backgroundPane.getBackground());
        Stage.setFullScreen(true);
        Scene battleHard = new Scene(root);
        Stage.setScene(battleHard);
        Stage.show();
    }


}
