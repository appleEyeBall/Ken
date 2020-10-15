import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Controller implements EventHandler{
    AnchorPane welcomeScene;
    VBox gameScene;
    Stage primaryStage;
    Menu newLookMenu;
    MenuItem rulesMenuItem;
    MenuItem oddsMenuItem;
    MenuItem exitMenuItem;
    Button startGameBtn;


    public Controller(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.setSceneUp();
        this.setEventHandlers();
    }


    private void setSceneUp(){
        welcomeScene = new AnchorPane();
        gameScene = new VBox();
        newLookMenu = new Menu("New Look");


        startGameBtn = new Button();
        startGameBtn.setText("Start Game");
        welcomeScene.getChildren().add(startGameBtn);
        AnchorPane.setLeftAnchor(startGameBtn, 325.0);
        AnchorPane.setTopAnchor(startGameBtn, 225.0);


        welcomeScene.getChildren().add(this.createMenuBar());

        // create menuBar for main game scene, and add to scene
        MenuBar gameMenuBar = this.createMenuBar();
        gameMenuBar.getMenus().add(newLookMenu);
        gameScene.getChildren().add(gameMenuBar);

        primaryStage.setTitle("Keno Game");
        primaryStage.setScene(new Scene(welcomeScene,Util.width, Util.height));
        primaryStage.show();
    }

    public void handle(Event event) {
        if (event.getSource() == rulesMenuItem){

        }
        else if (event.getSource() == oddsMenuItem){

        }
        else if (event.getSource() == startGameBtn){
            primaryStage.setScene(new Scene(gameScene,Util.width,Util.height));
            GameSceneController gameSceneController = new GameSceneController(gameScene);

        }
        else {
            primaryStage.close();
        }


    }

    private void setEventHandlers(){
        exitMenuItem.setOnAction(this);
        startGameBtn.setOnAction(this);
        oddsMenuItem.setOnAction(this);
        rulesMenuItem.setOnAction(this);

    }

    private MenuBar createMenuBar(){

        // create menu items
        rulesMenuItem = new MenuItem("Display the rules of the game");
        oddsMenuItem = new MenuItem("Display the odds of winning");
        exitMenuItem = new MenuItem("Exit game");

        Menu welcomeMenu = new Menu("Menu");
        // add the menu items to menu
        welcomeMenu.getItems().addAll(rulesMenuItem, oddsMenuItem, exitMenuItem);


        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(welcomeMenu);

        return menuBar;

    }



}
