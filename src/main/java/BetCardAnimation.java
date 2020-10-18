import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class BetCardAnimation implements EventHandler<ActionEvent>{
    /* This class is in charge of the animations on the bet card
     * Instead of sets, this class uses Arraylist positions
     * (Note: convert set like bets and intercepts to arraylist and pass to this class)
     *                *** Explanation (for the curious) ***
     * The class uses Arraylists because Arraylists can be treated like stacks.
     * so each animation goes thus:
     *       - Get one number from arraylist
     *       - change the color of the grid position at that number
     *       - pop the item
     *       - repeat
     *
     * Object parameter is the class that will be implementing our OnBetCardAnimationComplete interface
     * so that it'll be notified when animation completes
     * */

    GridPane betCard;
    Timeline timeline;
    ArrayList<Integer> positions;
    String type;
    OnBetCardAnimationComplete animationComplete;

    public BetCardAnimation(GridPane betCard, Object gameScene) {
        this.betCard = betCard;
        this.timeline = new Timeline(
                new KeyFrame(Duration.seconds(0.2), this)
        );
        // handle interface that notifies everyone that the animation is done
        animationComplete = (OnBetCardAnimationComplete) gameScene;
    }

    public void handle(ActionEvent event) {
        if (positions == null || positions.isEmpty()){
            if (type == Util.user){
                animationComplete.reactivateButtons();
                animationComplete.updateScores();
            }
            else if (type == Util.computer){
                // TODO: GARIMA: Animation Complete. we should reactivate footer buttons here because footer buttons should be disabled during animated gamePlay

                // notify everyone class that the animation has ended
                // (hopefully the classes implement the interface)
                animationComplete.reactivateButtons();
                animationComplete.updateScores();
                animationComplete.checkDrawsComplete();

            }
            return;
        }
        System.out.println("event triggered "+BetCardAnimation.this.positions.get(0));
        // set color at that pos, then remove the pos from arrayList
        setColors(type, positions.get(0));
        BetCardAnimation.this.positions.remove(0);
    }

    /* This interface needs to be implemented by a class that needs to do something after the animation is completed */
    public interface OnBetCardAnimationComplete{
        public void reactivateButtons();
        public void updateScores();
        public void checkDrawsComplete();

    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void playSpots(ArrayList<Integer> positions, String type){
        /* entry point for the game play animation */
        this.positions = new ArrayList<Integer>(positions);
        this.type = type;

        timeline.setCycleCount(this.positions.size()+1);
        timeline.play();
    }

    public Animation.Status pauseSpotPlaying(){
        this.timeline.pause();
        return timeline.getStatus();
    }

    public Animation.Status resumeSpotPlaying(){
        this.timeline.play();
        return timeline.getStatus();
    }

    public void setColors(String type, int number){
        /* type is either Util.user, Util.computer */
        System.out.println("number is "+number);
        if (betCard == null) return;

        Button button = ((Button) betCard.getChildren().get(number-1));
        if (isIntercept(button)){
            button.setBackground(new Background(new BackgroundFill(Color.DARKSEAGREEN, null, null)));
        }
        else if (type.equals(Util.user)) {
            button.setBackground(new Background(new BackgroundFill(Color.CORNFLOWERBLUE, null, null)));
        }
        else if (type.equals(Util.computer)) {
            button.setBackground(new Background(new BackgroundFill(Color.INDIANRED, null, null)));
        }

    }

    boolean isIntercept(Button button){
        // we check for intercept by checking if button's color is already blue
        if (!button.getBackground().getFills().isEmpty()){
            BackgroundFill fill = button.getBackground().getFills().get(0);
            if (fill.getFill() == Color.CORNFLOWERBLUE) return true;
        }
        return false;

    }

}