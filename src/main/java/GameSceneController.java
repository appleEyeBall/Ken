import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GameSceneController implements EventHandler {
    VBox gameScene;
    HBox firstRow;
    Button continueBtn;
    Button playAgainBtn;
    HBox footerRow;
    Label scoreLabel;
    Label scoreValue;
    Label drawingScoreLabel;
    Label drawingScoreValue;
    HBox spotsBox;
    HBox drawingsRow;
    static public GridPane betCard;
    HBox betCardContainer;  //Bet card
    ChoiceBox numberOfSpots;
    ChoiceBox drawingsValue;
    BetCardController betCardController;


    public GameSceneController(VBox gameScene) {
        this.gameScene = gameScene;
        firstRow = new HBox();
        betCardController = new BetCardController();
        createFirstRow();
        createSpotsRow();
        createDrawingsRow();
        createBetCard();
        createFooterRow();
        this.gameScene.getChildren().add(firstRow);
        this.gameScene.getChildren().add(spotsBox);
        this.gameScene.getChildren().add(drawingsRow);
        this.gameScene.getChildren().add(betCardContainer);
        this.gameScene.getChildren().add(footerRow);
        this.gameScene.setSpacing(Util.spaceBtwRows);


    }

    public void handle(Event event) {
        // numberOfSpotsEvent
        if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "spots"){
            betCardController.setUpVariables(numberOfSpots.getValue().toString(), drawingsValue.getValue().toString() );
            betCard.setDisable(false);

        }
        //drawingsEvent
        else if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "drawings"){
            ChoiceBox box = (ChoiceBox) event.getSource();

        }

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
        numberOfSpots = new ChoiceBox();
        numberOfSpots.setId("spots");

        numberOfSpots.getItems().addAll("1","4","8","10");
        spotsBox.getChildren().add(spotsLabel);
        spotsBox.getChildren().add(numberOfSpots);
        spotsBox.setAlignment(Pos.BASELINE_LEFT);
        spotsBox.setPadding(new Insets(0,Util.sidePadding,0,Util.sidePadding));
        numberOfSpots.setOnAction(this);

    }
    public void createDrawingsRow() {
        /* The row that number of drawings label and all */
        drawingsRow = new HBox();
        Label drawingsLabel = new Label("Number of Drawings:  ");
        drawingsValue = new ChoiceBox();

        drawingsValue.getItems().addAll("1", "2", "3", "4");
        drawingsValue.setValue("1");

        drawingsValue.setId("drawings");
        drawingsRow.setPadding(new Insets(0, 10, 0, Util.sidePadding));
        drawingsRow.getChildren().addAll(drawingsLabel, drawingsValue);
    }

    public void createFooterRow() {
        /* The row that is at the very bottom*/
        footerRow = new HBox();
        Button chooseRandomBtn = new Button("Choose spot randomly");
        chooseRandomBtn.setId("chooseRandomBtn");
        Button nextDrawBtn = new Button("Next Draw");
        nextDrawBtn.setId("nextDrawBtn");

        footerRow.getChildren().addAll(chooseRandomBtn, nextDrawBtn);
        footerRow.setPadding(new Insets(0, Util.sidePadding, 0, Util.sidePadding));
        footerRow.setSpacing(50);
    }

    public void createBetCard(){
        betCardContainer = new HBox();
        betCard = new GridPane();
        for(int i=0;i<10;i++){
            for(int j=0; j<8; j++){
                String buttonName = Integer.toString(i * 8+j+ 1);
                Button button = new Button(buttonName);
                button.setOnAction(betCardController);
                button.setId("gridBtn_"+buttonName);
//                button.setDisable(true);

                betCard.add(button, j,i);

            }
        }
        betCard.setDisable(true);
        betCardContainer.getChildren().add(betCard);
        drawingScoreLabel = new Label("Drawing Score: ");
        drawingScoreValue = new Label("$300");
        betCardContainer.getChildren().addAll(drawingScoreLabel, drawingScoreValue);


    }
}

//todo: enable betCard when no. of spots is selected