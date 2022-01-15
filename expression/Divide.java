package expression;
public class Divide extends BinaryOperation {

    public Divide(BothExpressions first, BothExpressions second) {
        super(first, second, "/");
    }

    public int getRes(int ft, int sc) {
        return ft / sc;
    }
}
