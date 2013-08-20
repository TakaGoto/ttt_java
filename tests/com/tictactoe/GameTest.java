package com.tictactoe;

import com.tictactoe.Board.Board;
import com.tictactoe.Board.BoardLogic;
import com.tictactoe.Player.Player;
import com.tictactoe.mocks.AiMock;
import com.tictactoe.mocks.UiMock;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.Hashtable;

import static junit.framework.Assert.assertEquals;

public class GameTest {
    Game game;
    UiMock ui;

    @Before public void initialize(){
        Reader reader = new StringReader("4\n5\n");
        ui = new UiMock(reader);
        game = new Game(ui);
    }

    @Test public void testTheGamePlay() {
        Reader newReader = new StringReader("1\n2\n3\n4\n5\n6\n7\n8\n9\nn");
        UiMock newUi = new UiMock(newReader);
        Game newGame = new Game(newUi);
        newGame.playGame();
    }

    @Test public void gameHasBoardSlots(){
        game.startGame();
        assertEquals("_________", game.getBoard().getSlots());
    }

    @Test public void gameHasPlayerOne(){
        game.startGame();
        assertEquals('X', game.getPlayerOne().getMark());
        assertEquals(true, game.getPlayerOne().isHuman());
    }

    @Test public void gameHasPlayerTwo(){
        game.startGame();
        assertEquals('O', game.getPlayerTwo().getMark());
        assertEquals(true, game.getPlayerTwo().isHuman());
    }

    @Test public void gameHasUi(){
        game.startGame();
        assertEquals(ui, game.getUi());
    }

    @Test public void gameStartsGame(){
        game.startGame();
        assertEquals(game.getPlayerOne().getMark(), 'X');
        assertEquals(game.getPlayerTwo().getMark(), 'O');
        assertEquals(game.getBoard().getLength(), 3);
    }

    @Test public void gameEndsGame(){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        game.startGame();
        game.endGame();
        assertEquals("\n\n\n\n\n\n", output.toString());
    }

    @Test public void makePlayerMoves(){
        Reader reader = new StringReader("4\n5\n");
        ui = new UiMock(reader);
        Game newGame = new Game(ui);
        newGame.startGame();
        newGame.makePlayerMoves();
        assertEquals("___XO____", newGame.getBoard().getSlots());
    }

    @Test public void makeComputerMoves(){
        game.startGame();
        AiMock ai = new AiMock('X');
        game.makePlayerMove(ai);
        assertEquals("____X____", game.getBoard().getSlots());
    }

    @Test public void gameHasBoardLogic(){
        game.startGame();
        assertEquals(game.getBoardLogic().getClass(), BoardLogic.class);
    }

    @Test public void returnsComputerPlayer(){
        Player player = game.determinePlayer("c", 'X');
        assertEquals('X', player.getMark());
    }

    @Test public void gameTakeInDataAndMakeAppropriateMoves() {
        Hashtable<String, String> settings = new Hashtable<String, String>();
        settings.put("board", "_________");
        settings.put("playerOne", "h");
        settings.put("playerTwo", "c");
        settings.put("boardSize", "3");
        Board board = Game.playGame(ui, settings, "5");
        assertEquals("____X____", board.getSlots());
    }

    @Test public void gameTakeInDataAndMakeAppropriateMovesForPlayerTwo() {
        Hashtable<String, String> settings = new Hashtable<String, String>();
        settings.put("board", "X________");
        settings.put("playerOne", "h");
        settings.put("playerTwo", "h");
        settings.put("boardSize", "3");
        Board board = Game.playGame(ui, settings, "5");
        assertEquals("X___O____", board.getSlots());
    }

    @Test public void computerMakesMoveWhilePlayingGame() {
        Hashtable<String, String> settings = new Hashtable<String, String>();
        settings.put("board", "_________");
        settings.put("playerOne", "c");
        settings.put("playerTwo", "h");
        settings.put("boardSize", "3");
        Board board = Game.playGame(ui, settings, "");
        assertEquals("X________", board.getSlots());
    }

    @Test public void computerVsComputerMakingMoves() {
        Hashtable<String, String> settings = new Hashtable<String, String>();
        settings.put("board", "_________");
        settings.put("playerOne", "c");
        settings.put("playerTwo", "c");
        settings.put("boardSize", "3");
        Board board = Game.playGame(ui, settings, "1");
        assertEquals("X________", board.getSlots());
    }
}
