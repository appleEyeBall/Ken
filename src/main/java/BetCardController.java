import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class BetCardController implements EventHandler {
    HashSet<Integer> bets  = new HashSet<Integer>();
    HashSet<Integer> drawSelections  = new HashSet<Integer>();
    HashSet<Integer> intercepts  = new HashSet<Integer>();

    Button pausePlayBtn;
    private int numberOfSpots;
    GridPane betCard;
    BetCardAnimation betCardAnimation;

    public BetCardController(Button pausePlayBtn, GridPane betCard) {
        this.pausePlayBtn = pausePlayBtn;
        this.betCard = betCard;
        makeThisClassTheEventHandler();     // ensure that this event handlers are set
        betCardAnimation = new BetCardAnimation(this.betCard);
    }

    private void makeThisClassTheEventHandler(){
        this.pausePlayBtn.setOnAction(this);
        for (Node child: betCard.getChildren()){
            ((Button) child).setOnAction(this);
        }
    }

    public void chooseSpots(String numberOfSpots){
        bets.clear();
        this.numberOfSpots = Integer.valueOf(numberOfSpots);
        removeAllBtnColors();
    }

    public void restart(){
        // TODO: GARIMA Play-again button should call this function. Make sure it works as expected (disable required buttons too)
        bets.clear();
        drawSelections.clear();
        intercepts.clear();
    }

    public void handle(Event event) {
        // any button on the grid is clicked
        System.out.println("stuff");
        if(((Button) event.getSource()).getId().contains("gridBtn")){
            pickSpotsManually(((Button) event.getSource()));
        }

        // continue/pause button is clicked
        if (event.getSource() == pausePlayBtn){
            System.out.println("pressed");
            // play/pause, then change text on button as required
            if (pausePlayBtn.getText() == Util.controlsBtnPause) {
                betCardAnimation.pauseSpotPlaying();
                pausePlayBtn.setText(Util.controlsBtnPlay);
            }
            else {
                betCardAnimation.resumeSpotPlaying();
                pausePlayBtn.setText(Util.controlsBtnPause);
            }
        }

    }

    public void pickSpotsManually(Button btn){
        String value = btn.getText();
        if (bets.size() < numberOfSpots){
            int number = Integer.valueOf(value);
            bets.add(number);
            System.out.println("num:" + numberOfSpots);
            btn.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        }
        System.out.println("from pickSpotsManually func, bets are: "+bets);

    }

    public void pickRandomUserSpots(){
        bets.clear();
        removeAllBtnColors();
        while(bets.size() < numberOfSpots){
            int number = new Random().nextInt(80)+1;
            bets.add(number);
        }
        System.out.println(bets);
        betCardAnimation.playSpots(new ArrayList<Integer>(bets), Util.user);
    }

    public void pickRandomComputerSpots(){
        drawSelections.clear();
        intercepts.clear();
        removeComputerColors();
        while(drawSelections.size() < 20){
            int number = new Random().nextInt(80)+1;
            drawSelections.add(number);
            if (isIntercept(number)) {
                intercepts.add(number);
            }
        }
        System.out.println(drawSelections);
        System.out.println(bets);
        System.out.println(intercepts);
        betCardAnimation.playSpots(new ArrayList<Integer>(drawSelections), Util.computer);

    }

    private boolean isIntercept(int pos) {
        /* this function checks if user has also picked pos */
        if (bets.contains(pos)){
            return true;
        }
        return false;
    }


    public void removeAllBtnColors(){
        for (Node child: betCard.getChildren()){
            child = (Button) child;
            ((Button) child).setBackground(Background.EMPTY);
        }
    }

    public void removeComputerColors(){
        // remove all colors, then add back the user-spots colors
        removeAllBtnColors();
        for (Node child: betCard.getChildren()){
            child = (Button) child;
            ((Button) child).setBackground(Background.EMPTY);
            if (bets.contains(Integer.valueOf(((Button) child).getText()))){
                ((Button) child).setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
            }
        }
    }

}