import javafx.scene.control.MenuBar;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {
    static Controller controller;
    static int iterCount = 0;
    @BeforeAll
    static void setup() {
        controller = new Controller(new Stage());
    }

    @BeforeEach
    void init(){

    }

    // test that the constructor works well
    @Test
    void controllerNullTest() {
        assertNotNull(controller, "Constructor error");
    }

    @Test
    void controllerInitializationTest(){
        assertNotNull(controller.getStartGameBtn(), "constructor not properly called");
    }

    @Test
    void createMenuBarTest() {
        assertNotNull(controller.createMenuBar(), "menuBar is null");
    }

    @Test
    void createMenuBar_2Test() {
        assertTrue(controller.createMenuBar() instanceof MenuBar, "wrong value");
    }

    @Test
    void displayGameInfoTest() {
        assertNotNull(controller.displayGameInfo(""), "Alert is null");
    }

    @Test
    void displayGameInfo_2Test() {
        assertEquals(controller.displayGameInfo("Rules", "info").getContentText(), "info", "Wrong value");
    }

    @Test
    void displayGameInfo_3Test() {
        assertEquals(controller.displayGameInfo("Rules", "info").getHeaderText(), "Rules", "wrong value");
    }

    @Test
    void displayGameInfo_4Test() {
        assertEquals(controller.displayGameInfo("Odds", "info").getHeaderText(), "Odds", "wrong value");
    }

    @Test
    void displayGameInfo_5Test() {
        assertNotNull(controller.displayGameInfo("Odds", "info").getGraphic(), "Alert Image is null");
    }
}
