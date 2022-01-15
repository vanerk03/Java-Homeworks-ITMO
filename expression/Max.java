package expression;

import java.lang.Math;

public class Max extends BinaryOperation {
    public Max(BothExpressions first, BothExpressions second) {
        super(first, second, "max");
    }

    public int getRes(int ft, int sc) {
        return Math.max(ft, sc);
    }
}
