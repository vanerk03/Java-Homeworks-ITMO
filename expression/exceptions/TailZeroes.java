package expression.exceptions;

import expression.BothExpressions;

public class TailZeroes implements BothExpressions {
    private BothExpressions first;

    public TailZeroes(BothExpressions first) {
        this.first = first;
    }

    private int getRes(int x) {
        int cnt = 0;
        if (x == 0) {
            return 32;
        } 
        if (x == Integer.MIN_VALUE) {
            return 31;
        }
        if (x < 0) {
            x *= -1;
        }
        while (true) {
            if (x % 2 == 1) {
                break;
            }
            cnt++;
            x /= 2;
        }
        return cnt;
    }

    public int evaluate(int x) {
        return getRes(first.evaluate(x));
    }

    public int evaluate(int x, int y, int z) {
        return getRes(first.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "t0(" + first.toString() + ")";
    }
}
