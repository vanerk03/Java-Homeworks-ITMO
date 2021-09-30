public class Scanner {
    
    private final String str;
    public int last;

    public Scanner(String str) {
        this.str = str + " ";
        this.last = 0;
    }


    public int nextInt() {
        
        int start = this.last;
        int res = 0;
        //start equals to a probable start of the new int
        for(int i = this.last; i < this.str.length(); i++) {
            
            char chr = this.str.charAt(i);
            if (Character.isWhitespace(chr)) {
                if (i != start) {
                    res = Integer.valueOf(this.str.substring(start, i));
                    this.last = i;
                    break;
                }
                start = i + 1;
            }
        }
        return res;
    }
       
}