package com.agilefaqs.tdd.demo;

import static com.agilefaqs.tdd.demo.TicTacToe.BLANK;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TicTacToeTest {
	private TicTacToe game = new TicTacToe(3);

	@Test
	public void allCellsAreEmptyInANewGame() {
		assertBoardIs(new char[][] { { BLANK, BLANK, BLANK },
				{ BLANK, BLANK, BLANK },
				{ BLANK, BLANK, BLANK } });
	}

	@Test(expected = IllegalArgumentException.class)
	public void boardHasToBeMinimum3x3Grid() {
		new TicTacToe(2);
	}

	@Test
	public void firstPlayersMoveMarks_X_OnTheBoard() {
		assertEquals("continue", game.move(1, 1));
		assertBoardIs(new char[][] { { BLANK, BLANK, BLANK },
				{ BLANK, 'X', BLANK },
				{ BLANK, BLANK, BLANK } });
	}

	@Test
	public void secondPlayersMoveMarks_O_OnTheBoard() {
		game.move(1, 1);
		assertEquals("continue", game.move(2, 2));
		assertBoardIs(new char[][] { { BLANK, BLANK, BLANK },
				{ BLANK, 'X', BLANK },
				{ BLANK, BLANK, 'O' } });
	}

	@Test
	public void playerCanOnlyMoveToAnEmptyCell() {
		game.move(1, 1);
		assertEquals("(1,1) is already occupied", game.move(1, 1));
	}

	@Test
	public void firstPlayerWithAllSymbolsInOneRowWins() {
		game.move(0, 0);
		game.move(1, 0);
		game.move(0, 1);
		game.move(2, 1);
		assertEquals("X won the game!", game.move(0, 2));
	}

	@Test
	public void firstPlayerWithAllSymbolsInOneColumnWins() {
		game.move(1, 1);
		game.move(0, 0);
		game.move(2, 1);
		game.move(1, 0);
		game.move(2, 2);
		assertEquals("O won the game!", game.move(2, 0));
	}

	@Test
	public void firstPlayerWithAllSymbolsInPrincipalDiagonalWins() {
		game.move(0, 0);
		game.move(1, 0);
		game.move(1, 1);
		game.move(2, 1);
		assertEquals("X won the game!", game.move(2, 2));
	}

	@Test
	public void firstPlayerWithAllSymbolsInMinorDiagonalWins() {
		game.move(0, 2);
		game.move(1, 0);
		game.move(1, 1);
		game.move(2, 1);
		assertEquals("X won the game!", game.move(2, 0));
	}

	@Test
	public void whenAllCellsAreFilledTheGameIsADraw() {
		game.move(0, 2);
		game.move(1, 1);
		game.move(1, 0);
		game.move(2, 1);
		game.move(2, 2);
		game.move(0, 0);
		game.move(0, 1);
		game.move(1, 2);
		assertEquals("Its a Draw!", game.move(2, 0));
	}

	private void assertBoardIs(char[][] expectedBoard) {
		assertArrayEquals(expectedBoard, game.displayBoard());
	}
}
