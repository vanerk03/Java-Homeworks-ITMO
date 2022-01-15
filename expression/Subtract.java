package expression;

public class Subtract extends BinaryOperation {
    
    public Subtract(BothExpressions first, BothExpressions second) {
        super(first, second, "-");
    }

    public int getRes(int ft, int sc) {
        return ft - sc;
    }
}
