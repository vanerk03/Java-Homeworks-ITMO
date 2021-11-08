import java.util.Scanner;

public class A {
    public static void main(String[] args) {
        int a;
        int b;
        int n;
        Scanner sc = new Scanner(System.in);
        a = sc.nextInt();
        b = sc.nextInt();
        n = sc.nextInt();
        sc.close();
        System.out.println(2 * ((n - a - 1) / (b - a)) + 1);
        
    }
}
