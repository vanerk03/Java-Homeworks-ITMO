import java.util.Scanner;
import java.lang.Math;


public class ReverseMin2{

    static String[] newArray(String[] s) {
        
        int n = s.length;
        String[] lst = new String[2 * n];

        for (int i = 0; i < n; i++) {
            lst[i] = s[i];
        }
        return lst;
    }

    static int countInts(String s) {

        Scanner sc = new Scanner(s);
        int cnt = 0;
        while (sc.hasNextInt()) {
            cnt++;
            sc.nextInt();
        }
        sc.close();
        return cnt;
    }

    static void fillIn(int[][] s, String string, int i) {

        int j = 0;
        Scanner sc = new Scanner(string);
        while (sc.hasNextInt()) {
            s[i][j++] = sc.nextInt();
        }
        sc.close();
    }

    static void print(int[][] s, int mx) {
        for (int j = 0; j < mx; j++) {
            for (int i = 0; i < s.length; i++) {
                System.out.print(s[i][j] + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        
        //String input = "1 2 3\n 5 6 7";
        String[] strings = new String[1];

        Scanner sc = new Scanner(System.in);
        int len = 0;
        int mx = 0;

        while (sc.hasNextLine()) {
            if (len == strings.length - 1) {
                strings = newArray(strings);
            }
            strings[len++] = sc.nextLine(); 
        }
        
        sc.close();
        int[][] s = new int[len][];
        
        for(int i = 0; i < len; i++) {

            int cnt = countInts(strings[i]);
            mx = Math.max(cnt, mx);
            s[i] = new int[cnt];

            fillIn(s, strings[i], i);
        }

        print(s, mx);
        sc.close();
    }
}
