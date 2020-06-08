package view;

import javafx.scene.image.ImageView;
import model.SHIP;

public class ExtremeLvlGame extends GameView {
    public ExtremeLvlGame(){}

    protected void creatElements(SHIP shipChoose) {
        whirlwind = new ImageView(Whirlwind_Image);
        whirlwind.setLayoutX(800);
        whirlwind.setLayoutY(10);
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

        remains1 = new ImageView[100];
        for (int i = 0; i < remains1.length; i++) {
            remains1[i] = new ImageView(remains_Image1);
            gamePane.getChildren().add(remains1[i]);
        }
        remains2 = new ImageView[100];
        for (int i = 0; i < remains2.length; i++) {
            remains2[i] = new ImageView(remains_Image2);
            gamePane.getChildren().add(remains2[i]);
        }
    }

    protected void moveElementRemains() {
        for (int i = 0; i < remains1.length; i++) {
            remains1[i].setLayoutY(remains1[i].getLayoutY() + 6);
        }
        for (int i = 0; i < remains2.length; i++) {
            remains2[i].setLayoutY(remains2[i].getLayoutY() + 6);
        }
    }
}
