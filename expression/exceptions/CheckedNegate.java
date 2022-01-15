package expression.exceptions;

import expression.BothExpressions;

public class CheckedNegate implements BothExpressions {
    
    private BothExpressions first;

    public CheckedNegate(BothExpressions first) {
        this.first =  first;
    }

    public int evaluate(int x) {
        return getRes(first.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return getRes(first.evaluate(x, y, z));
    }

    private int getRes(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException("overflow");
        }
        return -1 * x;
    }

    @Override
    public String toString() {
        return "-(" + first.toString() + ")";
    }
}
