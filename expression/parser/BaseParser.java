package expression.parser;

public class BaseParser {

    private static final char EOF = '\0';
    
    private String source;
    private char ch;
    private int lenSource;
    private int pos;

    protected BaseParser() {
        setSource("");
    }

    protected BaseParser(String str) {
        setSource(str);
    }

    protected void nextChar() {
        if (hasNext()) {
            ch = source.charAt(pos++);
        } else {
            ch = EOF;
        }
    }

    protected boolean test(char expected) {
        if (ch == expected) {
            nextChar();
            return true;
        }
        return false;
    }

    protected boolean test(String expected) {
        for(char c: expected.toCharArray()) {
            if (!test(c)) {
                return false;
            }
        }
        return true;
    }
    
    protected boolean eof() {
        whitespace();
        return ch == EOF;
    }

    private boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }

    protected boolean isDigit() {
        return between('0', '9');
    }

    protected boolean isLetter() {
        return between('a', 'z') || between('A', 'Z');
    }

    protected void whitespace() {       
        if (Character.isWhitespace(ch)) {
            nextChar();
            whitespace();
        }
    }

    private boolean hasNext() {
        return pos < lenSource; 
    } 

    protected void setSource(String str) {
        this.source = str;
        this.pos = 0;
        this.lenSource = str.length();
    }

    protected char getChar() {
        return ch;
    }
}