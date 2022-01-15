package game;

import java.util.Scanner;

import game.Players.*;

import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter players");
        List<Player> players = new ArrayList<>();

        PlayerSelection: while (true) {
            String nextPlayer = sc.next();

            switch (nextPlayer) {
                case "H":
                    players.add(new HumanPlayer());
                    break;
                case "R":
                    players.add(new RandomPlayer());
                    break;
                case "S":
                    players.add(new SequentialPlayer());
                    break;
                case "start":
                    break PlayerSelection;
            }
        }

        final Game game = new Game(true, players.toArray(new Player[0]));
        int result;

        do {
            System.out.println("Enter m, n, k");

            try {

                String tempM = sc.next();

                if (tempM.equals("exit")) {
                    break;
                }

                String tempN = sc.next();
                String tempK = sc.next();

                int m = Integer.parseInt(tempM);
                int n = Integer.parseInt(tempN);
                int k = Integer.parseInt(tempK);

                result = game.play(new MnkBoard(m, n, k));
                System.out.println("Game result: " + result);

            } catch (NumberFormatException e) {
                System.out.println("m, n, k should be integer");
            }

        } while (true);
        sc.close();
    }
}
