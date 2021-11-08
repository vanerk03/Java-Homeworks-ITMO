import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import java.io.*;

public class H {

    static class Scanner {

        public final int SIZE = 1024;
        public Reader reader;
        public char[] buffer = new char[SIZE];
        public int firstAvailable;
        public int end;

        public Scanner(String str) throws IOException {
            this.reader = new StringReader(str);
            readBuffer();
        }

        public Scanner(String fileName, String encoding)
                throws UnsupportedEncodingException, FileNotFoundException, IOException {
            this.reader = new InputStreamReader(new FileInputStream(fileName), encoding);
            readBuffer();
        }

        public Scanner(InputStream in) throws UnsupportedEncodingException, IOException {
            this.reader = new InputStreamReader(in, "utf8");
            readBuffer();
        }

        public void readBuffer() throws IOException {
            this.firstAvailable = 0;
            this.end = reader.read(buffer);
        }

        public void closeSc() throws IOException {
            reader.close();
        }

        public boolean isSep(char chr) {
            return (chr == '\n' || chr == '\r');
        }

        public int nextInt() throws IOException {

            StringBuilder sb = new StringBuilder();
            int i = firstAvailable;

            while (true) {

                firstAvailable = i + 1;

                if (i >= end) {
                    if (end == SIZE) {
                        readBuffer();
                        i = 0;
                        firstAvailable = 1;
                    } else {
                        break;
                    }
                }

                if (Character.isWhitespace(buffer[i]) || isSep(buffer[i])) {
                    if (sb.length() != 0) {
                        break;
                    }
                } else if (end != -1) {
                    sb.append(buffer[i]);
                }
                i++;
            }

            if (sb.length() == 0) {
                return 0;
            } else {
                return (Integer.valueOf(sb.toString()));
            }
        }
    }

    public static int fnc(int l, int r, int[] p) {
        if (l != 0) {
            return p[r] - p[l - 1];
        } else {
            return p[r];
        }
    }

    public static void main(String[] args) {

        try {

            Scanner sc = new Scanner(System.in);

            Map<Integer, Integer> mp = new HashMap<>();

            int n = sc.nextInt();
            int[] a = new int[n];
            int[] p = new int[n];
            int mx = 0;

            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
                mx = Math.max(a[i], mx);
            }

            for (int i = 0; i < n; i++) {
                p[i] = a[i];
                if (i != 0) {
                    p[i] = p[i] + p[i - 1];
                }
            }

            int q = sc.nextInt();

            while (q != 0) {

                q -= 1;

                int t = sc.nextInt();

                if (mp.containsKey(t)) {
                    System.out.println(mp.get(t));
                    continue;

                } else if (t < mx) {
                    System.out.println("Impossible");
                    continue;

                } else {
                    int i = 0;
                    int res = 0;

                    while (i < n) {
                        int l = i, r = n;
                        while (l < r - 1) {

                            int m = (l + r) / 2;
                            int tmp = fnc(i, m, p);

                            if (tmp <= t) {
                                l = m;
                            } else {
                                r = m;
                            }
                        }
                        i = r;
                        res++;
                    }
                    mp.put(t, res);
                    System.out.println(res);
                }

            }
        } catch (Exception e) {

        }
    }

}
