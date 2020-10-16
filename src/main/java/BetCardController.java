import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.HashSet;
import java.util.Set;

//TODO: create a set for the buttons event
//TODO: randomly choose 'spots' number of items
public class BetCardController implements EventHandler {
    HashSet<String> bets = new HashSet<String>();
    public BetCardController() {

    }


    public void handle(Event event) {
        if (bets.size() <= 20){
            String number = ((Button) event.getSource()).getText();
            bets.add(number);
        }
        for( String betSpots : bets ) {
            System.out.print(betSpots+" ");
        }
        System.out.println("\n");




    }












}
