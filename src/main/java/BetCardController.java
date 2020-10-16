import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashSet;
import java.util.Set;

//TODO: create a set for the buttons event
//TODO: randomly choose 'spots' number of items
public class BetCardController implements EventHandler {
    HashSet<String> bets  = new HashSet<String>();
    private int numberOfSpots;
    private int numberOfDraws;

    public void setUpVariables(String numberOfSpots, String numberOfDraws){
        this.numberOfSpots = Integer.valueOf(numberOfSpots);
        this.numberOfDraws = Integer.valueOf(numberOfDraws);

    }


    public void handle(Event event) {
        String label = ((Button) event.getSource()).getText();
        System.out.println("Label is "+ numberOfSpots);

    }

}