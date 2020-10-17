import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
    GridPane betCard;
    ChoiceBox numberOfSpots;
    ChoiceBox drawingsValue;
    BetCardController betCardController;
    Button chooseRandomBtn;
    Button nextDrawBtn;
    int nextDrawBtnPresses = 0;


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
        this.gameScene.getChildren().add(betCard);
        this.gameScene.getChildren().add(footerRow);
        this.gameScene.setSpacing(Util.spaceBtwRows);



    }

    public void handle(Event event) {
        // numberOfSpotsEvent
        if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "spots"){
            betCardController.setUpVariables(numberOfSpots.getValue().toString(), betCard );
            betCard.setDisable(false);

        }
        //drawingsEvent
        else if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "drawings"){
            ChoiceBox box = (ChoiceBox) event.getSource();

        }
        else if (event.getSource() == playAgainBtn){
            System.out.println("play again clicked");
            betCardController.restart();
        }
        else if(((Button) event.getSource()).getId() == "chooseRandomBtn"){

            betCardController.pickRandomUserSpots();
        }
        else if(event.getSource() == nextDrawBtn){
            nextDrawBtnPresses++;
            System.out.println("max is "+drawingsValue.getValue());
            System.out.println("num of draws is "+nextDrawBtnPresses);
            if (nextDrawBtnPresses == Integer.valueOf(drawingsValue.getValue().toString())){
                // disable buttons. end of round
                nextDrawBtn.setDisable(true);
                chooseRandomBtn.setDisable(true);
                betCard.setDisable(true);
            }
            betCardController.pickRandomComputerSpots();
        }


    }

    public void createFirstRow(){
        /* Create the first row*/
        HBox controlsBox = new HBox();  // will contain first 2 buttons
        HBox scoreBox = new HBox();     // will contain "Score: $7200"
        continueBtn = new Button("Continue");
        playAgainBtn = new Button("Play again");
        playAgainBtn.setOnAction(this);
        scoreLabel = new Label("Score: ");
        scoreValue = new Label("$720");

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

        HBox drawingScoreBox = new HBox();      // will contain the drawing score
        drawingScoreLabel = new Label("Drawing Score: ");
        drawingScoreValue = new Label("$300");
        drawingScoreBox.getChildren().addAll(drawingScoreLabel, drawingScoreValue);
        drawingScoreBox.setPadding(new Insets(0,0,0,Util.drawingBoxLeftPadding));

        numberOfSpots.getItems().addAll("1","4","8","10");
        spotsBox.getChildren().addAll(spotsLabel, numberOfSpots, drawingScoreBox);
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
        chooseRandomBtn = new Button("Choose spot randomly");
        chooseRandomBtn.setId("chooseRandomBtn");
        //this stuff
        chooseRandomBtn.setOnAction(this);
        nextDrawBtn = new Button("Next Draw");
        nextDrawBtn.setId("nextDrawBtn");
        //this stuff
        nextDrawBtn.setOnAction(this);

        footerRow.getChildren().addAll(chooseRandomBtn, nextDrawBtn);
        footerRow.setSpacing(50);
        footerRow.setAlignment(Pos.CENTER);
    }

    public void createBetCard(){
        betCard = new GridPane();
        for(int i=0;i<10;i++){
            for(int j=0; j<8; j++){
                String buttonName = Integer.toString(i * 8+j+ 1);
                Button button = new Button(buttonName);
                button.setOnAction(betCardController);
                button.setId("gridBtn_"+buttonName);
                button.setBackground(Background.EMPTY);
//                button.setDisable(true);

                betCard.add(button, j,i);
            }
        }

        betCard.setDisable(true);
        betCard.setAlignment(Pos.CENTER);

    }
}
