package lk.ijse.dep.service;

public class AiPlayer extends Player {
    public int contt;
    public AiPlayer(Board board) {
        super(board);
    }

    public void movePiece(int col) {
        col = colSelector();//calling the colSelector method
        this.board.updateMove(col, Piece.GREEN);//updating the move in the board 2d array
        this.board.getBoardUI().update(col, false);//updating the board ui
        Winner winner = this.board.findWinner();//finding the winner
        if (winner.getWinningPiece() != Piece.EMPTY) {//checking if the winning piece not equals to EMPTY
            this.board.getBoardUI().notifyWinner(winner);/*notifying the winner by returning the winner object that
            been made in the above step.*/
        } else if (!this.board.existLegalMoves()) {//checking if there are no legal moves on the board.
            this.board.getBoardUI().notifyWinner(new Winner(Piece.EMPTY));/*notify the winner by passing an object that
            have an empty piece*/
        }
    }

    private int colSelector() {
        /*This col selector method had been made, to deduct the complexity of the movePiece method, the working
        principles of this class is so simple, it only returns an int value according to the specific conditions
        of the board,basically , the main objective of this AI is to block the player from winning.it checks for
        available winning cases and prevent it by placing the piece to the position that can block the win of the
        human player, for this , tha minimax uses to predict the best case scenario, if there is no such event ,
        the AI will automatically focus on winning, it will use the minimax again , and it will search for a
        possible winning state, but if there is a user winning situation and the AI winning situation in the board,
        if the AI is playing, it will automatically skip the user blocking operation, and it will place the piece at the
        AI winning possible place of the board. if there is no winning places in the board, the colselector will return
        a random value by the math.random  */
        int tiedColumn = 0;
        //int alpha = Integer.MIN_VALUE;
        //int beta = Integer.MAX_VALUE;
        boolean UserWinningStats = false;
        for (int i = 0; i < 6; i++) {//searching for a tied column
            if (this.board.isLegalMove(i) && board.existLegalMoves()) {
                int row = this.board.findNextAvailableSpot(i);
                this.board.updateMove(i, Piece.GREEN);
                int eval = minimax(0,/* alpha, beta,*/ false);
                this.board.updateMove(i, row, Piece.EMPTY);
                if (eval == 1) {
                    contt = 0;
                    return i;
                }
                if (eval == -1) {
                    UserWinningStats = true;
                }else {
                    tiedColumn = i;
                }
            }
        }
        if ((UserWinningStats) && (this.board.isLegalMove(tiedColumn))) {/*checking is there is a user winning stats
             and legal move at the tied column of the board*/
            contt = 0;
            return tiedColumn;//returning the tied column to the movePiece method.
        }
        int col = 0;

        do {/*if none of those happened above, it will generate a random number that is leagel to move on the board,
             and returns the value to the movePiece method.*/
            col = (int) (Math.random() * 6);
        }while (!this.board.isLegalMove(col));
        contt = 0;
        return col;
    }
    /*private int max(int alpha, int beta){
        return Math.max(alpha, beta);
    }

    private int min(int alpha, int beta){
        return Math.min(alpha, beta);
    }*/

    private int minimax(int depth, /*int alpha, int beta,*/ boolean maximizingPlayer) {
        /*this minimax algorithm had been written by me by watching the Sebasthiyan Lague's video and the Code Bullet's
         video, I had tried to implement the alpha beta pruning part, but i haven't received any progress in that side, but
         without that, the code works just fine, so I had commented all the effort that I had done to implement the alpha
         beta pruning part*/
        contt++;
        System.out.println("minimax operation count id "+contt);
        Winner winner = this.board.findWinner();//checks if there is a winner or game over situation in the game
        if (winner.getWinningPiece() == Piece.GREEN) {//checking whether the winning pieces is green,
            return 1;//if the value
        }
        if (winner.getWinningPiece() == Piece.BLUE) {
            return -1;//user will win
        }
        if ((!this.board.existLegalMoves()) || (depth == 2)) {
            return 0;//this will return the value when only the depth is equals to 2 and are legal moves on the board
        }
        if (this.board.existLegalMoves()) {
            if (maximizingPlayer) {//checking if the move is maximizingplauyer
                for (int i = 0; i < 6; i++)//iterates through the columns of the board
                    if (this.board.isLegalMove(i)) {//check wether the move is leagel
                        //  int maxEval = Integer.MIN_VALUE;
                        int row = this.board.findNextAvailableSpot(i);//getting available spot on the current column
                        this.board.updateMove(i, Piece.GREEN);//update the move
                        int eval = minimax(depth + 1,/* alpha, beta,*/ false);/*checking the
                        playier's maximizing moving stats,*/
                        // maxEval = Math.max(maxEval, eval);//
                        //alpha = Math.max(alpha, eval);
                        //if(beta<= alpha){
                        //  System.out.println("Alpha beta pruning is working inside the if");
                        //this.board.updateMove(i, row, Piece.EMPTY);
                        //return maxEval;
                        //  break;
                        //}//
                        this.board.updateMove(i, row, Piece.EMPTY);//updating the
                        if (eval == 1) {
                            return eval;

                        }
                        // return maxEval;
                    }


            } else {
                for (int i = 0; i < 6; i++) {//checking if the move is minimizing playier
                    //int minEval = Integer.MAX_VALUE;
                    if (this.board.isLegalMove(i)) {//checking wether the move is leagel
                        int row = this.board.findNextAvailableSpot(i);//getting the next available spot of the column
                        this.board.updateMove(i, Piece.BLUE);//update the move
                        int eval = minimax(depth + 1, /*alpha, beta,*/ true);
                        /*returns the minimizing stats of the playier*/
                        //minEval = Math.min(minEval, eval);//
                        //alpha = Math.min(alpha, eval);
                        //if(beta<= alpha){
                        //  System.out.println("Alpha beta pruning is working inside the if");
                        //this.board.updateMove(i, row, Piece.EMPTY);
                        //return minEval;
                        //    break;
                        // }//
                        this.board.updateMove(i, row, Piece.EMPTY);//update the move by an empty piece
                        if (eval == -1) {
                            return eval;
                        }//
                        //return eval;
                    }
                }
            }
        }
        return 0;//if there are nothing, returns
    }
}