import java.util.Scanner;

public class J{
    public static void main(String[] args) {
        
        int n;
        
        Scanner sc = new Scanner(System.in);
        
        n = sc.nextInt();

        int[][] array = new int[n][n];
        int[][] ans = new int[n][n];

        for(int i = 0; i < n; i++) {
            String s = sc.next();
            for(int j = 0; j < n; j++) {
                array[i][j] = (int)(s.charAt(j) - '0');
            }
        }
        
        for(int i = 0; i < n; i++) {
            
            for(int j = 0; j < n; j++) {

                if (array[i][j] != 0) {
                    ans[i][j] = 1; 
                    for(int k = j + 1; k < n; k++) {
                        array[i][k] = array[i][k] - array[j][k] + 10;
                        if (array[i][k] >= 10) {
                            array[i][k] -= 10;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                
                System.out.print(ans[i][j]);
                
            }
            System.out.println();
        }
        sc.close();
    }
}