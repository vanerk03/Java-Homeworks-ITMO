package expression.exceptions;

public class Main {
    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        // TripleExpression exp = new CheckedSubtract(new Const(0), new Const(Integer.MIN_VALUE));
        System.out.println(parser.parse("t0 x * 10 - 2"));
    }
}
