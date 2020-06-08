package view;


import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SHIP;

import java.util.ArrayList;
import java.util.Random;


public class GameView {

    //Constructor
    public GameView() {

        initializationGame();
        creatKeyListener();
        randomPosition = new Random();

    }

    //Fields
    protected AnchorPane gamePane;
    protected Scene gameScene;
    protected Stage gameStage;
    //protected Stage menuStage;
    protected ImageView ship;
    protected static final int G_WEIGHT = 800;
    protected static final int G_HEIGHT = 900;

    //moveShip and loop
    protected boolean isLeftKeyPressed;
    protected boolean isRightKeyPressed;
    protected boolean isTopKeyPressed;
    protected boolean isDownKeyPressed;
    protected int angle;
    protected AnimationTimer gameTime;

    //backgroundElement
    protected GridPane gridPane1;
    protected GridPane gridPane2;
    protected static final String Background_Image = "view/resources/background.png";

    //shipMoveElement
    protected static final String remains_Image1 = "view/resources/remains/Ship_White.png";
    protected static final String remains_Image2 = "view/resources/remains/Ship_Black.png";
    protected ImageView[] remains1;
    protected ImageView[] remains2;
    Random randomPosition;

    protected ImageView life;
    protected ImageView crew;
    protected ImageView whirlwind;
    protected ImageView[] crews;
    protected ImageView[] shipLifes;
    protected int shipLife;
    protected int points;
    protected final static String Life_Image = "view/resources/life.png";
    protected final static String Crew_Image = "view/resources/crew/crewA.png";
    protected final static String Whirlwind_Image = "view/resources/whirlwind.png";

    //Collide
    protected final static int ShipP_Radius = 30;
    protected final static int Crew_Radius = 15;
    protected final static int ShipRandom_Radius = 20;
    protected final static int Whirlwind_Radius = 96;

    protected Label text = new Label();

    //Initialization of the game's scene
    public void initializationGame() {
        gamePane = new AnchorPane();
        gameScene = new Scene(gamePane, G_WEIGHT, G_HEIGHT);
        gameStage = new Stage();
        gameStage.setScene(gameScene);

    }

    //Create Game
    public void creatNewGame(SHIP shipChoose) {
        creatBackgroundGame();
        textGame();
        creatShip(shipChoose);
        creatElements(shipChoose);
        checkPositionElementCollide();
        creatGameLoop();
        gameStage.setFullScreen(true);
        gameStage.show();
    }

    private void textGame() {
        Text text = new Text("Save ten crews !!");
        text.setFont(new Font("Arial", 50));
        text.setLayoutX(600);
        text.setLayoutY(50);
        gamePane.getChildren().add(text);
    }


    public void creatBackgroundGame() {

        gridPane1 = new GridPane();
        gridPane2 = new GridPane();

        for (int i = 0; i < 12; i++) {
            ImageView backgroundImg1 = new ImageView(Background_Image);
            ImageView backgroundImg2 = new ImageView(Background_Image);
            GridPane.setConstraints(backgroundImg1, i % 4, i / 4);
            GridPane.setConstraints(backgroundImg2, i % 4, i / 4);
            gridPane1.getChildren().add(backgroundImg1);
            gridPane2.getChildren().add(backgroundImg2);
        }
        gridPane2.setLayoutY(-1024);

        gamePane.getChildren().addAll(gridPane1, gridPane2);
    }

    //moveBackground
    protected void moveBackgrond() {
        gridPane1.setLayoutY(gridPane1.getLayoutY() + 0.5);
        gridPane2.setLayoutY(gridPane2.getLayoutY() + 0.5);

        if (gridPane1.getLayoutY() >= 1024) {
            gridPane1.setLayoutY(-1024);
        }
        if (gridPane2.getLayoutY() >= 1024) {
            gridPane2.setLayoutY(-1024);
        }
    }

    protected void creatShip(SHIP shipChoose) {
        ship = new ImageView(shipChoose.getUrlShip());
        ship.setLayoutX(G_WEIGHT - 400);
        ship.setLayoutY(G_HEIGHT - 200);
        gamePane.getChildren().addAll(ship);
    }

    protected void creatKeyListener() {

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = true;
            } else if (event.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = true;
            } else if (event.getCode() == KeyCode.UP) {
                isTopKeyPressed = true;
            } else if (event.getCode() == KeyCode.DOWN) {
                isDownKeyPressed = true;
            }
        });

        gameScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.LEFT) {
                isLeftKeyPressed = false;
            } else if (event.getCode() == KeyCode.RIGHT) {
                isRightKeyPressed = false;
            } else if (event.getCode() == KeyCode.UP) {
                isTopKeyPressed = false;
            } else if (event.getCode() == KeyCode.DOWN) {
                isDownKeyPressed = false;
            }
        });

    }

    protected void moveShip() {

        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > -20) {
                ship.setLayoutX(ship.getLayoutX() - 3);
            }
        }

        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship.setRotate(angle);
            if (ship.getLayoutX() > 0) {
                ship.setLayoutX(ship.getLayoutX() + 3);
            }
        }

        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }

        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship.setRotate(angle);
        }
        if (isTopKeyPressed && !isDownKeyPressed) {
            ship.setLayoutY(ship.getLayoutY() - 3);
        }
        if (!isTopKeyPressed && isDownKeyPressed) {
            ship.setLayoutY(ship.getLayoutY() + 3);
        }

        //Block on the borders
        while (ship.getLayoutX() > 1617.9) { //droite
            ship.setLayoutX(ship.getLayoutX() - 0.1);
        }
        while (ship.getLayoutX() < 0) { //gauche
            ship.setLayoutX(ship.getLayoutX() + 0.1);
        }
        while (ship.getLayoutY() > 942.99) { //en bas
            ship.setLayoutY(ship.getLayoutY() - 0.1);
        }
        while (ship.getLayoutY() < 0.90) { // en haut
            ship.setLayoutY(ship.getLayoutY() + 0.1);
        }

    }


    protected void creatElements(SHIP shipChoose) {

        //Rectangle r = new Rectangle(500, 500, 70, 118);
        //gamePane.getChildren().add(r);

        whirlwind = new ImageView(Whirlwind_Image);
        whirlwind.setLayoutX(800);
        whirlwind.setLayoutY(500);
        whirlwind.setVisible(false);
        gamePane.getChildren().add(whirlwind);

        crew = new ImageView(Crew_Image);
        setNewPositionElement(crew);
        gamePane.getChildren().add(crew);
        crews = new ImageView[30];
        for (int i = 0; i < crews.length; i++) {
            crews[i] = new ImageView(Crew_Image);
            crews[i].setLayoutX(randomPosition.nextInt(1000));
            crews[i].setLayoutY(randomPosition.nextInt(1500));
            gamePane.getChildren().add(crews[i]);
            //System.out.println(crews[i].getLayoutX());
        }

        shipLife = 2;
        shipLifes = new ImageView[3];

        for (int i = 0; i < shipLifes.length; i++) {
            shipLifes[i] = new ImageView(shipChoose.getLifeShip());
            shipLifes[i].setLayoutX(1600 + i * 20);
            shipLifes[i].setLayoutY(50);
            gamePane.getChildren().add(shipLifes[i]);
        }

        remains1 = new ImageView[20];
        for (int i = 0; i < remains1.length; i++) {
            remains1[i] = new ImageView(remains_Image1);
            gamePane.getChildren().add(remains1[i]);
        }
        remains2 = new ImageView[20];
        for (int i = 0; i < remains2.length; i++) {
            remains2[i] = new ImageView(remains_Image2);
            gamePane.getChildren().add(remains2[i]);
        }
    }

    protected void setNewPositionElement(ImageView img) {
        img.setLayoutY(randomPosition.nextInt(2000));
        img.setLayoutY(-(randomPosition.nextInt(3200)));
        img.setLayoutX(randomPosition.nextInt(2000));
    }

    protected void moveElementRemains() {
        for (int i = 0; i < remains1.length; i++) {
            remains1[i].setLayoutY(remains1[i].getLayoutY() + 3);
        }
        for (int i = 0; i < remains2.length; i++) {
            remains2[i].setLayoutY(remains2[i].getLayoutY() + 3);
        }
    }

    protected void checkPositionElementEndMap() {

        for (int i = 0; i < remains1.length; i++) {
            if (remains1[i].getLayoutY() > 1010) {
                setNewPositionElement(remains1[i]);
            }
        }
        for (int i = 0; i < remains2.length; i++) {
            if (remains2[i].getLayoutY() > 1010) {
                setNewPositionElement(remains2[i]);
            }
        }
    }

    protected void checkPositionElementCollide() {

        for (int i = 0; i < crews.length; i++) {

            if (ShipP_Radius + Crew_Radius > distanceTwoPoint(ship.getLayoutX(), crews[i].getLayoutX(), ship.getLayoutY(), crews[i].getLayoutY())) {
                points++;
                setNewPositionElement(crews[i]);
                if (points >= 10) {
                    gameStage.setFullScreen(false);
                    WinView end = new WinView();
                    end.startStage();
                    gameStage.close();
                    gameTime.stop();
                }
            }
        }

        for (int i = 0; i < remains1.length; i++) {
            if (ShipRandom_Radius + ShipP_Radius > distanceTwoPoint(ship.getLayoutX() + 49, remains1[i].getLayoutX() + 20, ship.getLayoutY() + 37, remains1[i].getLayoutY() + 20)) {
                removeLife();
                setNewPositionElement(remains1[i]);
            }
        }
        for (int i = 0; i < remains2.length; i++) {
            if (ShipRandom_Radius + ShipP_Radius > distanceTwoPoint(ship.getLayoutX() + 49, remains2[i].getLayoutX() + 20, ship.getLayoutY() + 37, remains2[i].getLayoutY() + 20)) {
                removeLife();
                setNewPositionElement(remains2[i]);
            }
        }
    }

    protected void removeLife() {

        gamePane.getChildren().remove(shipLifes[shipLife]);
        shipLife--;
        if (shipLife < 0) {
            gameStage.setFullScreen(false);
            LoseView end = new LoseView();
            end.startStage();
            gameStage.close();
            gameTime.stop();
        }
    }

    protected double distanceTwoPoint(double x1, double x2, double y1, double y2) {
        double x = x1 - x2;
        double y = y1 - y2;
        double a = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return a;
    }

    //Game Loop
    protected void creatGameLoop() {
        gameTime = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBackgrond();
                moveElementRemains();
                checkPositionElementEndMap();
                checkPositionElementCollide();
                moveShip();
            }
        };
        gameTime.start();
    }
}


