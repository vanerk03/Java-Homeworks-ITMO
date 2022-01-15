package expression.exceptions;

import expression.BothExpressions;
public class CheckedAdd extends BinaryOperation {
    
    public CheckedAdd(BothExpressions first, BothExpressions second) {
        super(first, second, "+");
    }

    public int getRes(int ft, int sc) {
        if ((ft > 0 && sc > 0 && ft + sc <= 0) || (ft < 0 && sc < 0 && ft + sc >= 0)) {
            throw new OverflowException("overflow");
        }
        return ft + sc;
    }
}