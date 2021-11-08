import java.io.*;

public class FastScanner {

    public final int SIZE = 1024;
    public Reader reader;
    public char[] buffer = new char[SIZE];
    public int firstAvailable;
    public int end;

    public FastScanner(String str) {
        this.reader = new StringReader(str);
        readBuffer();
    }

    public FastScanner(String fileName, String encoding) {
        try {
            this.reader = new InputStreamReader(new FileInputStream(fileName), encoding);
            readBuffer();
        }

        catch (IOException e) {
            System.out.println("error occured" + e.getMessage());
        }
    }

    public FastScanner(InputStream in) {
        try {
            this.reader = new InputStreamReader(in, "utf8");
            readBuffer();
        } catch (IOException e) {
            System.out.println("error occured" + e.getMessage());
        }

    }

    public void readBuffer() {
        try {
            this.firstAvailable = 0;
            this.end = reader.read(buffer);
        } catch (IOException e) {
            System.out.println("error occured" + e.getMessage());
        }

    }

    public void closeSc() {
        try {
            reader.close();
        } catch (IOException e) {
            System.out.println("error occured" + e.getMessage());
        }
        
    }

    public boolean isSep(char chr) {
        return (chr == '\n' || chr == '\r');
    }

    public String nextWord() throws IOException {

        StringBuilder sb = new StringBuilder();
        int now = firstAvailable;

        while (end != -1) {
            firstAvailable = now + 1;
            if (now == SIZE) {
                readBuffer();
                now = 0;
                firstAvailable = now + 1;
            }
            if (now == end) {
                if (sb.length() == 0) {
                    closeSc();
                    return null;
                } else {
                    break;
                }
            }

            if ((Character.getType(buffer[now]) == Character.DASH_PUNCTUATION) || Character.isLetter(buffer[now])
                    || buffer[now] == '\'') {
                sb.append(buffer[now]);
            } else {
                break;
            }
            now++;
        }
        return sb.toString();
    }

    public int nextInt() {

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
        return Integer.valueOf(sb.toString());
    }

    public String nextLine() {

        StringBuilder sb = new StringBuilder();
        int now = firstAvailable;

        while (end != -1) {

            if (now == SIZE) {
                readBuffer();
                now = 0;
            }

            firstAvailable = now + 1;
            if (now == end) {
                if (sb.length() == 0) {
                    closeSc();
                    return null;
                } else {
                    break;
                }
            }

            if (buffer[now] == '\r') {
                if (now + 1 == SIZE) {
                    readBuffer();
                    now = 0;
                    if (buffer[now] == '\n') {
                        firstAvailable = 1;
                    }
                }

                else if (buffer[now + 1] == '\n') {
                    firstAvailable = now + 2;
                }
                break;

            } else if (isSep(buffer[now])) {
                break;

            } else {
                sb.append(buffer[now]);
            }
            now++;
        }
        return sb.toString();
    }
}