package expression;

public class Minus implements BothExpressions {
    
    BothExpressions first;

    public Minus(BothExpressions first) {
        this.first =  first;
    }

    public int evaluate(int x) {
        return -1 * first.evaluate(x);
    }

    public int evaluate(int x, int y, int z) {
        return -1 * first.evaluate(x, y, z);
    }

    @Override
    public String toString() {
        return "-(" + first.toString() + ")";
    }
}
