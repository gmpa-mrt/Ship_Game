package view;

import javafx.animation.AnimationTimer;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.SHIP;

public class MultiPlayerGame extends GameView {

    //Constructor
    public MultiPlayerGame() {
        creatKeyListener2();
    }

    //Fields
    protected ImageView ship2;


    public void creatNewGame(SHIP shipChoose, SHIP shipChoose2) {
        creatBackgroundGame();
        creatShip2(shipChoose2);
        creatElements(shipChoose2);
        super.creatShip(shipChoose);
        checkPositionElementCollide2();

        creatGameLoop();
        gameStage.setFullScreen(true);
        gameStage.show();
    }


    private void creatShip2(SHIP shipChoose2) {
        ship2 = new ImageView(shipChoose2.getUrlShip());
        ship2.setLayoutX(G_WEIGHT - 200);
        ship2.setLayoutY(G_HEIGHT - 200);
        gamePane.getChildren().add(ship2);
    }


    private void creatKeyListener2() {

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.A) {
                isLeftKeyPressed = true;
            } else if (event.getCode() == KeyCode.D) {
                isRightKeyPressed = true;
            } else if (event.getCode() == KeyCode.W) {
                isTopKeyPressed = true;
            } else if (event.getCode() == KeyCode.S) {
                isDownKeyPressed = true;
            }
        });

        gameScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> {
            if (event.getCode() == KeyCode.A) {
                isLeftKeyPressed = false;
            } else if (event.getCode() == KeyCode.D) {
                isRightKeyPressed = false;
            } else if (event.getCode() == KeyCode.W) {
                isTopKeyPressed = false;
            } else if (event.getCode() == KeyCode.S) {
                isDownKeyPressed = false;
            }
        });
    }


    protected void moveShip2() {

        if (isLeftKeyPressed && !isRightKeyPressed) {
            if (angle > -30) {
                angle -= 5;
            }
            ship2.setRotate(angle);
            if (ship2.getLayoutX() > -20) {
                ship2.setLayoutX(ship2.getLayoutX() - 3);
            }
        }

        if (isRightKeyPressed && !isLeftKeyPressed) {
            if (angle < 30) {
                angle += 5;
            }
            ship2.setRotate(angle);
            if (ship2.getLayoutX() > 0) {
                ship2.setLayoutX(ship2.getLayoutX() + 3);
            }
        }

        if (isLeftKeyPressed && isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship2.setRotate(angle);
        }

        if (!isLeftKeyPressed && !isRightKeyPressed) {
            if (angle < 0) {
                angle += 5;
            } else if (angle > 0) {
                angle -= 5;
            }
            ship2.setRotate(angle);
        }
        if (isTopKeyPressed && !isDownKeyPressed) {
            ship2.setLayoutY(ship2.getLayoutY() - 3);
        }
        if (!isTopKeyPressed && isDownKeyPressed) {
            ship2.setLayoutY(ship2.getLayoutY() + 3);
        }

        //Block on the borders
        while (ship2.getLayoutX() > 1617.9) {
            ship2.setLayoutX(ship2.getLayoutX() - 0.1);
        }
        while (ship2.getLayoutX() < 0) {
            ship2.setLayoutX(ship2.getLayoutX() + 0.1);
        }
        while (ship2.getLayoutY() > 942.99) {
            ship2.setLayoutY(ship2.getLayoutY() - 0.1);
        }
        while (ship2.getLayoutY() < 0.90) {
            ship2.setLayoutY(ship2.getLayoutY() + 0.1);
        }
        //System.out.println(ship2.getLayoutX());
        //System.out.println(ship2.getLayoutY());
    }

    protected void creatElements2(SHIP shipChoose2) {
        crew = new ImageView(Crew_Image);
        setNewPositionElement(crew);
        gamePane.getChildren().add(crew);
        crews = new ImageView[15];
        for(int i = 0; i < crews.length; i++) {
            crews[i] = new ImageView(Crew_Image);
            crews[i].setLayoutX(randomPosition.nextInt(1000));
            crews[i].setLayoutY(randomPosition.nextInt(1500));
            gamePane.getChildren().add(crews[i]);
            //System.out.println(crews[i].getLayoutX());
        }

        shipLife = 2;
        shipLifes = new ImageView[3];

        for (int i = 0; i < shipLifes.length; i++) {
            shipLifes[i] = new ImageView(shipChoose2.getLifeShip());
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

    protected void checkPositionElementCollide2() {
        for (int i = 0; i < crews.length; i++) {
            // System.out.println(crews[i].getLayoutX());
            if (ShipP_Radius + Crew_Radius > distanceTwoPoint(ship2.getLayoutX(), crews[i].getLayoutX(), ship2.getLayoutY() , crews[i].getLayoutY())) {
                points++;
                setNewPositionElement(crews[i]);
                System.out.println(points);

                if (points == 10){
                    gameStage.close();
                    gameTime.stop();
                }
            }
        }
        for (int i = 0; i < remains1.length; i++) {
            if (ShipRandom_Radius + ShipP_Radius > distanceTwoPoint(ship2.getLayoutX() + 49, remains1[i].getLayoutX() + 20, ship2.getLayoutY() + 37, remains1[i].getLayoutY() + 20)) {
                removeLife2();
                setNewPositionElement(remains1[i]);
            }
        }
        for (int i = 0; i < remains2.length; i++) {
            if (ShipRandom_Radius + ShipP_Radius > distanceTwoPoint(ship2.getLayoutX() + 49, remains2[i].getLayoutX() + 20, ship2.getLayoutY() + 37, remains2[i].getLayoutY() + 20)) {
                removeLife2();
                setNewPositionElement(remains2[i]);
            }
        }
    }

    protected void removeLife2() {
        gamePane.getChildren().remove(shipLifes[shipLife]);
        shipLife--;
        if (shipLife < 0) {
            Label lose = new Label("GAME OVER");
            lose.getLabelFor();
            gameStage.close();
            gameTime.stop();
        }
    }


    //Game Loop
    protected void creatGameLoop() {
        gameTime = new AnimationTimer() {
            @Override
            public void handle(long l) {
                moveBackgrond();
                moveElementRemains();
                checkPositionElementEndMap();
                checkPositionElementCollide2();
                moveShip2();
            }
        };
        gameTime.start();
    }

}
