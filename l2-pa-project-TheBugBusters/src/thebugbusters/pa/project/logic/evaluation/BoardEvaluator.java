package thebugbusters.pa.project.logic.evaluation;

import thebugbusters.pa.project.logic.move.PieceOptions;
import thebugbusters.pa.project.state.GameState;
import thebugbusters.pa.project.state.Player;

/**
 * Board evaluator class.
 * <p>
 * Evaluates a chess board. Evaluates a WHITE advantage as positive score and negative otherwise.
 */
public class BoardEvaluator implements IBoardEvaluator {
    private final IPieceEvaluator pawnEvaluator;
    private final IPieceEvaluator rookEvaluator;
    private final IPieceEvaluator knightEvaluator;
    private final IPieceEvaluator bishopEvaluator;
    private final IPieceEvaluator queenEvaluator;
    private final IPieceEvaluator kingEvaluator;

    public BoardEvaluator() {
        this.pawnEvaluator = new PawnEvaluator();
        this.rookEvaluator = new RookEvaluator();
        this.knightEvaluator = new KnightEvaluator();
        this.bishopEvaluator = new BishopEvaluator();
        this.queenEvaluator = new QueenEvaluator();
        this.kingEvaluator = new KingEvaluator();
    }

    private int endGameScore() {
        final int multiplier = GameState.getPlayerOnMove() == Player.WHITE ? 1 : -1;

        if (!PieceOptions.kingIsSafe()) {
            return (Integer.MIN_VALUE + 10) * multiplier;
        } else {
            return 0;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int evaluateBoard(final char[][] board, final int movesNumber) {
        if (movesNumber == 0) {
            return endGameScore();
        }

        int score = 0;

        for (int i = 0; i < 64; i++) {
            switch (board[i / 8][i % 8]) {
                case 'P':
                case 'p':
                    score += this.pawnEvaluator.evaluatePiece(board[i / 8][i % 8], i / 8, i % 8);
                    break;
                case 'R':
                case 'r':
                    score += this.rookEvaluator.evaluatePiece(board[i / 8][i % 8], i / 8, i % 8);
                    break;
                case 'K':
                case 'k':
                    score += this.knightEvaluator.evaluatePiece(board[i / 8][i % 8], i / 8, i % 8);
                    break;
                case 'B':
                case 'b':
                    score += this.bishopEvaluator.evaluatePiece(board[i / 8][i % 8], i / 8, i % 8);
                    break;
                case 'Q':
                case 'q':
                    score += this.queenEvaluator.evaluatePiece(board[i / 8][i % 8], i / 8, i % 8);
                    break;
                case 'A':
                case 'a':
                    score += this.kingEvaluator.evaluatePiece(board[i / 8][i % 8], i / 8, i % 8);
                    break;
            }
        }

        return score;
    }
}
