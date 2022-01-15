package expression.exceptions;

import expression.BothExpressions;

public class CheckedDivide extends BinaryOperation {

    public CheckedDivide(BothExpressions first, BothExpressions second) {
        super(first, second, "/");
    }
        
    public int getRes(int ft, int sc) {
        if (sc == 0 || (ft == Integer.MIN_VALUE && sc == -1)) {
            throw new OverflowException("zero division");
        }
        return ft / sc;
    }
}
