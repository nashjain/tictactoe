package com.agilefaqs.tdd.demo;

import static com.agilefaqs.tdd.demo.TicTacToe.EMPTY;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

public class TicTacToeTest {
	private TicTacToe game = new TicTacToe(3);
	private String winner;

	@Test
	public void allCellsAreEmptyInANewGame() {
		assertBoardIs(new char[][] { { EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY },
				{ EMPTY, EMPTY, EMPTY } });
	}

	@Test(expected = IllegalArgumentException.class)
	public void boardHasToBeMinimum3x3Grid() {
		new TicTacToe(2);
	}

	@Test
	public void firstPlayersMoveMarks_X_OnTheBoard() {
		game.move(1, 1);
		assertBoardIs(new char[][] { { EMPTY, EMPTY, EMPTY },
				{ EMPTY, 'X', EMPTY },
				{ EMPTY, EMPTY, EMPTY } });
	}

	@Test
	public void secondPlayersMoveMarks_O_OnTheBoard() {
		game.move(1, 1);
		game.move(2, 2);
		assertBoardIs(new char[][] { { EMPTY, EMPTY, EMPTY },
				{ EMPTY, 'X', EMPTY },
				{ EMPTY, EMPTY, 'O' } });
	}

	@Test
	public void playerCanOnlyMoveToAnEmptyCell() {
		game.move(1, 1);
		try {
			game.move(1, 1);
			fail("Should have thrown an exception");
		} catch (CellOccupiedException e) {
			assertEquals("(1,1) is already occupied", e.getMessage());
		}
	}

	@Test
	public void firstPlayerWithAllSymbolsInOneRowWins() {
		game.move(0, 0);
		game.move(1, 0);
		game.move(0, 1);
		game.move(2, 1);
		verify("X").winsTheGameWhenMovedTo(0, 2);
	}

	@Test
	public void firstPlayerWithAllSymbolsInOneColumnWins() {
		game.move(1, 1);
		game.move(0, 0);
		game.move(2, 1);
		game.move(1, 0);
		game.move(2, 2);
		verify("O").winsTheGameWhenMovedTo(2, 0);
	}

	@Test
	public void firstPlayerWithAllSymbolsInPrincipalDiagonalWins() {
		game.move(0, 0);
		game.move(1, 0);
		game.move(1, 1);
		game.move(2, 1);
		verify("X").winsTheGameWhenMovedTo(2, 2);
	}

	@Test
	public void firstPlayerWithAllSymbolsInMinorDiagonalWins() {
		game.move(0, 2);
		game.move(1, 0);
		game.move(1, 1);
		game.move(2, 1);
		verify("X").winsTheGameWhenMovedTo(2, 0);
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
		try {
			game.move(2, 0);
			fail("Should have thrown an exception");
		} catch (GameOverException e) {
			assertEquals("Its a Draw!", e.getMessage());
		}
	}

	private TicTacToeTest verify(String winner) {
		this.winner = winner;
		return this;
	}

	private void winsTheGameWhenMovedTo(int x, int y) {
		try {
			game.move(x, y);
			fail("Should have thrown an exception");
		} catch (GameOverException e) {
			assertEquals(winner + " won the game!", e.getMessage());
		}
	}

	private void assertBoardIs(char[][] expectedBoard) {
		assertArrayEquals(expectedBoard, game.displayBoard());
	}
}
