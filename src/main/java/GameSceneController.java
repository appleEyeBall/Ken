import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public class GameSceneController implements EventHandler {
    VBox gameScene;
    HBox firstRow;
    Button continueBtn;
    Button playAgainBtn;
    Label scoreLabel;
    Label scoreValue;

    public GameSceneController(VBox gameScene) {
        this.gameScene = gameScene;
        firstRow = new HBox();
        createFirstRow();
        this.gameScene.getChildren().add(firstRow);

    }

    public void handle(Event event) {

    }

    public void createFirstRow(){
        continueBtn = new Button("Continue");
        playAgainBtn = new Button("Play again");
        scoreLabel = new Label("Score");
        scoreValue = new Label("$7200");

        Node[] nodes = {continueBtn, playAgainBtn, scoreLabel, scoreValue};
        firstRow.getChildren().addAll(nodes);

    }

}