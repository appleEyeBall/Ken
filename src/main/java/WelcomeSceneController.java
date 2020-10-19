import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

// This class sets up the welcome scene of the game that consists of a menu and start game button

public class WelcomeSceneController implements EventHandler{
    Pane welcomeScene;
    Button startGameBtn;
    CallMain callMain;

    public WelcomeSceneController(Node welcomeScene, Object main){
        this.welcomeScene = (Pane) welcomeScene;
        this.setSceneUp();
        callMain = (CallMain) main;
    }

    /** Interface**/
    public interface CallMain {         // the interface that communicates back to Main
        public void loadGameScene();
    }

    private void setSceneUp(){
        startGameBtn = new Button();
        startGameBtn.setText("Start Game");
        startGameBtn.setId("start");
        startGameBtn.setOnAction(this);

        // add start-button to scene
        welcomeScene.getChildren().add(startGameBtn);
        AnchorPane.setLeftAnchor(startGameBtn, 325.0);
        AnchorPane.setTopAnchor(startGameBtn, 225.0);
    }

    @Override
    public void handle(Event event) {
        if (event.getSource() == startGameBtn){   // create the game scene when start game button is pressed
            this.callMain.loadGameScene();
        }

    }

}