import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller implements EventHandler{
    AnchorPane welcomeScene;
    VBox gameScene;
    Stage primaryStage;
    Button startGameBtn;
    Menu gameSceneMenu;
    MenuBar gameSceneMenuBar;
    GameSceneController gameSceneController;

    public Controller(Stage primaryStage){
        this.primaryStage = primaryStage;

        this.setSceneUp();
        startGameBtn.setOnAction(this);
        gameSceneController = new GameSceneController(gameScene);
        //TODO: delete these 2 lines of code. They make it so
        // I don't have to click to go tho game screen
//        primaryStage.setScene(new Scene(gameScene,Util.width,Util.height));

    }


    private void setSceneUp(){
        welcomeScene = new AnchorPane();
        gameScene = new VBox();
        //newLookMenu = new MenuItem( "New Look");
        MenuItem newLookMenuItem = new MenuItem("New Look");
        newLookMenuItem.setId("newLookMenu");
        newLookMenuItem.setOnAction(this);

        startGameBtn = new Button();
        startGameBtn.setText("Start Game");
        welcomeScene.getChildren().add(startGameBtn);
        AnchorPane.setLeftAnchor(startGameBtn, 325.0);
        AnchorPane.setTopAnchor(startGameBtn, 225.0);

        welcomeScene.getChildren().add(this.createMenuBar());

        // create menuBar for main game scene, and add to scene
        Menu gameSceneMenu = this.createMenuBar().getMenus().get(0);
        gameSceneMenu.getItems().add(newLookMenuItem);
        gameSceneMenuBar = new MenuBar(gameSceneMenu);
        gameScene.getChildren().add(gameSceneMenuBar);

        primaryStage.setTitle("Keno Game");
        primaryStage.setScene(new Scene(welcomeScene, Util.width, Util.height));
        primaryStage.show();
    }

    //
    public void handle(Event event) {

        if (event.getSource() == startGameBtn){
            primaryStage.setScene(new Scene(gameScene,Util.width,Util.height));

        }

        else if (((MenuItem)event.getSource()).getId() == "rulesBtn"){
            displayGameInfo("Rules for the game:",Util.gameRules );
        }
        else if (((MenuItem)event.getSource()).getId() == "oddsBtn"){
            displayGameInfo("Odds of Winning the Game:",Util.oddsOfWinning);
        }
        else if (((MenuItem)event.getSource()).getId() == "newLookMenu"){
            System.out.println("new looks");
            setNewLooks();
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
        //MenuItem newLookMenuItem = new MenuItem("New Look");

        rulesMenuItem.setId("rulesBtn");
        oddsMenuItem.setId("oddsBtn");
        exitMenuItem.setId("exitBtn");
       // newLookMenuItem.setId("newLookMenu");

        rulesMenuItem.setOnAction(this);
        oddsMenuItem.setOnAction(this);
        exitMenuItem.setOnAction(this);

        Menu welcomeMenu = new Menu("Menu");
        // add the menu items to menu
        welcomeMenu.getItems().addAll(rulesMenuItem, oddsMenuItem, exitMenuItem);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().add(welcomeMenu);
//        Menu gameMenuBar = new Menu("Menu");
        //gameMenuBar.getItems().addAll(rulesMenuItem, oddsMenuItem, exitMenuItem);
        return menuBar;
        // commit

    }

    //Display the Rules and Odds of winning using an alert
    public void displayGameInfo(String message, String menuInfo){

        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(message);
        alert.setContentText(menuInfo);
        alert.show();
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

    }

    public void setNewLooks(){
        gameSceneController.firstRow.setBackground(new Background(new BackgroundFill (Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        gameSceneController.spotsBox.setBackground(new Background(new BackgroundFill (Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));
        gameSceneController.drawingsRow.setBackground(new Background(new BackgroundFill (Color.CORAL, CornerRadii.EMPTY, Insets.EMPTY)));

    }


}
