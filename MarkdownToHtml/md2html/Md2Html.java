package md2html;

public class Md2Html {
    public static void main(String[] args) {
        Parser ps = new Parser(args[0], args[1]);
        ps.toHtml();
    }
}
