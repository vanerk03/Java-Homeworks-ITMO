package expression.exceptions;

import expression.BothExpressions;

public class LeadingZeroes implements BothExpressions {

    private BothExpressions first;

    public LeadingZeroes(BothExpressions first) {
        this.first = first;
    }

    private int getRes(int x) {
        if (x < 0) {
            return 0;
        }
        int cnt = 0;
        while (x != 0) {
            cnt++;
            x /= 2;
        }
        return 32 - cnt;
    }

    public int evaluate(int x) {
        return getRes(first.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return getRes(first.evaluate(x, y, z));
    }  
    
    @Override
    public String toString() {
        return "l0(" + first.toString() + ")";
    }
}
