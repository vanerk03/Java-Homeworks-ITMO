package game;

 
public interface Board {
    Position getPosition();
    Cell getCell();
    Result makeMove(Move move, int numPlayers, boolean[] isInGame);
}