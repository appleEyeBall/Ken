import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.xml.soap.Node;

public class Controller {
    Stage primaryStage;

    public Controller(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void start(){
        AnchorPane root = new AnchorPane();
        Controller controller = new Controller(primaryStage);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        menuBar.getMenus().add(menu);
        // create menu items
        MenuItem rulesMenuItem = new MenuItem("Display the rules of the game");
        MenuItem oddsMenuItem = new MenuItem("Display the odds of winning");
        MenuItem exitMenuItem = new MenuItem("Exit game");
        // add the menu items to menu
        menu.getItems().addAll(rulesMenuItem, oddsMenuItem, exitMenuItem);




        root.getChildren().add(menuBar);

        primaryStage.setTitle("Keno Game");
        primaryStage.setScene(new Scene(root, 750, 450));
        primaryStage.show();
    }
}
