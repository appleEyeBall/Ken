import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GameSceneController implements EventHandler {
    VBox gameScene;
    HBox headerRow;
    HBox footerRow;
    HBox spotsBox;
    HBox drawingsRow;
    HBox betCardContainer;
    GridPane betCard;
    Button continueBtn;
    Button playAgainBtn;
    Label scoreLabel;
    Label scoreValue;
    ChoiceBox drawingsValueChoices;

    public GameSceneController(VBox gameScene) {
        this.gameScene = gameScene;
        headerRow = new HBox();
        createHeaderRow();
        createDrawingsRow();
        createFooterRow();
        createSpotsRow();
        createBetCard();
        this.gameScene.getChildren().add(headerRow);
        this.gameScene.getChildren().add(spotsBox);
        this.gameScene.getChildren().add(drawingsRow);
        this.gameScene.getChildren().add(betCardContainer);
        this.gameScene.getChildren().add(footerRow);
        this.gameScene.setSpacing(Util.spaceBtwRows);


    }

    public void handle(Event event) {

    }

    public void createHeaderRow(){
        /* Create the first row. The row that
        contains continue buttons and score*/
        HBox controlsBox = new HBox();  // will contain first 2 buttons
        HBox scoreBox = new HBox();     // will contain "Score: $7200"
        continueBtn = new Button("Continue");
        playAgainBtn = new Button("Play again");
        scoreLabel = new Label("Score: ");
        scoreValue = new Label("$7200");

        controlsBox.getChildren().addAll(continueBtn, playAgainBtn);
        scoreBox.getChildren().addAll(scoreLabel, scoreValue);

        controlsBox.setSpacing(Util.width/16);
        headerRow.setSpacing(Util.width/2);

        headerRow.setPadding(new Insets(0,10,0,10));
        headerRow.getChildren().addAll(controlsBox, scoreBox);
    }

    public void createDrawingsRow(){
        /* The row that number of drawings label and all */
        drawingsRow = new HBox();
        Label drawingsLabel = new Label("Number of Drawings:  ");
        drawingsValueChoices = new ChoiceBox();

        drawingsValueChoices.getItems().addAll("1", "2", "3", "4");
        drawingsRow.setPadding(new Insets(0,10,0,Util.sidePadding));
        drawingsRow.getChildren().addAll(drawingsLabel, drawingsValueChoices);
    }

    public void createFooterRow(){
        /* The row that is at the very bottom*/
        footerRow = new HBox();
        Button chooseRandomBtn = new Button("Choose spot randomly");
        chooseRandomBtn.setId("chooseRandomBtn");
        Button nextDrawBtn = new Button("Next Draw");
        nextDrawBtn.setId("nextDrawBtn");

        footerRow.getChildren().addAll(chooseRandomBtn, nextDrawBtn);
        footerRow.setPadding(new Insets(0, Util.sidePadding,0, Util.sidePadding));
        footerRow.setSpacing(50);
    }

    public void createSpotsRow (){
        /* Create the row where you pick spots*/
        Label spotsLabel = new Label("Number of spots: ");
        spotsBox = new HBox();
        ChoiceBox numberOfSpots = new ChoiceBox();
        numberOfSpots.getItems().addAll("1","4","8","10");
        spotsBox.getChildren().add(spotsLabel);
        spotsBox.getChildren().add(numberOfSpots);
        spotsBox.setAlignment(Pos.BASELINE_LEFT);
        spotsBox.setPadding(new Insets(0,Util.sidePadding,0,Util.sidePadding));
    }

    public void createBetCard(){
        betCardContainer = new HBox();
        betCard = new GridPane();
        for(int i=1;i<9;i++){
            for(int j=1; j<11; j++){
                String buttonName = Integer.toString(i*j);
                betCard.add(new Button(buttonName), i,j);

            }
        }
        betCardContainer.getChildren().add(betCard);
    }
}
