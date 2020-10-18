import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class GameSceneController implements EventHandler, BetCardAnimation.OnBetCardAnimationComplete{
    VBox gameScene;
    private HBox firstRow;
    private Button pausePlayBtn;
    private Button playAgainBtn;
    private HBox footerRow;
    Label scoreLabel;
    Label scoreValue;
    Label drawingScoreLabel;
    Label drawingScoreValue;
    HBox spotsBox;
    HBox drawingsRow;
    GridPane betCard;
    private ChoiceBox numberOfSpots;
    private ChoiceBox drawingsValue;
    private BetCardController betCardController;
    private Button chooseRandomBtn;
    private Button nextDrawBtn;
    private int nextDrawBtnPresses = 0;


    public GameSceneController(VBox gameScene) {
        createFirstRow();
        createSpotsRow();
        createDrawingsRow();
        createBetCard();
        createFooterRow();
        this.gameScene = gameScene;
        this.betCardController = new BetCardController(pausePlayBtn, betCard, this);
        this.gameScene.getChildren().add(firstRow);
        this.gameScene.getChildren().add(spotsBox);
        this.gameScene.getChildren().add(drawingsRow);
        this.gameScene.getChildren().add(betCard);
        this.gameScene.getChildren().add(footerRow);
        this.gameScene.setSpacing(Util.spaceBtwRows);
        initializeGameScene();

    }

    public void handle(Event event) {
        // numberOfSpots Event
        if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "spots"){
            if (numberOfSpots.getValue() == null){
                return;
            }
            betCardController.chooseSpots(numberOfSpots.getValue().toString());
            betCard.setDisable(false);
            chooseRandomBtn.setDisable(false);
            nextDrawBtn.setDisable(false);
        }
        //drawingsEvent
        else if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "drawings"){
            ChoiceBox box = (ChoiceBox) event.getSource();

        }
        else if (event.getSource() == playAgainBtn){
            System.out.println("play again clicked");
            betCardController.restart();
            numberOfSpots.getSelectionModel().clearSelection();

            initializeGameScene();   // initialize the game scene
            numberOfSpots.setDisable(false);
            drawingsValue.setValue("1");
            drawingsValue.setDisable(false);
            numberOfSpots.setOnAction(this);
        }
        else if(((Button) event.getSource()).getId() == "chooseRandomBtn"){
            disableFooter();
            numberOfSpots.setDisable(true);
            drawingsValue.setDisable(true);
            betCardController.pickRandomUserSpots();
        }
        else if(event.getSource() == nextDrawBtn){
            //update scores
            if (! betCardController.getBetSize().equals(numberOfSpots.getValue().toString())){   // user hasn't selected number of spots yet
                return;
            }
            disableFooter();
            betCardController.pickRandomComputerSpots();

        }

    }

    public boolean disableFooter(){
        nextDrawBtn.setDisable(true);
        chooseRandomBtn.setDisable(true);
        return (chooseRandomBtn.isDisabled() && nextDrawBtn.isDisabled());
    }

    public HBox createFirstRow(){
        /* Create the first row*/
        firstRow = new HBox();
        HBox controlsBox = new HBox();  // 3.
        // will contain first 2 buttons
        HBox scoreBox = new HBox();     // will contain "Score: $7200"
        pausePlayBtn = new Button("Pause");
        playAgainBtn = new Button("Play again");
        pausePlayBtn.setId("controls");
        playAgainBtn.setOnAction(this);
        scoreLabel = new Label("You Won: $");
        scoreValue = new Label("0");

        scoreValue.setPadding(new Insets(0,Util.labelRightPadding,0,0));

        controlsBox.getChildren().addAll(pausePlayBtn, playAgainBtn);
        scoreBox.getChildren().addAll(scoreLabel, scoreValue);

        controlsBox.setSpacing(Util.width/16);
        firstRow.setSpacing(Util.width/2);

        firstRow.setPadding(new Insets(0,10,0,10));
        firstRow.getChildren().addAll(controlsBox, scoreBox);

        return firstRow;
    }

    public HBox createSpotsRow (){

        Label spotsLabel = new Label("Number of spots: ");
        spotsBox = new HBox();
        numberOfSpots = new ChoiceBox();
        numberOfSpots.setId("spots");

        HBox drawingScoreBox = new HBox();      // will contain the drawing score
        drawingScoreLabel = new Label("Draw Prize: $");
        drawingScoreValue = new Label("0");
        drawingScoreBox.getChildren().addAll(drawingScoreLabel, drawingScoreValue);
        drawingScoreBox.setPadding(new Insets(0,Util.labelRightPadding,0,Util.drawingBoxLeftPadding));

        numberOfSpots.getItems().addAll("1","4","8","10");
        spotsBox.getChildren().addAll(spotsLabel, numberOfSpots, drawingScoreBox);
        spotsBox.setPadding(new Insets(0,0,0,Util.sidePadding));
        numberOfSpots.setOnAction(this);
        return spotsBox;

    }
    public HBox createDrawingsRow() {
        /* The row that number of drawings label and all */
        drawingsRow = new HBox();
        Label drawingsLabel = new Label("Number of Drawings:  ");
        drawingsValue = new ChoiceBox();

        drawingsValue.getItems().addAll("1", "2", "3", "4");
        drawingsValue.setValue("1");

        drawingsValue.setId("drawings");
        drawingsRow.setPadding(new Insets(0, 10, 0, Util.sidePadding));
        drawingsRow.getChildren().addAll(drawingsLabel, drawingsValue);

        return drawingsRow;
    }

    public HBox createFooterRow() {
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

        return footerRow;
    }

    public GridPane createBetCard(){
        betCard = new GridPane();
        for(int i=0;i<10;i++){
            for(int j=0; j<8; j++){
                String buttonName = Integer.toString(i * 8+j+ 1);
                Button button = new Button(buttonName);
                button.setId("gridBtn_"+buttonName);
                button.setBackground(Background.EMPTY);
                betCard.add(button, j,i);
            }
        }
        betCard.setDisable(true);
        betCard.setAlignment(Pos.CENTER);

        return betCard;

    }

    // initialize the game scene everytime game starts (used for playAgain)
    public void initializeGameScene(){
        betCard.setDisable(true);
        disableFooter();
        drawingScoreValue.setText("0");
        scoreValue.setText("0");

    }
    // TODO: remove this old commented out scores calculation
    // calculates the prize $ after each draw and returns as a label string
//    public String calculateDrawScores(){
//        int drawScore = betCardController.intercepts.size()*100;
//        return String.valueOf(drawScore);
//    }
//
//    // calculates the total prize $ inclusive of all previous draw prizes
//    public String calculateTotalScores(Label drawingScoreValue, Label scoreValue){
//
//        int drawScore = Integer.parseInt(drawingScoreValue.getText());
//        int totalScore = Integer.parseInt(scoreValue.getText());
//        totalScore += drawScore;
//
//        return String.valueOf(totalScore);
//    }

    /*** Beginning: These functions are called when the betCard animations are complete ***/
    public void reactivateButtons() {
        chooseRandomBtn.setDisable(false);
        nextDrawBtn.setDisable(false);
    }

    public void updateScores() {
        // update drawings value
        drawingScoreValue.setText(
                ScoresCalculator.calculateDrawScores(numberOfSpots.getValue().toString(),
                        betCardController.getInterceptsSize()));

        // update scores value
        scoreValue.setText(
                ScoresCalculator.calculateTotalScores(numberOfSpots.getValue().toString(),
                        betCardController.getInterceptsSize(),
                        scoreValue.getText() ));

    }

    public void checkDrawsComplete() {
        nextDrawBtnPresses++;
        if (nextDrawBtnPresses == Integer.valueOf(drawingsValue.getValue().toString())){    // last draw
            System.out.println("gonna disable footer");
            // disable buttons. end of round
            disableFooter();
        }
    }
                        /*** End: These functions are betCardAnimation implements ***/


}