package expression;


public class Multiply extends BinaryOperation {

    public Multiply(BothExpressions first, BothExpressions second) {
        super(first, second, "*");
    }
        
    public int getRes(int ft, int sc) {
        return ft * sc;
    }
}
