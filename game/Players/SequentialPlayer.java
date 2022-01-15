package game.Players;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

public class SequentialPlayer implements Player {
    
    @Override
    public Move move(final Position position, final Cell cell) {

        final int m = position.getM();
        final int n = position.getN();

        for(int r = 0; r < n; r++) {
            for(int c = 0; c < m; c++) {
                final Move move = new Move(r, c, cell);
                if (position.isValid(move)) {
                    return move;
                }   
            }
        }
        
        throw new IllegalStateException("No valid moves");
    }
}
