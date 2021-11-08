import java.util.Scanner;
import java.lang.Math;

public class B {
    public static void main(String[] args) {
        
        final int EPS = 710;
        int now = -710 * 25000;
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        sc.close();

        for(int i = 0; i < n; i++) {
            System.out.println(now + i * (EPS));
        }
    }
}
