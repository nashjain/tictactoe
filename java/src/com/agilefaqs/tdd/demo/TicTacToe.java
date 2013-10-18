package com.agilefaqs.tdd.demo;

public class TicTacToe {
	public static final char BLANK = '\u0000';
	private final char[][] board;
	private int moveCount;
	private Referee refree;

	public TicTacToe(int gridSize) {
		if (gridSize < 3)
			throw new IllegalArgumentException("TicTacToe board size has to be minimum 3x3 grid");
		board = new char[gridSize][gridSize];
		refree = new Referee(gridSize);
	}

	public char[][] displayBoard() {
		return board.clone();
	}

	public String move(int x, int y) {
		if (board[x][y] != BLANK)
			return "(" + x + "," + y + ") is already occupied";
		board[x][y] = whoseTurn();
		return refree.isGameOver(x, y, board[x][y], ++moveCount);
	}

	private char whoseTurn() {
		return moveCount % 2 == 0 ? 'X' : 'O';
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

		private String isGameOver(int x, int y, char symbol, int moveCount) {
			if (isWinningMove(x, y, symbol))
				return symbol + " won the game!";
			if (isBoardCompletelyFilled(moveCount))
				return "Its a Draw!";
			return "continue";
		}

		private boolean isBoardCompletelyFilled(int moveCount) {
			return moveCount == gridSize * gridSize;
		}

		private boolean isWinningMove(int x, int y, char symbol) {
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
