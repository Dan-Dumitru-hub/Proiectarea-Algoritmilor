package thebugbusters.pa.project.logic.move;

import thebugbusters.pa.project.logic.evaluation.IBoardEvaluator;
import thebugbusters.pa.project.state.GameState;
import thebugbusters.pa.project.state.Move;
import thebugbusters.pa.project.state.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * As the name suggests, this class implements a standard minimax move finder. No alpha-beta pruning.<p>
 * <p>
 * It depends on an {@link IBoardEvaluator}.
 */
public class MinimaxMoveFinder implements IMoveFinder {
    private final IBoardEvaluator boardEvaluator;

    private final KingOptions kingOptions = new KingOptions();
    private final QueenOptions queenOptions = new QueenOptions();
    private final BishopOptions bishopOptions = new BishopOptions();
    private final RookOptions rookOptions = new RookOptions();
    private final KnightOptions knightOptions = new KnightOptions();
    private final PawnOptions pawnOptions = new PawnOptions();

    private Move move;

    /**
     * Constructs the move finder.
     * @param boardEvaluator your board evaluator of choice.
     */
    public MinimaxMoveFinder(final IBoardEvaluator boardEvaluator) {
        this.boardEvaluator = boardEvaluator;
    }

    /**
     * Detects whether we reached a move repetition. That would cause an automatic draw.
     * <p>
     * This checks 2 move pairs behind.
     *
     * @return true if we have ourselves a repetition.
     */
    private boolean isRepetition() {
        final int size = GameState.moveHistory.size();

        return size >= 8
                && GameState.moveHistory.get(size - 1).equals(GameState.moveHistory.get(size - 5))
                && GameState.moveHistory.get(size - 2).equals(GameState.moveHistory.get(size - 6))
                && GameState.moveHistory.get(size - 3).equals(GameState.moveHistory.get(size - 7))
                && GameState.moveHistory.get(size - 4).equals(GameState.moveHistory.get(size - 8));
    }

    /**
     * Initiates the minimax move search. The search depth is configured in {@link GameState#depth}.
     *
     * @return best move we got.
     */
    private Move minimax() {
        if (GameState.getPlayingAs() == Player.WHITE) {
            max(GameState.depth,-Integer.MAX_VALUE,Integer.MAX_VALUE);
        } else {
            min(GameState.depth,Integer.MAX_VALUE,-Integer.MAX_VALUE);
        }

        return move;
    }

    /**
     * The min part. Grab all available moves available and select the one with the minimum evaluation score.
     * The selected move will be saved in {@link MinimaxMoveFinder#move} to be later returned by
     * {@link MinimaxMoveFinder#minimax()}.
     *
     * @param depth the desired depth.
     * @return the minimum move score found.
     */
    private int min(final int depth,int alpha , int beta) {
        /*
         If depth limit is reached, evaluate current state.
         */
        if (depth == 0) {
            /*
             If this is a repetition draw, return 0 (obvious draw score).
             */
            if (isRepetition()) {
                return 0;
            }

            return this.boardEvaluator.evaluateBoard(GameState.getBoard(), -1);
        }

        final List<Move> moves = getAllOptions();

        /*
         If there are no possible moves, return board evaluation.
         */
        if (moves.isEmpty()) {
            return this.boardEvaluator.evaluateBoard(GameState.getBoard(), moves.size());
        }

        /*
         If we have valid moves, evaluate each one recursively and return the lowest evaluation score found.
         */
        int bestScore = Integer.MAX_VALUE;
        for (final Move move : moves) {
            GameState.movePieceOnBoard(move);
            GameState.setPlayerOnMove(Player.WHITE);

            final int newScore = max(depth - 1,alpha,beta);
            
            if (newScore <= alpha) {
                return alpha;    // alpha cut-off
            }
     
            // dintre toate variantele pe care maxi le permite (!!!),
            // mini o va alege pe cea cu scor minim
            if (newScore < beta) {
                beta = newScore;
            }

            if (bestScore > newScore) {
                bestScore = newScore;
                if (depth == GameState.depth) {
                    this.move = move;
                }
            }

            GameState.reverse(move);
            GameState.setPlayerOnMove(Player.BLACK);
        }

        return beta;
    }

    /**
     * The max part. Grab all available moves available and select the one with the maximum evaluation score.
     * The selected move will be saved in {@link MinimaxMoveFinder#move} to be later returned by
     * {@link MinimaxMoveFinder#minimax()}.
     *
     * @param depth the desired depth.
     * @return the maximum move score found.
     */
    private int max(final int depth,int alpha,int beta) {
        /*
         If depth limit is reached, evaluate current state.
         */
        if (depth == 0) {
            /*
             If this is a repetition draw, return 0 (obvious draw score).
             */
            if (isRepetition()) {
                return 0;
            }

            return this.boardEvaluator.evaluateBoard(GameState.getBoard(), -1);
        }

        final List<Move> moves = getAllOptions();

        /*
         If there are no possible moves, return board evaluation.
         */
        if (moves.isEmpty()) {
            return this.boardEvaluator.evaluateBoard(GameState.getBoard(), moves.size());
        }

        /*
         If we have valid moves, evaluate each one recursively and return the highest evaluation score found.
         */
        int bestScore = Integer.MIN_VALUE;
        for (final Move move : moves) {
            GameState.movePieceOnBoard(move);
            GameState.setPlayerOnMove(Player.BLACK);

            final int newScore = min(depth - 1,alpha,beta);

            if (newScore >= beta) {
                return beta;    // beta cut-off
            }
     
            // dintre toate variantele pe care mini le permite (!!!),
            // maxi o va alege pe cea cu scor maxim
            if (newScore > alpha) {
                alpha = newScore;
            }
            if (bestScore < newScore) {
                bestScore = newScore;
                if (GameState.depth == depth) {
                    this.move = move;
                }
            }

            GameState.reverse(move);
            GameState.setPlayerOnMove(Player.WHITE);
        }

        return alpha;
    }

    /**
     * Find all possible moves for all available pieces of the current player.
     *
     * @return list of all possible moves.
     */
    public List<Move> getAllOptions() {
        final List<Move> allOptions = new ArrayList<>();

        for (int i = 0; i < 64; i++) {
            if ('A' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.WHITE) {
                allOptions.addAll(kingOptions.findOptions(i / 8, i % 8));
            }
            if ('Q' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.WHITE) {
                allOptions.addAll(queenOptions.findOptions(i / 8, i % 8));
            }
            if ('B' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.WHITE) {
                allOptions.addAll(bishopOptions.findOptions(i / 8, i % 8));
            }
            if ('R' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.WHITE) {
                allOptions.addAll(rookOptions.findOptions(i / 8, i % 8));
            }
            if ('K' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.WHITE) {
                allOptions.addAll(knightOptions.findOptions(i / 8, i % 8));
            }
            if ('P' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.WHITE) {
                allOptions.addAll(pawnOptions.findOptions(i / 8, i % 8));
            }
            if ('a' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.BLACK) {
                allOptions.addAll(kingOptions.findOptions(i / 8, i % 8));
            }
            if ('q' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.BLACK) {
                allOptions.addAll(queenOptions.findOptions(i / 8, i % 8));
            }
            if ('b' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.BLACK) {
                allOptions.addAll(bishopOptions.findOptions(i / 8, i % 8));
            }
            if ('r' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.BLACK) {
                allOptions.addAll(rookOptions.findOptions(i / 8, i % 8));
            }
            if ('k' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.BLACK) {
                allOptions.addAll(knightOptions.findOptions(i / 8, i % 8));
            }
            if ('p' == (GameState.getBoard()[i / 8][i % 8]) && GameState.getPlayerOnMove() == Player.BLACK) {
                allOptions.addAll(pawnOptions.findOptions(i / 8, i % 8));
            }
        }

        return allOptions;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Move> findMove() {
        return Optional.of(minimax());
    }
}


