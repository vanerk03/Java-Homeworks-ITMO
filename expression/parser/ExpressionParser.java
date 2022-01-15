package expression.parser;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import expression.*;

public class ExpressionParser extends BaseParser implements Parser {

    private static Set<Character> VARIABLE_NAMES = Set.of('x', 'y', 'z');

    private final Supplier<BothExpressions> minMax = () -> minMax();
    private final Supplier<BothExpressions> addSubtract = () -> addSubtract();
    private final Supplier<BothExpressions> multiplyDivide = () -> multiplyDivide();
    private final Supplier<BothExpressions> varConst = () -> varConst();

    private final Map<Supplier<BothExpressions>, Supplier<BothExpressions>> nextFunc = Map.of(
            minMax, addSubtract,
            addSubtract, multiplyDivide,
            multiplyDivide, varConst,
            varConst, minMax);

    public BothExpressions parse(final String s) {
        setSource(s);
        nextChar();
        return minMax();
    }

    private BothExpressions minMax() {
        Supplier<BothExpressions> lower = nextFunc.get(minMax);
        BothExpressions first = lower.get();

        while (!eof()) {

            if (test("m")) {
                if (test("in")) {
                    first = new Min(first, lower.get());
                } else if (test("ax")) {
                    first = new Max(first, lower.get());
                }
            } else {
                break;
            }
        }
        return first;
    }

    private BothExpressions addSubtract() {
        Supplier<BothExpressions> lower = nextFunc.get(addSubtract);
        BothExpressions first = lower.get();

        while (!eof()) {
            if (test('+')) {
                first = new Add(first, lower.get());
            } else if (test('-')) {
                first = new Subtract(first, lower.get());
            } else {
                break;
            }
        }
        return first;
    }

    private BothExpressions multiplyDivide() {
        Supplier<BothExpressions> lower = nextFunc.get(multiplyDivide);
        BothExpressions first = lower.get();

        while (!eof()) {
            if (test('*')) {
                first = new Multiply(first, lower.get());
            } else if (test('/')) {
                first = new Divide(first, lower.get());
            } else {
                break;
            }
        }
        return first;
    }

    private BothExpressions varConst() {
        eof();
        if (isDigit()) {
            return parseConst(1);
        }
        if (test('(')) {
            BothExpressions result = nextFunc.get(varConst).get();
            test(')');
            return result;
        }
        if (test('-')) {
            return parseMinus();
        }
        if (isLetter()) {
            return parseVariable();
        } 
        return new Const(0);
    }

    private BothExpressions parseMinus() {
        if (isDigit()) {
            return parseConst(-1);
        }
        return new Minus(varConst());
    }

    private BothExpressions parseConst(int sign) {
        int num = 0;
        while (!eof() && isDigit()) {
            num = num * 10 + sign * (int) (getChar() - '0');
            nextChar();
        }
        return new Const(num);
    }

    private BothExpressions parseVariable() {

        StringBuilder sb = new StringBuilder();
        while (!eof() && isLetter() && VARIABLE_NAMES.contains(getChar())) {
            sb.append(getChar());
            nextChar();
        }
        return new Variable(sb.toString());
    }
}
