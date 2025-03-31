package edu.uw.app.Model;

import java.util.Arrays;

/**
 * Class that represents the game board.
 * @author Roman Bureacov
 * @version 2025-03-27
 */
class GameBoard implements Board {

    private final int[][] fGameBoard;

    /**
     * Creates a new, square game board with the specified size.
     */
    public GameBoard(final int pSize) {
        super();
        if (pSize < 3) throw new IllegalArgumentException(
                "Argument pSize for the board (%d) must be at least 3".formatted(pSize));
        else if (10 < pSize) throw new IllegalArgumentException(
                "Argument pSize is unreasonably large (%d > 10)".formatted(pSize)
        );

        this.fGameBoard = new int[pSize][pSize];
    }

    @Override
    public int get(final Point pPoint) {
        return this.get(pPoint.getX(), pPoint.getY());
    }

    @Override
    public int get(final int pX, final int pY) {
        return this.fGameBoard[pY][pX];
    }

    @Override
    public void set(final Point pPoint, final int pToInt) {
        this.set(pPoint.getX(), pPoint.getY(), pToInt);
    }

    @Override
    public void set(final int pX, final int pY, final int pToInt) {
        this.fGameBoard[pX][pY] = pToInt;
    }

    @Override
    public int size() {
        return this.fGameBoard.length;
    }

    @Override
    public int[][] getBoard() {
        final int boardSize = this.fGameBoard.length;
        final int[][] boardCopy = new int[boardSize][boardSize];
        for (int row = 0; row < boardSize; row++) {
            boardCopy[row] = Arrays.copyOf(this.fGameBoard[row], boardSize);
        }
        return boardCopy;
    }

}
