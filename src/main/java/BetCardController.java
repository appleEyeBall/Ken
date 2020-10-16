import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

//TODO: create a set for the buttons event
//TODO: randomly choose 'spots' number of items
public class BetCardController implements EventHandler {
    HashSet<Integer> bets = new HashSet<Integer>();
    private int numberOfSpots;
    private int numberOfDraws;

    public void setUpVariables(String numberOfSpots, String numberOfDraws){
        bets.clear();
        this.numberOfSpots = Integer.valueOf(numberOfSpots);
        this.numberOfDraws = Integer.valueOf(numberOfDraws);

    }
    public BetCardController() {

    }


    public void handle(Event event) {

        if(((Button) event.getSource()).getId().contains("gridBtn")){
            pickSpotsManually(((Button) event.getSource()).getText());
        }
        if(((Button) event.getSource()).getId() == "chooseRandomBtn"){

            pickRandomSpots(event);
        }


    }

    public void pickSpotsManually(String value){
        if (bets.size() < numberOfSpots){
            int number = Integer.valueOf(value);
            bets.add(number);
            System.out.println("num:" + numberOfSpots);
        }
        System.out.println(bets);

    }
    public void pickRandomSpots(Event event){
        bets.clear();
        while(bets.size() < numberOfSpots){

            int number = new Random().nextInt(80);
            bets.add(number);
        }
        System.out.println(bets);
    }












}
