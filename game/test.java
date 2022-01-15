package game;

public class test {
    public static void main(String[] args) {
        
        int n = 20;
        int m = 20;

        int lenHor = Integer.toString(m - 1).length();
        int lenVer = Integer.toString(n - 1).length();

        StringBuilder hor = new StringBuilder();
        StringBuilder ver = new StringBuilder();
        
        for(int i = 0; i < lenHor; i++) {
            hor.append(" ");
        }

        for(int i = 0; i < lenVer; i++) {
            ver.append("\n");
        }
        
        StringBuilder sb = new StringBuilder(hor.toString() + " ");

        for (int i = 0; i < m; i++) {
            sb.append(i + hor.toString().substring(0, hor.length() - Integer.toString(i).length() + 1));
        }
        
        for (int r = 0; r < n; r++) {
            sb.append(ver);
            sb.append(r + hor.toString().substring(0, hor.length() - Integer.toString(r).length() + 1));
            for (int c = 0; c < m; c++) {
                sb.append('.').append(hor.toString());
            }
        }
        System.out.println(sb.toString());
    }
}
