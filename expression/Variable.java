package expression;

public class Variable implements BothExpressions {

    public final String name;
    private final int hash;

    public Variable(String name) {
        this.name = name;
        hash = name.hashCode();
    }

    public int evaluate(int x) {
        return x;
    }

    public int evaluate(int x, int y, int z) {
        return switch(name) {
            case("x") -> x;
            case("y") -> y;
            case("z") -> z;
            default -> 0;
        };
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj.getClass() == this.getClass()) {
            Variable other = (Variable) obj;
            return other.name.equals(this.name);
        }
        return false;
    }
    
    @Override
    public int hashCode() {
        return hash;
    }
}
