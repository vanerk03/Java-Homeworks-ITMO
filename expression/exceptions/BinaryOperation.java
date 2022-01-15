package expression.exceptions;

import expression.BothExpressions;
import java.util.Objects;

public abstract class BinaryOperation implements BothExpressions {

    private final BothExpressions first;
    private final BothExpressions second;
    private final String sign;
    private final int hash;

    public BinaryOperation(BothExpressions first, BothExpressions second, String sign) {
        this.first = first;
        this.second = second;
        this.sign = sign;
        this.hash = Objects.hash(first, second, getClass());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        sb.append(first).append(" ").append(sign).append(" ").append(second).append(")");
        return sb.toString();
    }

    public int evaluate(int x, int y, int z) {
        return getRes(first.evaluate(x, y, z), second.evaluate(x, y, z));
        
    }

    public int evaluate(int x) {
        return getRes(first.evaluate(x), second.evaluate(x));
    }

    protected abstract int getRes(int first, int second);


    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }

        BinaryOperation other = (BinaryOperation) obj;
        return Objects.equals(first, other.getFirst()) && Objects.equals(second, other.getSecond());
    }

    @Override
    public int hashCode() {
        return hash;
    }

    public BothExpressions getFirst() {
        return first;
    }

    public BothExpressions getSecond() {
        return second;
    }

    public String getSign() {
        return sign;
    }
}
