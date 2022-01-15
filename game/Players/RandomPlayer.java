package game.Players;

import java.util.Random;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

public class RandomPlayer implements Player {
    private final Random random;

    public RandomPlayer(final Random random) {
        this.random = random;
    }

    public RandomPlayer() {
        this(new Random());
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        
        final int m = position.getM();
        final int n = position.getN();

        while (true) {
            int r = random.nextInt(n);
            int c = random.nextInt(m);

            final Move move = new Move(r, c, cell);
            if (position.isValid(move)) {
                return move;
            }
        }
    }
}
