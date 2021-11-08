import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class M {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();

        for (int test = 0; test < t; test++) {

            int n = sc.nextInt();
            List<Integer> lst = new ArrayList<Integer>();

            for (int i = 0; i < n; i++) {
                int x = sc.nextInt();
                lst.add(x);
            }

            int res = 0;
            HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

            for (int j = n - 1; j >= 0; j--) {
                for (int i = 0; i < j; i++) {
                    int tmp = 2 * lst.get(j) - lst.get(i);
                    if (hm.containsKey(tmp)) {
                        res = res + hm.get(tmp);
                    }
                }

                int key = lst.get(j);

                if (hm.containsKey(key)) {
                    hm.put(key, hm.get(key) + 1);
                } else {
                    hm.put(key, 1);
                }
            }
            System.out.println(res);
        }
        sc.close();
    }
}
