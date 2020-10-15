import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GameSceneController implements EventHandler {
    VBox gameScene;
    HBox firstRow;
    Button continueBtn;
    Button playAgainBtn;
    Label scoreLabel;
    Label scoreValue;
    HBox spotsBox;

    public GameSceneController(VBox gameScene) {
        this.gameScene = gameScene;
        firstRow = new HBox();
        createFirstRow();
        createSpotsRow();
        this.gameScene.getChildren().add(firstRow);
        this.gameScene.getChildren().add(spotsBox);

    }

    public void handle(Event event) {

    }

    public void createFirstRow(){
        /* Create the first row*/
        HBox controlsBox = new HBox();  // will contain first 2 buttons
        HBox scoreBox = new HBox();     // will contain "Score: $7200"
        continueBtn = new Button("Continue");
        playAgainBtn = new Button("Play again");
        scoreLabel = new Label("Score: ");
        scoreValue = new Label("$7200");

        controlsBox.getChildren().addAll(continueBtn, playAgainBtn);
        scoreBox.getChildren().addAll(scoreLabel, scoreValue);

        controlsBox.setSpacing(Util.width/16);
        firstRow.setSpacing(Util.width/2);

        firstRow.setPadding(new Insets(0,10,0,10));
        firstRow.getChildren().addAll(controlsBox, scoreBox);
    }

    public void createSpotsRow (){

        Label spotsLabel = new Label("Number of spots: ");
        spotsBox = new HBox();
        ChoiceBox numberOfSpots = new ChoiceBox();
        numberOfSpots.getItems().addAll("1","4","8","10");
        spotsBox.getChildren().add(spotsLabel);
        spotsBox.getChildren().add(numberOfSpots);
        spotsBox.setAlignment(Pos.BASELINE_LEFT);
        spotsBox.setPadding(new Insets(0,Util.sidePadding,0,Util.sidePadding));
    }


}