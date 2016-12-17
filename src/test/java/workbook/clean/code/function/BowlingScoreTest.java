package workbook.clean.code.function;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by hwang on 17/12/2016.
 */
public class BowlingScoreTest {
    @Test
    public void testScore1() throws Exception {
        assertEquals(137, BowlingScore.score("X -/ X 5- 8/ 9- X 81 1- 4/X"));
    }

    @Test
    public void testScore2() throws Exception {
        assertEquals(140, BowlingScore.score("62 71 X 9- 8/ X X 35 72 5/8"));
    }

    @Test
    public void testScore3() throws Exception {
        assertEquals(300, BowlingScore.score("X X X X X X X X X XXX"));
    }

}