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

public class ReadMe {

    //Constructor
    public ReadMe() {
        initialiZation();
        creatBackground();
        buttonClose();
        textGame();
    }
    //Fields

    private AnchorPane ReadPane;
    private Scene ReadScene;
    private Stage ReadStage;

    //Initialization game
    private void initialiZation() {
        ReadPane = new AnchorPane();
        ReadScene = new Scene(ReadPane, 750, 700);
        ReadStage = new Stage();
        ReadStage.setScene(ReadScene);
        ReadStage.initStyle(StageStyle.UNDECORATED);
    }

    public void startRead(){
        ReadStage.show();
    }

    private void buttonClose(){
        Button close = new Button("Close");
        close.setLayoutX(750/2);
        close.setLayoutY(500);

        close.addEventHandler(MouseEvent.MOUSE_CLICKED, e->{
           ReadStage.close();
        });

        ReadPane.getChildren().add(close);


    }

    private void textGame() {
        Text textReadMe = new Text(" Save teens crews, Use arrows to move your ship." + "\n You have 3 lifes, be careful about other ships !!");
        textReadMe.setLayoutX(40);
        textReadMe.setLayoutY(350);
        textReadMe.setFill(Color.WHITE);
        textReadMe.setFont(new Font("Arial", 30));
        ReadPane.getChildren().add(textReadMe);
    }
    private void creatBackground (){
        Image backgroundImg = new Image("view/resources/endBackground.jpg", 750, 700, false , true );
        BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        ReadPane.setBackground(new Background(background));
    }
}

