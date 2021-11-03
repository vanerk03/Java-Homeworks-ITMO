import java.io.*;

public class Scanner {

    public final int SIZE = 512;
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
        return (chr == '\n' || chr == '\r' || chr == '\u2028' || chr == '\u2029' || chr == '\u0085');
    }

    public String nextWord() throws IOException {

        StringBuilder sb = new StringBuilder();
        int now = firstAvailable;
        if (now > end) {
            return null;
        }
        while (end != -1) {

            firstAvailable = now + 1;
            if (now == SIZE) {
                readBuffer();
                now = 0;
                firstAvailable = now + 1;
            }
            if (now >= end) {
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
                if (sb.length() != 0) {
                    break;
                }
            }
            now++;
        }
        return sb.toString().toLowerCase();
    }

    public String nextLine() throws IOException {

        StringBuilder sb = new StringBuilder();
        int now = firstAvailable;

        while (end != -1) {

            if (now == SIZE) {
                readBuffer();
                now = 0;
            }

            firstAvailable = now + 1;
            if (now >= end) {
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
        if (end == -1 && sb.length() == 0) {
            return null;
        }
        return sb.toString().toLowerCase();
    }
}