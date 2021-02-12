package model;

import model.components.GameBoard;
import model.pieces.Advisor;
import model.pieces.General;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeneralTest extends PieceTest{
    @BeforeEach
    public void setup() {
        board = new GameBoard();
        redP = new General(4, 1, board, true);
        blackP = new General(4, 9, board, false);
    }

    @Test
    public void testConstructor() {
        assertTrue(redP.isRed());
        assertEquals(4, redP.getPosX());
        assertEquals(1, redP.getPosY());
        assertEquals(redP, board.getPAt(4, 1));
    }

    @Test
    public void testCanMoveToInPalaceOneGrid() {
        assertTrue(redP.canMoveTo(4, 2));
        assertTrue(redP.canMoveTo(4, 0));
        assertTrue(redP.canMoveTo(3, 1));
        assertTrue(redP.canMoveTo(5, 1));
        assertFalse(redP.canMoveTo(3, 0));
        assertFalse(redP.canMoveTo(3, 2));
    }

    @Test
    public void testCanMoveToInPalaceMultipleGrids() {
        board.redMove("41 30");
        assertTrue(redP.canMoveTo(4, 0));
        assertFalse(redP.canMoveTo(5, 0));
        assertFalse(redP.canMoveTo(3, 2));
        assertFalse(redP.canMoveTo(5, 2));

        board.redMove("41 52");
        assertFalse(redP.canMoveTo(3, 2));
        assertFalse(redP.canMoveTo(5, 0));
        assertFalse(redP.canMoveTo(3, 0));
    }

    @Test
    public void testCanMoveToOutOfPalace() {
        board.redMove("41 32");
        assertFalse(redP.canMoveTo(2, 2));
        assertFalse(redP.canMoveTo(2, 3));

        board.redMove("41 52");
        assertFalse(redP.canMoveTo(5, 3));
        assertFalse(redP.canMoveTo(6, 2));

        board.redMove("41 27");
        assertFalse(blackP.canMoveTo(2, 7));
        assertFalse(blackP.canMoveTo(3, 6));

        board.redMove("41 37");
        assertFalse(blackP.canMoveTo(6, 7));
        assertFalse(blackP.canMoveTo(5, 6));
    }

    @Test
    public void testCanCaptureInPalace() {
        assertTrue(redP.canCapture(4, 2));
        assertTrue(redP.canCapture(4, 0));
        assertTrue(redP.canCapture(3, 1));
        assertTrue(redP.canCapture(5, 1));
        assertFalse(redP.canCapture(3, 0));
        assertFalse(redP.canCapture(3, 2));
    }

    @Test
    public void testCanCaptureOppGeneral() {
        assertTrue(redP.canCapture(4, 9));

        new Advisor(4, 8, board, false);
        assertFalse(redP.canCapture(4, 9));
        assertFalse(redP.canCapture(3, 8));
    }
}
