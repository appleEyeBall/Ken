import javafx.scene.control.MenuBar;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AllTests {
    @BeforeAll
    static void setup() {

    }

    @Test
    void drawTest() {
        String result = ScoresCalculator.calculateDrawScores("1", "1");
        assertEquals(result, "2", "Error");

    }

    @Test
    void draw_2Test() {
        String result = ScoresCalculator.calculateDrawScores("4", "2");
        assertEquals(result, "1", "Error");

    }

    @Test
    void draw_3Test() {
        String result = ScoresCalculator.calculateDrawScores("4", "3");
        assertEquals(result, "5", "Error");

    }

    @Test
    void draw_5Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "4");
        assertEquals(result, "2", "Error");

    }

    @Test
    void draw_6Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "5");
        assertEquals(result, "12", "Error");

    }

    @Test
    void draw7Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "6");
        assertEquals(result, "50", "Error");

    }
    @Test
    void draw_8Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "7");
        assertEquals(result, "750", "Error");

    }
    @Test
    void draw_9Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "8");
        assertEquals(result, "10000", "Error");

    }
    @Test
    void draw_10Test() {
        String result = ScoresCalculator.calculateDrawScores("10", "0");
        assertEquals(result, "5", "Error");

    }
    @Test
    void draw_11Test() {
        String result = ScoresCalculator.calculateDrawScores("10", "5");
        assertEquals(result, "2", "Error");

    }
    @Test
    void draw_12Test() {
        String result = ScoresCalculator.calculateDrawScores("10", "6");
        assertEquals(result, "15", "Error");

    }
    @Test
    void draw_13Test() {
        String result = ScoresCalculator.calculateDrawScores("10", "7");
        assertEquals(result, "40", "Error");

    }
    @Test
    void draw_14Test() {
        String result = ScoresCalculator.calculateDrawScores("10", "8");
        assertEquals(result, "450", "Error");

    }
    @Test
    void draw_15Test() {
        String result = ScoresCalculator.calculateDrawScores("10", "9");
        assertEquals(result, "4250", "Error");

    }
    @Test
    void draw_16Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "0");
        assertEquals(result, "0", "Error");
    }

    @Test
    void draw_18Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "1");
        assertEquals(result, "0", "Error");
    }
    @Test
    void draw_19Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "2");
        assertEquals(result, "0", "Error");
    }
    @Test
    void draw_20Test() {
        String result = ScoresCalculator.calculateDrawScores("8", "3");
        assertEquals(result, "0", "Error");
    }
    @Test
    void draw_21Test() {
        String result = ScoresCalculator.calculateDrawScores("4", "0");
        assertEquals(result, "0", "Error");
    }
    @Test
    void draw_22Test() {
        String result = ScoresCalculator.calculateDrawScores("4", "1");
        assertEquals(result, "0", "Error");
    }

    @Test
    void draw_23Test() {
        String result = ScoresCalculator.calculateTotalScores("4", "3", "12");
        assertEquals(result, "17", "Error");
    }

    @Test
    void draw_24Test() {
        String result = ScoresCalculator.calculateTotalScores("8", "6", "120");
        assertEquals(result, "170", "Error");
    }

    @Test
    void draw_25Test() {
        String result = ScoresCalculator.calculateTotalScores("8", "5", "100");
        assertEquals(result, "112", "Error");
    }

    @Test
    void draw_26Test() {
        String result = ScoresCalculator.calculateTotalScores("8", "3", "132");
        assertEquals(result, "132", "Error");
    }

    @Test
    void draw_27Test() {
        String result = ScoresCalculator.calculateTotalScores("8", "7", "132");
        assertEquals(result, "882", "Error");
    }








}
