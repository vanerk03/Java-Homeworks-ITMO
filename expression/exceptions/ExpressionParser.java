package expression.exceptions;

import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

import expression.BothExpressions;
import expression.Max;
import expression.Min;
import expression.Const;
import expression.Variable;

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
        BothExpressions result = minMax();
        if (eof()) {
            return result;
        } else {
            throw new ParseException("Bracket Mismatch");
        }
    }

    private BothExpressions minMax() {
        Supplier<BothExpressions> lower = nextFunc.get(minMax);
        BothExpressions first = lower.get();

        while (!eof()) {
            if (test("m")) {
                if (test("in") && (Character.isWhitespace(getChar()) || getChar() == '(') || getChar() == '-') {
                    first = new Min(first, lower.get());
                } else if (test("ax") && (Character.isWhitespace(getChar()) || getChar() == '(') || getChar() == '-') {
                    first = new Max(first, lower.get());
                } else {
                    throw new ParseException("expected min or max, received m**");
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
                first = new CheckedAdd(first, lower.get());
            } else if (test('-')) {
                first = new CheckedSubtract(first, lower.get());
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
                first = new CheckedMultiply(first, lower.get());
            } else if (test('/')) {
                first = new CheckedDivide(first, lower.get());
            } else {
                break;
            }
        }
        return first;
    }

    private BothExpressions varConst() {
        if (eof()) {
            throw new ParseException("Expected variable or const, found EOF");
        }
        if (isDigit()) {
            return parseConst(1);
        }
        if (test('(')) {
            BothExpressions result = nextFunc.get(varConst).get();
            if (!test(')')) {
                throw new ParseException("Excected: ), found:" + getChar());
            }
            return result;
        }
        if (test('l')) {
            if (test('0') && (Character.isWhitespace(getChar()) || getChar() == '(')) {
                return new LeadingZeroes(varConst());
            } else {
                throw new ParseException("exprected l0, got l*");
            }
        }   
        if (test('t')) {
            if (test('0') && (Character.isWhitespace(getChar()) || getChar() == '(')) {
                return new TailZeroes(varConst());                
            } else {
                throw new ParseException("exprected d0, got t*");
            }
        }
        if (test('-')) {
            return parseMinus();
        }
        if (isValidName()) {
            return parseVariable();
        }

        throw new ParseException("Unknown symbol");
    }

    private BothExpressions parseMinus() {
        if (isDigit()) {
            return parseConst(-1);
        }
        return new CheckedNegate(varConst());
    }

    private BothExpressions parseConst(int sign) {
        int num = 0;
        while (isDigit()) {
            char ch = getChar();
            if ((num > 0 && num * 10 + sign * (ch - '0') < 0)
                    || (num < 0 && num * 10 + sign * (ch - '0') > 0)) {
                throw new OverflowException("Overflow in parseConst method");
            }
            num = num * 10 + sign * (int) (ch - '0');
            nextChar();
        }
        return new Const(num);
    }

    private BothExpressions parseVariable() {
        final String name = Character.toString(getChar());
        nextChar();
        return new Variable(name);
    }

    private boolean isValidName() {
        return VARIABLE_NAMES.contains(getChar());
    }
}