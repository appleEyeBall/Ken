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

    private int numberOfSpots;
    GridPane betCard;
    private BetCardAnimation betCardAnimation;

    public BetCardController(GridPane betCard, Object gameScene) {
        this.betCard = betCard;
        setThisAsEventHandler();     // ensure that this event handlers are set
        betCardAnimation = new BetCardAnimation(this.betCard, gameScene);
    }

    private void setThisAsEventHandler(){
//        this.pausePlayBtn.setOnAction(this);
        for (Node child: betCard.getChildren()){
            ((Button) child).setOnAction(this);
        }
    }

    public String getBetSize(){
        if (bets == null || bets.size() == 0){
            return "0";
        }
        return String.valueOf(bets.size());
    }
    public String getInterceptsSize(){
        if (intercepts == null || intercepts.size() == 0){
            return "0";
        }
        return String.valueOf(intercepts.size());
    }
    public BetCardAnimation getBetCardAnimation(){
        return betCardAnimation;
    }

    public void chooseSpots(String numberOfSpots){
        bets.clear();
        this.numberOfSpots = Integer.valueOf(numberOfSpots);
        removeAllBtnColors();
    }

    public void restart(){
        // restart the betCardController
        bets.clear();
        drawSelections.clear();
        intercepts.clear();
        removeAllBtnColors();

    }

    @Override
    public void handle(Event event) {
        // any button on the grid is clicked
        System.out.println("stuff");
        if(((Button) event.getSource()).getId().contains("gridBtn")){
            pickSpotsManually(((Button) event.getSource()));
        }
    }

    public void pickSpotsManually(Button btn){
        String value = btn.getText();
        int number = Integer.valueOf(value);
        // deselect the spot if the number is in bets
        // else, add to bets
        if(bets.contains(number)){
            bets.remove(number);
            btn.setBackground(Background.EMPTY);
            System.out.println("removed "+btn.getText());
        }
        else if (bets.size() < numberOfSpots){
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
        betCardAnimation.playSpots(new ArrayList<Integer>(bets), Util.TYPE_USER);
    }

    public void pickRandomComputerSpots(){
        drawSelections.clear();
        intercepts.clear();
        removeComputerColors();
        while(drawSelections.size() < 20){
            int number = new Random().nextInt(80)+1;
            drawSelections.add(number);
            if (isIntercept(bets, number)) {
                intercepts.add(number);
            }
        }
        System.out.println(drawSelections);
        System.out.println(bets);
        System.out.println(intercepts);
        betCardAnimation.playSpots(new ArrayList<Integer>(drawSelections), Util.TYPE_COMPUTER);

    }

    private boolean isIntercept(HashSet bets, int pos) {
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