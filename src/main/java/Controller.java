import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Controller implements EventHandler{
    AnchorPane welcomeScene;
    AnchorPane gameScene;
    Stage primaryStage;
    Menu newLookMenu;
//    MenuItem rulesMenuItem;
//    MenuItem oddsMenuItem;
//    MenuItem exitMenuItem;
    Button startGameBtn;

    int width = 750;
    int height = 450;

    public Controller(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.setSceneUp();
        startGameBtn.setOnAction(this);
    }


    private void setSceneUp(){
        welcomeScene = new AnchorPane();
        gameScene = new AnchorPane();
        newLookMenu = new Menu("New Look");
        newLookMenu.setId("newLookMenu");


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
        primaryStage.setScene(new Scene(welcomeScene, width, height));
        primaryStage.show();
    }

    public void handle(Event event) {
        //System.out.println(((Node) event.getSource()).getId());
        if (event.getSource() == startGameBtn){
            primaryStage.setScene(new Scene(gameScene,width,height));

        }
        else if (((MenuItem)event.getSource()).getId() == "rulesBtn"){
            displayGameInfo("Rules for the game:",Util.gameRules );
        }
        else if (((MenuItem)event.getSource()).getId() == "oddsBtn"){
            displayGameInfo("Odds of Winning the Game:",Util.oddsOfWinning);
        }

        else {
            primaryStage.close();
        }

    }

    private MenuBar createMenuBar(){
        // create menu items
        MenuItem rulesMenuItem = new MenuItem("Display the rules of the game");
        MenuItem oddsMenuItem = new MenuItem("Display the odds of winning");
        MenuItem exitMenuItem = new MenuItem("Exit game");

        rulesMenuItem.setId("rulesBtn");
        oddsMenuItem.setId("oddsBtn");
        exitMenuItem.setId("exitBtn");


        rulesMenuItem.setOnAction(this);
        oddsMenuItem.setOnAction(this);
        exitMenuItem.setOnAction(this);

        Menu welcomeMenu = new Menu("Menu");
        // add the menu items to menu
        welcomeMenu.getItems().addAll(rulesMenuItem, oddsMenuItem, exitMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(welcomeMenu);

        return menuBar;
        // commit

    }

    //Display the Rules and Odds of winning using this function
    public void displayGameInfo(String message, String menuInfo){

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(message);
        alert.setContentText(menuInfo);
        alert.show();
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

    }


}
