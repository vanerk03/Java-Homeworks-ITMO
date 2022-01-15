package game;

public class Game {

    private final boolean log;
    private final Player[] players;
    private final int playerNum;
    private boolean[] playerInGame;
    private int playersLeft;

    public Game(final boolean log, final Player... players) {

        this.log = log;
        this.players = players;
        this.playerNum = players.length;
        this.playerInGame = new boolean[playerNum];
        this.playersLeft = players.length;

        for (int i = 0; i < playerNum; i++) {
            playerInGame[i] = true;
        }
    }

    public int play(Board board) {
        int num = 0;
        while (true) {

            final int result = move(board, players[num], num + 1);
            if (result >= 0) {
                clearLost();
                return result;
            }
            num = findNext(num);
        }
    }

    private int findNext(final int nm) {
        for (int i = nm + 1; i < nm + this.playerNum + 1; i++) {
            if (playerInGame[i % playerNum]) {
                return i % playerNum;
            }
        }
        return 0;
    }

    private void clearLost() {
        playersLeft = playerNum;
        for (int i = 0; i < playerNum; i++) {
            playerInGame[i] = true;
        }
    }

    private int move(final Board board, final Player player, final int no) {

        if (playersLeft == 1) {
            log("Player " + no + " won\n");
            return no;
        }
        log("Player " + no + " to move:");

        try {
            final Move move = player.move(board.getPosition(), board.getCell());
            final Result result = board.makeMove(move, playerNum, playerInGame);

            log("Player " + no + " move: " + move + "\n");
            log("Position:\n" + board);

            if (result == Result.WIN) {
                log("Player " + no + " won");
                return no;
            } else if (result == Result.LOSE) {
                log("Player " + no + " lost");
                playerLost(no);
                return -2;
            } else if (result == Result.DRAW) {
                log("Draw");
                return 0;
            } else {
                return -1;
            }
        } catch (Exception e) {
            log("Player " + no + " threw exception with his move therefore he lost");
            playerLost(no);
            return -2;
        }
    }

    private void log(final String message) {
        if (log) {
            System.out.println(message);
        }
    }

    private void playerLost(final int no) {

        playersLeft -= 1;
        playerInGame[no - 1] = false;

        log("Players left: " + playersLeft);
        StringBuilder sb = new StringBuilder("Players still in game: ");

        for (int i = 0; i < playerNum; i++) {
            if (playerInGame[i]) {
                int num = i + 1;
                sb.append(num).append(" ");
            }
        }

        log(sb.toString());
    }
}
