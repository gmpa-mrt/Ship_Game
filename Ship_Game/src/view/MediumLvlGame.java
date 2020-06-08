package view;

import javafx.scene.image.ImageView;
import model.SHIP;

public class MediumLvlGame extends GameView {

    public MediumLvlGame(){
    }
    protected void creatElements(SHIP shipChoose) {
        whirlwind = new ImageView(Whirlwind_Image);
        whirlwind.setLayoutX(800);
        whirlwind.setLayoutY(500);
        whirlwind.setVisible(false);
        gamePane.getChildren().add(whirlwind);


        crew = new ImageView(Crew_Image);
        setNewPositionElement(crew);
        gamePane.getChildren().add(crew);
        crews = new ImageView[25];
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

        remains1 = new ImageView[50];
        for (int i = 0; i < remains1.length; i++) {
            remains1[i] = new ImageView(remains_Image1);
            gamePane.getChildren().add(remains1[i]);
        }
        remains2 = new ImageView[50];
        for (int i = 0; i < remains2.length; i++) {
            remains2[i] = new ImageView(remains_Image2);
            gamePane.getChildren().add(remains2[i]);
        }
    }

    protected void checkPositionElementCollide() {
        for (int i = 0; i < crews.length; i++) {
            System.out.println(ship.getLayoutY());
            if (ShipP_Radius + Crew_Radius > distanceTwoPoint(ship.getLayoutX(), crews[i].getLayoutX(), ship.getLayoutY(), crews[i].getLayoutY())) {
                points++;
                setNewPositionElement(crews[i]);
                System.out.println(points);
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
}
