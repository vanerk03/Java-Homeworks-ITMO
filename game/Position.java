package game;


public interface Position {
    boolean isValid(Move move);
    boolean isTurn(Move move);
    Cell getCell(int r, int c);
    int getM();
    int getN();
}
