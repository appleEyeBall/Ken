import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;

class WelcomeSceneControllerTest extends ApplicationTest {
    static WelcomeSceneController welcomeSceneController;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Keno Game");

        // create welcome scene and add menuBar to it
        Pane welcomeScene = new AnchorPane();
        MenuBar menuBar = new MenuBar();
        welcomeScene.getChildren().add( menuBar);

        // load welcome scene and set its controller
        welcomeSceneController = new WelcomeSceneController(welcomeScene, this);
        stage.setScene(new Scene(welcomeScene, Util.WIDTH, Util.HEIGHT));
        stage.show();

        System.out.println("Test loaded");
    }

    @BeforeAll
    static void setup() {
    }

    @BeforeEach
    void ini() throws Exception {
    }
//
    @Test
    void controllerNullTest() {
        Thread one = new Thread() {
            public void run() {

            }
        };

        one.start();
    }
}