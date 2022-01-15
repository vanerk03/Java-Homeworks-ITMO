package game;

import java.util.Arrays;
import java.util.Map;

public class MnkBoard implements Board, Position {

    private static final Map<Cell, Character> SYMBOLS = Map.of(Cell.X, 'X', Cell.O, 'O', Cell.E, '.', Cell.Y, '-',
            Cell.Z, '|');
    private Cell[] turns = new Cell[]{Cell.X, Cell.O, Cell.Y, Cell.Z};

    private final int m, n, k;
    private final Cell[][] cells;
    private Cell turn;
    private int empty;

    public MnkBoard(int m, int n, int k) {

        this.m = m;
        this.n = n;
        this.k = k;
        this.cells = new Cell[n][m];
        for (Cell[] row : cells) {
            Arrays.fill(row, Cell.E);
        }
        turn = Cell.X;
        this.empty = m * n;
        System.out.println("Start Position: \n" + this.toString());
    }

    private boolean checkDirection(int shiftRow, int shiftCol, int r, int c, int k) {

        int cnt = 1;
        final Cell value = cells[r][c];

        for (int factor = -1; factor <= 1; factor += 2) {
            for (int i = 1; i <= k; i++) {

                final int tempR = r + i * shiftRow * factor;
                final int tempC = c + i * shiftCol * factor;

                if (isGood(tempR, tempC) && cells[tempR][tempC] == value) {
                    cnt++;
                } else {
                    break;
                }
            }
        }

        return (cnt >= k);
    }

    public boolean isGood(int r, int c) {
        return (r >= 0 && r < this.n && c >= 0 && c < this.m);
    }

    private void nextTurn(final boolean[] isInGame, final int numPlayers) {
        int num = 0;
        for (int i = 0; i < 4; i++) {
            if (turn == turns[i]) {
                num = i;
                break;
            }
        }

        for (int i = num + 1; i <= num + 4; i++) {
            int tmp = i % numPlayers;
            if (isInGame[tmp]) {
                this.turn = turns[tmp];
                break;
            }
        }
    }

    private Result checkResult(final int r, final int c, final int numPlayers) {

        final int[] shiftCol = new int[]{0, -1, -1, -1};
        final int[] shiftRow = new int[]{-1, 1, -1, 0};
        boolean res = false;

        for (int i = 0; i < 4; i++) {
            res = (res || checkDirection(shiftRow[i], shiftCol[i], r, c, k));
        }

        if (res) {
            return Result.WIN;
        }
        if (empty == 0) {
            return Result.DRAW;
        }
        return Result.UNKNOWN;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Cell getCell() {
        return turn;
    }

    @Override
    public Result makeMove(final Move move, final int numPlayers, boolean[] isInGame) {

        final int r = move.getRow();
        final int c = move.getColumn();

        if (!isValid(move) || move.getValue() == null) {
            nextTurn(isInGame, numPlayers);
            return Result.LOSE;
        }
        nextTurn(isInGame, numPlayers);
        empty--;

        cells[move.getRow()][move.getColumn()] = move.getValue();
        return checkResult(r, c, numPlayers);
    }

    public boolean isTurn(final Move move) {
        return turn == move.getValue();
    }

    @Override
    public boolean isValid(final Move move) {

        int r = move.getRow();
        int c = move.getColumn();
        return isGood(r, c) && cells[move.getRow()][move.getColumn()] == Cell.E && isTurn(move);
    }

    @Override
    public Cell getCell(final int r, final int c) {
        return cells[r][c];
    }

    @Override
    public String toString() {
        int lenVer = Integer.toString(n - 1).length();

        StringBuilder hor = new StringBuilder();
        StringBuilder ver = new StringBuilder();

        hor.append(" ");

        for (int i = 0; i < lenVer; i++) {
            ver.append("\n");
        }

        StringBuilder sb = new StringBuilder(hor.toString() + " ");

        for (int i = 0; i < m; i++) {
            sb.append(i + hor.toString().substring(0, hor.length() - Integer.toString(i).length() + 1));
        }

        for (int r = 0; r < n; r++) {
            sb.append(ver);
            sb.append(r + hor.toString().substring(0, hor.length() - Integer.toString(r).length() + 1));
            for (int c = 0; c < m; c++) {
                sb.append(SYMBOLS.get(cells[r][c])).append(hor.toString());
            }
        }

        return sb.toString();
    }

    @Override
    public int getM() {
        return this.m;
    }

    @Override
    public int getN() {
        return this.n;
    }
}
