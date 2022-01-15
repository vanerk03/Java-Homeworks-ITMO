package expression.exceptions;

import expression.BothExpressions;

public class CheckedMultiply extends BinaryOperation {

    public CheckedMultiply(BothExpressions first, BothExpressions second) {
        super(first, second, "*");
    }
        
    public int getRes(int ft, int sc) {
        int res = ft * sc;
        if ((ft != 0 && res / ft != sc) || (sc != 0 && res / sc != ft)) {
            throw new OverflowException("overflow");
        }
        return ft * sc;
    }
}
