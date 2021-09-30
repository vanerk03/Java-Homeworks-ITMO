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

// import java.util.Scanner;
// import java.lang.Math;
// import java.util.ArrayList;

// public class ReverseMin2{
//     static String[] newArray(String[] s) {
//         int n = s.length;
//         String[] lst = new String[2 * n];
//         for (int i = 0; i < n; i++) {
//             lst[i] = s[i];
//         }
//         return lst;
//     }

//     static void dbg() {
//         System.out.println("good");
//     }

//     static void fillIn(ArrayList<Integer>[] s, ArrayList<String> strings, int row) {

//         Scanner sc = new Scanner(strings.get(row));
//         int j = 0;

//         while (sc.hasNextInt()) {
     
//             int i = row - 1;
//             int elem = sc.nextInt();
             
//             while (i > -1 && s[i].size() - 1 < j) {
//                 i--;
//             }

            
//             if (j == 0 && i == -1) {
//                 s[row].add(elem);
//             } else if (i == -1 && j != 0) {
//                 s[row].add(Math.min(elem, s[row].get(j - 1))); 
            
//             } else if(j == 0 && i >= 0) {
//                 s[row].add(Math.min(elem, s[i].get(j)));
//             } else {
//                 s[row].add(Math.min(elem, Math.min(s[i].get(j), s[row].get(j - 1))));
//             }
//             j++; 

//         }
//         sc.close();
//     }

//     static void print(ArrayList<Integer>[] s) {
//         for (int i = 0; i < s.length; i++) {
//             for (int j = 0; j < s[i].size(); j++) {
//                 System.out.print(s[i].get(j) + " ");
//             }
//             System.out.println();
//         }
//     }
    
//     public static void main(String[] args) {
        
//         // String input = "1 2 3\n \n1 2 3";
//         // String[] strings = new String[1];

//         ArrayList<String> strings = new ArrayList();

//         Scanner sc = new Scanner(System.in);
//         int len = 0;

//         while (sc.hasNextLine()) {
//             len++;
//             strings.add(sc.nextLine()); 
//         }

//         ArrayList<Integer>[] s = new ArrayList[len];
        
//         for(int i = 0; i < len; i++) {
//             s[i] = new ArrayList<Integer>();
//             fillIn(s, strings, i);
//         }
//         print(s);
//         sc.close();
//     }
// }
