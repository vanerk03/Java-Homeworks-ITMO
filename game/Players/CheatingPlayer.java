package game.Players;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

public class CheatingPlayer implements Player {

    @Override
    public Move move(final Position position, Cell cell) {
        return new Move(0, 0, null);
    }
}
