import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Controller implements EventHandler{
    Pane welcomeScene;
    VBox gameScene;
    Stage primaryStage;
    Button startGameBtn;
    Menu gameSceneMenu;
    MenuBar gameSceneMenuBar;
    GameSceneController gameSceneController;
    int countLookChanges =0; // for back and forth Look changes
    Menu newLookMenu;

    public Controller(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.setSceneUp();
        startGameBtn.setOnAction(this);
        gameSceneController = new GameSceneController(gameScene);
        setNewLooks(false);

    }


    private void setSceneUp(){
        welcomeScene = new AnchorPane();
        gameScene = new VBox();
        MenuItem newLookMenuItem = new MenuItem("New Look");
        newLookMenuItem.setId("newLookMenu");
        newLookMenuItem.setOnAction(this);

        startGameBtn = new Button();
        startGameBtn.setText("Start Game");
        welcomeScene.getChildren().add(startGameBtn);
        AnchorPane.setLeftAnchor(startGameBtn, 325.0);
        AnchorPane.setTopAnchor(startGameBtn, 225.0);


        welcomeScene.getChildren().add(this.createMenuBar());

        // create menuBar for game scene (welcome Menu + newLooks option)
        gameSceneMenu = this.createMenuBar().getMenus().get(0);
        gameSceneMenu.getItems().add(newLookMenuItem);
        gameSceneMenuBar = new MenuBar(gameSceneMenu);
        gameScene.getChildren().add(gameSceneMenuBar);

        primaryStage.setTitle("Keno Game");
        primaryStage.setScene(new Scene(welcomeScene, Util.width, Util.height));
        primaryStage.show();

    }

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
        else if (((MenuItem)event.getSource()).getId() == "newLookMenu"){    //event handling for new Looks menu item in the game scene menu
            countLookChanges++;
            if(countLookChanges%2 ==0){ //switch back to old look
                setNewLooks(false);
            }
            else{            // set a new look
                setNewLooks(true);
            }
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
    }

    //Display the Rules and Odds of winning using an alert
    public Alert displayGameInfo(String message, String menuInfo){
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setTitle(message);

        if (message.contains("Rules")){
            System.out.println("in Rules");
            alert.setContentText(menuInfo);
        }
        else {
            System.out.println("in odds");
            Image pic = new Image("all-odds.jpg");
            ImageView v = new ImageView(pic);
            alert.setGraphic(v);
        }

        alert.getDialogPane().setMinHeight(300);
        alert.show();
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);

        return alert;
    }

// change the looks of the game scene
    public void setNewLooks(Boolean newLooksStatus){

        // made the score labels' font bold
        gameSceneController.scoreValue.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        gameSceneController.scoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        gameSceneController.drawingScoreLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 18));
        gameSceneController.drawingScoreValue.setFont(Font.font("Verdana", FontWeight.BOLD, 18));

        if(newLooksStatus) { // if the button is pressed to set a new look
            gameSceneController.spotsBox.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.drawingsRow.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.gameScene.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.betCard.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.scoreLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.scoreValue.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.drawingScoreLabel.setBackground( Background.EMPTY);
            gameSceneController.drawingScoreValue.setBackground(Background.EMPTY);


        }
        else{   // if the button is pressed to switch back to previous look
            gameSceneController.gameScene.setBackground(new Background(new BackgroundFill (Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.betCard.setBackground(Background.EMPTY);
            gameSceneController.spotsBox.setBackground(Background.EMPTY);
            gameSceneController.drawingsRow.setBackground(Background.EMPTY);
            gameSceneController.scoreLabel.setBackground(Background.EMPTY);
            gameSceneController.scoreLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.scoreValue.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.drawingScoreLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
            gameSceneController.drawingScoreValue.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        }

    }

}