
import javafx.application.Application;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application implements EventHandler, WelcomeSceneController.CallMain {
    Stage primaryStage;
    MenuBar menuBar;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Keno Game");

        // create welcome scene and add menuBar to it
        Pane welcomeScene = new AnchorPane();
        menuBar = createMenuBar();
        welcomeScene.getChildren().add(menuBar);

        // load welcome scene and set its controller
        WelcomeSceneController welcomeSceneController = new WelcomeSceneController(welcomeScene, this);
        primaryStage.setScene(new Scene(welcomeScene, Util.WIDTH, Util.HEIGHT));
        primaryStage.show();
    }

    public MenuBar createMenuBar(){
        MenuBar menuBar = new MenuBar();
        Menu welcomeMenu = new Menu("Menu");

        // create menu items and let Main be the event Handler
        MenuItem rulesMenuItem = new MenuItem("Display the rules of the game");
        MenuItem oddsMenuItem = new MenuItem("Display the odds of winning");
        MenuItem exitMenuItem = new MenuItem("Exit game");
        rulesMenuItem.setId("rulesBtn");
        oddsMenuItem.setId("oddsBtn");
        exitMenuItem.setId("exitBtn");
        rulesMenuItem.setOnAction(this);
        oddsMenuItem.setOnAction(this);
        exitMenuItem.setOnAction(this);

        // add menu items to menu
        welcomeMenu.getItems().addAll(rulesMenuItem, oddsMenuItem, exitMenuItem);

        // add menu to menu bar
        menuBar.getMenus().add(welcomeMenu);

        return menuBar;
    }

    @Override
    public void loadGameScene() {
        VBox gameScene = new VBox();

        // add "new look" to the menu before loading gameScene
        MenuItem newLookMenu = createNewLookItem();
        menuBar.getMenus().get(0).getItems().add(newLookMenu);

        gameScene.getChildren().add(menuBar);   // include menu bar in game scene

        // create gameSceneController and make it The event listener of the newLooks button
        GameSceneController gameSceneController = new GameSceneController(gameScene);
        newLookMenu.setOnAction(gameSceneController);

        // load the scene
        primaryStage.setScene(new Scene(gameScene,Util.WIDTH,Util.HEIGHT));
    }

    public MenuItem createNewLookItem(){
        MenuItem newLookMenuItem = new MenuItem("New Look");
        newLookMenuItem.setId("newLookMenu");
        return newLookMenuItem;
    }

    @Override
    public void handle(Event event) {
        if (((MenuItem)event.getSource()).getId() == "rulesBtn"){
            displayGameInfo("Rules for the game:", Util.gameRules );
        }
        else if (((MenuItem)event.getSource()).getId() == "oddsBtn"){
            displayGameInfo("Odds of Winning the Game:");
        }
        else if (((MenuItem)event.getSource()).getId() == "exitBtn"){
            primaryStage.close();
        }

    }

    //Display the Rules and Odds of winning using an alert
    public Alert displayGameInfo(String message, String... menuInfo){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(message);

        if (message.contains("Rules")){
            System.out.println("in Rules");
            alert.setContentText(menuInfo[0]);
            alert.getDialogPane().setMinHeight(400);
        }
        else {
            System.out.println("in odds");
            Image pic = new Image("all-odds.jpg");
            ImageView v = new ImageView(pic);
            alert.getDialogPane().setMinHeight(300);
            alert.setGraphic(v);
        }


        alert.show();
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

        return alert;
    }
}
