package expression;
public class Add extends BinaryOperation {

    public Add(BothExpressions first, BothExpressions second) {
        super(first, second, "+");
    }
    
    public int getRes(int ft, int sc) {
        return ft + sc;
    }
}
