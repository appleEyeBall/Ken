import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith( JfxTestRunner.class )
//public class MyUnitTest
//{
//    @Test
//    public void testMyMethod()
//    {
//        //...
//    }
//}

class GameSceneControllerTest {
    static GameSceneController gameSceneController;
    @BeforeAll
    static void setup() {
        gameSceneController = new GameSceneController(new VBox());
    }

//    @BeforeEach
//    void init(){
//
//    }

    public void start(Stage primaryStage) throws Exception {
//        controller = new Controller(primaryStage);
    }

    // test that the constructor works well
//    @Test
//    void controllerNullTest() {
//        assertNotNull(gameSceneController, "Constructor error");
//    }

//    @Test
//    void controllerInitializationTest(){
//        assertNotNull(controller.getStartGameBtn(), "constructor not properly called");
//    }
//
//    @Test
//    void createMenuBarTest() {
//        assertNotNull(controller.createMenuBar(), "menuBar is null");
//    }
//
//    @Test
//    void createMenuBar_2Test() {
//        assertTrue(controller.createMenuBar() instanceof MenuBar, "wrong value");
//    }
//
//    @Test
//    void displayGameInfoTest() {
//        assertNotNull(controller.displayGameInfo(""), "Alert is null");
//    }
//
//    @Test
//    void displayGameInfo_2Test() {
//        assertEquals(controller.displayGameInfo("Rules", "info").getContentText(), "info", "Wrong value");
//    }
//
//    @Test
//    void displayGameInfo_3Test() {
//        assertEquals(controller.displayGameInfo("Rules", "info").getHeaderText(), "Rules", "wrong value");
//    }
//
//    @Test
//    void displayGameInfo_4Test() {
//        assertEquals(controller.displayGameInfo("Odds", "info").getHeaderText(), "Odds", "wrong value");
//    }
//
//    @Test
//    void displayGameInfo_5Test() {
//        assertNotNull(controller.displayGameInfo("Odds", "info").getGraphic(), "Alert Image is null");
//    }
}
