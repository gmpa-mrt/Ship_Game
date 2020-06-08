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


public class WinView{

    //Constructor
    public WinView(){
        initialiZation();
        creatBackground();
        buttonEnd();
        textGame();
    }
    //Fields

    private AnchorPane WinPane;
    private Scene WinScene;
    private Stage WinStage;

    //Initialization game
    private void initialiZation(){
        WinPane = new AnchorPane();
        WinScene = new Scene(WinPane, 700, 700);
        WinStage = new Stage();
        WinStage.setScene(WinScene);
        WinStage.initStyle(StageStyle.UNDECORATED);
    }

    private void textGame(){
        Text text = new Text("You Win !! Congratulations !!");
        text.setFont(new Font("Arial", 50));
        text.setFill(Color.WHITE);
        text.setLayoutX(30);
        text.setLayoutY(300);
        WinPane.getChildren().add(text);
    }


    public void startStage(){
        WinStage.show();
    }

    private void buttonEnd(){
        Button end = new Button("End");
        WinPane.getChildren().add(end);
        end.setLayoutX(700/2);
        end.setLayoutY(350);

        end.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
            WinStage.close();
        });
    }
    private void creatBackground (){
        Image backgroundImg = new Image("view/resources/endBackground.jpg", 750, 700, false , true );
        BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        WinPane.setBackground(new Background(background));
    }
}
