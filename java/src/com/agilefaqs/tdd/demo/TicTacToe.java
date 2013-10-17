package com.agilefaqs.tdd.demo;

public class TicTacToe {
	public static final char EMPTY = '\u0000';
	private final char[][] board;
	private int moveCount;
	private Referee refree;

	public TicTacToe(int gridSize) {
		if (gridSize < 3)
			throw new IllegalArgumentException("TicTacToe board size has to be minium 3x3 grid");
		board = new char[gridSize][gridSize];
		refree = new Referee(gridSize);
	}

	public char[][] displayBoard() {
		return board.clone();
	}

	public void move(int x, int y) {
		validateCellIsEmpty(x, y);
		board[x][y] = currentSymbol();
		refree.stopGameIfItsOver(x, y, board[x][y], ++moveCount);
	}

	private char currentSymbol() {
		return moveCount % 2 == 0 ? 'X' : 'O';
	}

	private void validateCellIsEmpty(int x, int y) {
		if (board[x][y] != EMPTY)
			throw new CellOccupiedException(x, y);
	}

	private class Referee {
		private static final int NO_OF_DIAGONALS = 2;
		private static final int MINOR = 1;
		private static final int PRINCIPAL = 0;
		private final int gridSize;
		private final int[] rowTotal;
		private final int[] colTotal;
		private final int[] diagonalTotal;

		private Referee(int size) {
			gridSize = size;
			rowTotal = new int[size];
			colTotal = new int[size];
			diagonalTotal = new int[NO_OF_DIAGONALS];
		}

		private void stopGameIfItsOver(int x, int y, char symbol, int moveCount) {
			if (hasSomeoneWonTheGame(x, y, symbol))
				throw new GameOverException(symbol + " won the game!");
			if (isBoardCompletelyFilled(moveCount))
				throw new GameOverException("Its a Draw!");
		}

		private boolean isBoardCompletelyFilled(int moveCount) {
			return moveCount == gridSize * gridSize;
		}

		private boolean hasSomeoneWonTheGame(int x, int y, char symbol) {
			if (isPrincipalDiagonal(x, y) && allSymbolsMatch(symbol, diagonalTotal, PRINCIPAL))
				return true;
			if (isMinorDiagonal(x, y) && allSymbolsMatch(symbol, diagonalTotal, MINOR))
				return true;
			return allSymbolsMatch(symbol, rowTotal, x) || allSymbolsMatch(symbol, colTotal, y);
		}

		private boolean allSymbolsMatch(char symbol, int[] total, int index) {
			total[index] += symbol;
			return total[index] / gridSize == symbol;
		}

		private boolean isPrincipalDiagonal(int x, int y) {
			return x == y;
		}

		private boolean isMinorDiagonal(int x, int y) {
			return x + y == gridSize - 1;
		}
	}
}

final class CellOccupiedException extends RuntimeException {
	public CellOccupiedException(int x, int y) {
		super("(" + x + "," + y + ") is already occupied");
	}
}

final class GameOverException extends RuntimeException {
	public GameOverException(String msg) {
		super(msg);
	}
}
