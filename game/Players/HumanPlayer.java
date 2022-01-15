package game.Players;

import java.io.PrintStream;
import java.util.Scanner;

import game.Cell;
import game.Move;
import game.Player;
import game.Position;

public class HumanPlayer implements Player {

    private final PrintStream out;
    private final Scanner in;
    public int test;
    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            
            String first = in.next();
            String second = in.next();

            try {

                int r = Integer.parseInt(first);
                int c = Integer.parseInt(second);
                final Move move = new Move(r, c, cell);

                if (position.isValid(move)) {
                    return move;
                }

                out.println("Move " + move + " is invalid");
                
            } catch (Exception e) {
                out.println("Move is a string");
            }
        }
    }
}
