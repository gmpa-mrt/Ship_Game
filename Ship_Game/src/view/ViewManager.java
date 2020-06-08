package view;



import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;


import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SHIP;


public class ViewManager {

    //Constructor
    public ViewManager() {
        mainPane = new AnchorPane();
        mainScene = new Scene(mainPane, WIDTH, HEIGHT);
        mainStage = new Stage();
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        title();
        buttonMenu();
        creatBackground();
    }

    //Fields
    private static final int WIDTH = 750;
    private static final int HEIGHT = 700;
    private AnchorPane mainPane;
    private Scene mainScene;
    private Stage mainStage;

    //Getter
    public Stage getMainStage() {
        return mainStage;
    }

    private void title () {
        //Create title
        Label title = new Label("Ship Game");
        title.setFont(new Font("Arial", 30));
        title.setLayoutX(150);
        title.setLayoutY(150);
        mainPane.getChildren().add(title);
    }

    private void buttonMenu() {

        // Menu Button
        Group buttonMenu = new Group();
        Button solo = new Button("Beginner");// first button to start the game
        Button multi = new Button("Multi");
        Button readme = new Button("Read Me");
        Button exit = new Button("Exit");

        //Level Button
        Button medium = new Button("Medium");
        Button hard = new Button("Hard");
        Button extreme = new Button("Extreme");


        /** CreatEventButton **/
        //Start Game
        solo.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            GameView gameView = new GameView();
            gameView.creatNewGame(SHIP.YELLOW);
        });
        medium.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            MediumLvlGame mediumLvlGame = new MediumLvlGame();
            mediumLvlGame.creatNewGame(SHIP.YELLOW);
        });
        hard.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            HardLvlGame hardLvlGame = new HardLvlGame();
            hardLvlGame.creatNewGame(SHIP.YELLOW);
        });
        extreme.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            ExtremeLvlGame extremeLvlGame = new ExtremeLvlGame();
            extremeLvlGame.creatNewGame(SHIP.YELLOW);
        });
        //Multi
        multi.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            MultiPlayerGame multiPlayerGame = new MultiPlayerGame();
            multiPlayerGame.creatNewGame(SHIP.YELLOW, SHIP.GREEN);
        });
        //Read Me
        readme.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            ReadMe readMe = new ReadMe();
            readMe.startRead();
        });
        //Exit Game
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            mainStage.hide();
        });

       // buttonMenu.getChildren().addAll(solo, multi, exit);


        // Box Organization
        VBox vBox = new VBox(50, solo, multi, readme, exit);
        vBox.setLayoutX(350);// voir le bind
        vBox.setLayoutY(250);
        vBox.getChildren().add(buttonMenu);

        mainPane.getChildren().addAll(vBox);

        HBox hBox = new HBox(20, medium, hard, extreme);
        hBox.setLayoutX(440);
        hBox.setLayoutY(250);
        mainPane.getChildren().add(hBox);
    }

    private void creatBackground() {
        Image backgroundImg = new Image("view/resources/Welcome.jpg", 750, 700, false, true);
        BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        mainPane.setBackground(new Background(background));
    }
}
