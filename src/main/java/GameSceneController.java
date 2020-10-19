import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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
    private int countLookChanges =0; // for back and forth Look changes


    public GameSceneController(VBox gameScene) {
        createFirstRow();
        createSpotsRow();
        createDrawingsRow();
        createBetCard();
        createFooterRow();
        this.gameScene = gameScene;
        this.betCardController = new BetCardController(betCard, this);
        this.gameScene.getChildren().addAll(firstRow, spotsBox, drawingsRow, betCard, footerRow);
        this.gameScene.setSpacing(Util.SPACE_BTW_ROWS);
        initializeGameScene();

    }

    @Override
    public void handle(Event event) {
        //event handling for "new Looks" menu item
        if ( event.getSource() instanceof MenuItem && ((MenuItem)event.getSource()).getId() == "newLookMenu"){
            //even-number-> default look, odd-number-> new-look
            countLookChanges++;
            if(countLookChanges%2 ==0){
                setNewLooks(false);
            }
            else{
                setNewLooks(true);
            }
        }

        // event handling for numberOfSpots choiceBox
        else if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "spots"){
            if (numberOfSpots.getValue() == null){
                return;
            }
            betCardController.chooseSpots(numberOfSpots.getValue().toString());
            betCard.setDisable(false);
            chooseRandomBtn.setDisable(false);
            nextDrawBtn.setDisable(false);
        }
        //event handling for numberOfDrawings choiceBox
        else if (event.getSource() instanceof ChoiceBox && ((ChoiceBox) event.getSource()).getId() == "drawings"){
            ChoiceBox box = (ChoiceBox) event.getSource();

        }
        else if (event.getSource() == playAgainBtn){
            System.out.println("play again clicked");

            initializeGameScene();   // initialize the game scene
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

        // continue/pause button is clicked
        else if (event.getSource() == pausePlayBtn){
            System.out.println("pressed");
            // play/pause, then change text on button as required
            if (pausePlayBtn.getText() == Util.CONTROLS_BTN_PAUSE) {
                betCardController.getBetCardAnimation().pauseSpotPlaying();
                pausePlayBtn.setText(Util.CONTROLS_BTN_PLAY);
            }
            else {
                betCardController.getBetCardAnimation().resumeSpotPlaying();
                pausePlayBtn.setText(Util.CONTROLS_BTN_PAUSE);
            }
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
        pausePlayBtn.setOnAction(this);
        playAgainBtn.setOnAction(this);
        scoreLabel = new Label("You Won: $");
        scoreValue = new Label("0");

        scoreValue.setPadding(new Insets(0,Util.LABEL_RIGHT_PADDING,0,0));

        controlsBox.getChildren().addAll(pausePlayBtn, playAgainBtn);
        scoreBox.getChildren().addAll(scoreLabel, scoreValue);

        controlsBox.setSpacing(Util.WIDTH /16);
        firstRow.setSpacing(Util.WIDTH /2);

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
        drawingScoreBox.setPadding(new Insets(0,Util.LABEL_RIGHT_PADDING,0,Util.DRAWING_BOX_LEFT_PADDING));

        numberOfSpots.getItems().addAll("1","4","8","10");
        spotsBox.getChildren().addAll(spotsLabel, numberOfSpots, drawingScoreBox);
        spotsBox.setPadding(new Insets(0,0,0,Util.SIDE_PADDING));
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
        drawingsRow.setPadding(new Insets(0, 10, 0, Util.SIDE_PADDING));
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


    public void initializeGameScene(){      // make the game look like the first time
        betCardController.restart();
        numberOfSpots.getSelectionModel().clearSelection();
        drawingsValue.getSelectionModel().clearSelection();

        drawingScoreValue.setText("0");
        scoreValue.setText("0");
        nextDrawBtnPresses = 0;
        setNewLooks(false);

        drawingsValue.setValue("1");
        drawingsValue.setDisable(false);
        numberOfSpots.setDisable(false);
        betCard.setDisable(true);
        disableFooter();

        numberOfSpots.setOnAction(this);
    }


    public void setNewLooks(Boolean newLooksStatus){        // change the looks of the game scene

        // made the score labels' font bold
        this.scoreValue.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        this.scoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        this.drawingScoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        this.drawingScoreValue.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        if(newLooksStatus) { // if the button is pressed to set a new look
            this.spotsBox.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            this.drawingsRow.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            this.gameScene.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            this.betCard.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            this.scoreLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            this.scoreValue.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            this.drawingScoreLabel.setBackground( Background.EMPTY);
            this.drawingScoreValue.setBackground(Background.EMPTY);


        }
        else{   // if the button is pressed to switch back to previous look
            this.gameScene.setBackground(new Background(new BackgroundFill (Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
            this.betCard.setBackground(Background.EMPTY);
            this.spotsBox.setBackground(Background.EMPTY);
            this.drawingsRow.setBackground(Background.EMPTY);
            this.scoreLabel.setBackground(Background.EMPTY);
            this.scoreLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            this.scoreValue.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            this.drawingScoreLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            this.drawingScoreValue.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

    @Override
    public void reactivateButtons() {
        chooseRandomBtn.setDisable(false);
        nextDrawBtn.setDisable(false);
    }

    @Override
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
    @Override
    public void checkDrawsComplete() {
        nextDrawBtnPresses++;
        if (nextDrawBtnPresses == Integer.valueOf(drawingsValue.getValue().toString())){    // last draw
            System.out.println("gonna disable footer");
            // disable buttons. end of round
            disableFooter();
        }
    }

}