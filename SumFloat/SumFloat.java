public class SumFloat {
    static Float parseString(String s) {

        Float sum = 0f;
        int start = 0;

        for(int i = 0; i < s.length(); i++) {
            
            char chr = s.charAt(i);
            if (Character.isWhitespace(chr)) {
                if (!s.substring(start, i).isEmpty()) {
                    sum += Float.valueOf(s.substring(start, i));
                }
                start = i + 1;
            } 
            
        }
        return sum;
    }
    public static void main(String[] args) {
        Float res = 0f;

        for(int i = 0; i < args.length; i++) {
            res += parseString(args[i] + " ");
        }
        System.out.println(res);
    }
}
