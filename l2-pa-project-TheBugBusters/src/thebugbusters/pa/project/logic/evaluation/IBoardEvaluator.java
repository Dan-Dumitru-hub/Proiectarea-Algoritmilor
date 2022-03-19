package thebugbusters.pa.project.logic.evaluation;

/**
 * Board evaluator.
 * <p>
 * Evaluates a board and returns the evaluation as an int value.
 */
public interface IBoardEvaluator {
    /**
     * Evaluates a board and returns the evaluation as an int value.
     * @param board board representation.
     * @param numberOfMoves the number of moves available to the current player.
     * @return evaluation score.
     */
    int evaluateBoard(char[][] board, int numberOfMoves);
}
