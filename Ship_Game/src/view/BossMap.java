package view;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import model.SHIP;

import javax.swing.text.html.ImageView;

public class BossMap {

    //Constructor
    public BossMap() {
        initializationGame();
    }

    //Fields
    private AnchorPane BossPane;
    private Scene BossScene;
    private Stage BossStage;
    private ImageView background;
    private final String backgroundImg ="view/resources/background2.jpg";



    public void initializationGame() {
        BossPane = new AnchorPane();
        BossScene = new Scene(BossPane, 800, 800);
        BossStage = new Stage();
        BossStage.setScene(BossScene);
        BossStage.setFullScreen(true);

    }

    //Create Game
    public void creatNewGame(){
        creatBackground();
        BossStage.show();
    }

    private void creatBackground(){
        Image backgroundImg = new Image("view/resources/background2.jpg", 750, 700, false , true );
        BackgroundImage background = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
        BossPane.setBackground(new Background(background));
    }


}
