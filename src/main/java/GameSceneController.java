import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class GameSceneController implements EventHandler {
    AnchorPane gameScene;
    VBox parentRoot;

    public GameSceneController(AnchorPane gameScene) {
        this.gameScene = gameScene;
        parentRoot = new VBox();
        parentRoot.setMinWidth(Util.width);
        parentRoot.setMinHeight(Util.height);
//        gameScene.getChildren().add(parentRoot);
    }

    public void handle(Event event) {

    }
}
