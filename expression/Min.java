package expression;

import java.lang.Math;

public class Min extends BinaryOperation {
    public Min(BothExpressions first, BothExpressions second) {
        super(first, second, "min");
    }

    public int getRes(int ft, int sc) {
        return Math.min(ft, sc);
    }
}
