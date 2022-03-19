package thebugbusters.pa.project.logic.evaluation;

import thebugbusters.pa.project.logic.move.PieceOptions;

/**
 * Evaluator class for PAWN.
 */
class PawnEvaluator implements IPieceEvaluator {
    private static final int PAWN_MATERIAL = 100;
    private static final int[][] BLACK_PAWN_BOARD;
    private static final int[][] PAWN_BOARD = {
            {0, 0, 0, 0, 0, 0, 0, 0},
            {50, 50, 50, 50, 50, 50, 50, 50},
            {10, 10, 20, 30, 30, 20, 10, 10},
            {5, 5, 10, 25, 25, 10, 5, 5},
            {0, 0, 0, 20, 20, 0, 0, 0},
            {5, -5, -10, 0, 0, -10, -5, 5},
            {5, 10, 10, -20, -20, 10, 10, 5},
            {0, 0, 0, 0, 0, 0, 0, 0}};

    static {
        BLACK_PAWN_BOARD = new int[8][8];

        for (int i = 7; i >= 0; i--) {
            BLACK_PAWN_BOARD[i] = PAWN_BOARD[7 - i];
        }
    }

    private int attack(final char symbol) {
        if (!PieceOptions.kingIsSafe()) {
            if (Character.isUpperCase(symbol)) {
                return -64;
            } else {
                return 64;
            }
        }

        return 0;
    }

    private int positional(final char symbol, final int line, final int column) {
        if (Character.isUpperCase(symbol)) {
            return PAWN_BOARD[line][column];
        }
        return -BLACK_PAWN_BOARD[line][column];
    }

    private int material(final char symbol) {
        if (Character.isUpperCase(symbol)) {
            return PAWN_MATERIAL;
        }
        return -PAWN_MATERIAL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int evaluatePiece(final char symbol, final int line, final int column) {
        return material(symbol) + positional(symbol, line, column) + attack(symbol);
    }
}
