package lk.ijse.dep.service;

public class Winner {
    private Piece winningPiece;
    private int col1 = -1;
    private int col2 = -1;
    private int row1 = -1;
    private int row2 = -1;

    public Winner(Piece winningPiece) {
        this.winningPiece = winningPiece;
    }

    public Winner(Piece winningPiece, int col1, int row1, int col2, int row2) {
        this.winningPiece = winningPiece;
        this.col1 = col1;
        this.row1 = row1;
        this.col2 = col2;
        this.row2 = row2;
    }

    public Piece getWinningPiece() {

        return winningPiece;
    }

    public void setWinningPiece(Piece piece) {
        this.winningPiece = piece;
    }

    public void setCol1(int col) {
        this.col1 = col;
    }

    public void setCol2(int col) {
        this.col2 = col;
    }

    public void setRow1(int row) {
        this.row1 = row;
    }

    public void setRow2(int row) {
        this.row2 = row;
    }

    public String toString() {
        return "Winner{" +
                "winningPiece=" + winningPiece +
                ", col1=" + col1 +
                ", row1=" + row1 +
                ", col2=" + col2 +
                ", row2=" + row2 +
                '}';

    }

    public int getCol2() {
        return col2;
    }

    public int getCol1() {
        return col1;
    }

    public int getRow2() {
        return row2;
    }

    public int getRow1() {
        return row1;
    }
}
