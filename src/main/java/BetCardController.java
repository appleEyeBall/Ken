import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Random;

public class BetCardController implements EventHandler {
    HashSet<Integer> bets  = new HashSet<Integer>();
    HashSet<Integer> drawSelections  = new HashSet<Integer>();
    HashSet<Integer> intercepts  = new HashSet<Integer>();

    private int numberOfSpots;
    GridPane betCard;

    public void setUpVariables(String numberOfSpots, GridPane betCard){
        bets.clear();
        this.numberOfSpots = Integer.valueOf(numberOfSpots);
        this.betCard = betCard;
        removeAllBtnColors();

    }

    public void restart(){
        // TODO: add other things to do after restart. (Play again should call this function)
        bets.clear();
        drawSelections.clear();
        intercepts.clear();
    }

    public void handle(Event event) {
        // any button on the grid is clicked
        if(((Button) event.getSource()).getId().contains("gridBtn")){
            pickSpotsManually(((Button) event.getSource()).getText());
        }

    }

    public void pickSpotsManually(String value){
        if (bets.size() < numberOfSpots){
            int number = Integer.valueOf(value);
            bets.add(number);
            System.out.println("num:" + numberOfSpots);
            setColors(Util.user, number);
        }
        System.out.println(bets);


    }

    public void pickRandomUserSpots(){
        bets.clear();
        removeAllBtnColors();
        while(bets.size() < numberOfSpots){
            int number = new Random().nextInt(80)+1;
            bets.add(number);
            setColors(Util.user, number);
        }
        System.out.println(bets);
    }

    public void pickRandomComputerSpots(){
        drawSelections.clear();
        intercepts.clear();
        removeComputerColors();
        while(drawSelections.size() < 20){
            int number = new Random().nextInt(80)+1;
            drawSelections.add(number);
            setColors(Util.computer, number);
            if (isIntercept(number)) {
                setColors(Util.intercept, number);
                intercepts.add(number);
            }
        }
        System.out.println(drawSelections);
        System.out.println(bets);
        System.out.println(intercepts);
    }

    private boolean isIntercept(int pos) {
        /* this function checks if player has also picked pos */
        if (bets.contains(pos)){
            return true;
        }
        return false;
    }

    public void setColors(String type, int number){
        /* type is either Util.user, Util.computer or Util.intercept */
        System.out.println("number is "+number);
        if (betCard == null) return;

        Button button = ((Button) betCard.getChildren().get(number-1));
        if (type.equals(Util.user)) {
            button.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        }
        else if (type.equals(Util.computer)) {
            button.setBackground(new Background(new BackgroundFill(Color.INDIANRED, null, null)));
        }
        else if (type.equals(Util.intercept)){
            button.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
        }

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
        for (int val: bets){
            setColors(Util.user, val);
        }
    }

}