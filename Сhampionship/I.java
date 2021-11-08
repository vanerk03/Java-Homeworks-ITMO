import java.util.Scanner;
import java.lang.Math;
public class I {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        long xl = Long.MAX_VALUE, xr = Long.MIN_VALUE, yl = Integer.MAX_VALUE, yr = Long.MIN_VALUE;

        n = sc.nextInt();
        for(int i = 0; i < n; i++) {
            int x, y, h;
            x = sc.nextInt();
            y = sc.nextInt();
            h = sc.nextInt();
            xl = Math.min(xl, x - h);
            xr = Math.max(xr, x + h);
            yl = Math.min(yl, y - h);
            yr = Math.max(yr, y + h);
        }
        System.out.println((xl + xr) / 2 + " " + (yl + yr) / 2 + " " + (Math.max(xr - xl, yr - yl) + 2 - 1) / 2);
    }
}
