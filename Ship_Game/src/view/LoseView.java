package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class LoseView{

    //Constructor
    public LoseView(){
      initialiZation();
      creatBackground();
      buttonEnd();
      textGame();
    }
    //Fields

    private AnchorPane LosePane;
    private Scene LoseScene;
    private Stage LoseStage;

    //Initialization game
    private void initialiZation(){
        LosePane = new AnchorPane();
        LoseScene = new Scene(LosePane, 700, 700);
        LoseStage = new Stage();
        LoseStage.setScene(LoseScene);
        LoseStage.initStyle(StageStyle.UNDECORATED);
    }

    private void textGame(){
        Text text = new Text("You lose !!");
        text.setFont(new Font("Arial", 50));
        text.setFill(Color.WHITE);
        text.setLayoutX(150);
        text.setLayoutY(300);
        LosePane.getChildren().add(text);
    }


    public void startStage(){
        LoseStage.show();
    }

    private void buttonEnd(){
        Button end = new Button("End");
        LosePane.getChildren().add(end);
        end.setLayoutX(700/2);
        end.setLayoutY(350);

        end.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            LoseStage.close();
        });
    }
    private void creatBackground (){
        Image backgroundImg = new Image("view/resources/endBackground.jpg", 750, 700, false , true );
        BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        LosePane.setBackground(new Background(background));
    }
}
