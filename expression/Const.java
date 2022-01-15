package expression;

public class Const implements BothExpressions {
    private final int CONST;

    public Const(int value) {
        this.CONST = value;
    }

    public int evaluate(int x) {
        return CONST;
    }

    
    public int evaluate(int x, int y, int z) {
        return CONST;
    }

    @Override
    public String toString() {
        return Integer.toString(CONST);
    }

    @Override
    public boolean equals(Object obj) {
        
        if (obj == null) {
            return false;
        }

        if (obj.getClass() == this.getClass()) {
            Const other = (Const) obj;
            return other.getCONST() == this.getCONST();
        }
        return false;
    }

    public int getCONST() {
        return CONST;
    }

    @Override 
    public int hashCode() {
        return CONST;
    }
}
