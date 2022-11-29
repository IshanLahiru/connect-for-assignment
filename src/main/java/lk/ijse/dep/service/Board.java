package lk.ijse.dep.service;

public interface Board {
    public static final int NUM_OF_ROWS = 5;
    public static final int NUM_OF_COLS = 6;

    public BoardUI getBoardUI();

    public int findNextAvailableSpot(int paramInt);

    public boolean isLegalMove(int paramInt);

    public boolean existLegalMoves();

    public void updateMove(int paramInt, Piece paramPiece);

    public void updateMove(int paramInt1, int paramInt2, Piece paramPiece);

    public Winner findWinner();
}