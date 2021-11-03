import java.io.*;

public class WordStatCount {

    static void sorting(int[] cnt, String[] words, int last) {
        int j = 0;
        for (int i = 0; i < last; i++) {
            j = i;
            while (j != 0 && cnt[j] < cnt[j - 1]) {
                int t = 0;
                String ts = "";
                t = cnt[j];
                cnt[j] = cnt[j - 1];
                cnt[j - 1] = t;
                ts = words[j];
                words[j] = words[j - 1];
                words[j - 1] = ts;
                j--;
            }
        }
    }

    static int getHash(String s) {

        int MOD = 1000000123;
        int p = 1499;
        long pow = 1;
        long hash = 0;

        for (int i = 0; i < s.length(); i++) {
            int chr = s.charAt(i);
            hash = (hash + (chr * pow) % MOD) % MOD;
            pow = pow * p % MOD;
        }
        return (int) hash;
    }

    static String[] newStrArray(String[] old) {

        int n = old.length;
        String[] lst = new String[2 * n];

        for (int i = 0; i < n; i++) {
            lst[i] = old[i];
        }

        return lst;
    }

    static BufferedReader wrapReader(String fileName) throws FileNotFoundException, UnsupportedEncodingException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "utf8"));
        return reader;
    }

    static BufferedWriter wrapWriter(String fileName) throws FileNotFoundException, UnsupportedEncodingException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf8"));
        return writer;
    }

    static boolean isGoodChar(char chr) {
        return ((Character.getType(chr) == Character.DASH_PUNCTUATION) || (Character.isLetter(chr)) || (chr == '\''));
    }

    static String[] readAndFindWords(String inputFile) {

        String[] array = new String[1];
        int last = 0;
        try {
            BufferedReader reader = wrapReader(inputFile);

            try {
                while (true) {

                    String s = reader.readLine();

                    if (s == null) {
                        break;
                    } else {
                        s = s + " ";
                    }

                    int start = 0;

                    for (int i = 0; i < s.length(); i++) {

                        char chr = s.charAt(i);

                        if (!isGoodChar(chr)) {

                            if (!s.substring(start, i).isEmpty()) {

                                if (last == array.length) {
                                    array = newStrArray(array);
                                }

                                String word = s.substring(start, i).toLowerCase();
                                array[last++] = word;
                            }

                            start = i + 1;
                        }
                    }
                }
            } finally {
                reader.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("File " + inputFile + " is not found, make sure u have created it!");

        } catch (UnsupportedEncodingException e) {
            System.out.println("File " + inputFile + " contains symbols that can't be read by the system!");

        } catch (IOException e) {
            System.out.println("Another string type was expected");
        }

        return array;

    }

    static int[] putHashes(String[] array, int len) {

        int[] hashes = new int[len];

        for (int i = 0; i < len; i++) {
            hashes[i] = getHash(array[i]);
        }
        return hashes;
    }

    static void writeWords(String[] array, String fileName, int len) {

        int[] hashes = putHashes(array, len);

        int last = 0;

        int[] count = new int[len];
        String[] noRepeat = new String[len];

        try {
            BufferedWriter writer = wrapWriter(fileName);

            try {
                for (int i = 0; i < len; i++) {

                    Boolean used = false;

                    for (int j = 0; j < i; j++) {

                        if (hashes[i] == hashes[j]) {
                            used = true;
                            break;
                        }
                    }
                    if (!used) {
                        int cnt = 0;

                        for (int j = i; j < len; j++) {
                            if (hashes[i] == hashes[j]) {
                                cnt++;
                            }
                        }
                        String word = array[i];
                        noRepeat[last] = word;
                        count[last] = cnt;
                        last += 1;

                    }
                }

                sorting(count, noRepeat, last);
                for (int i = 0; i < last; i++) {
                    writer.write(noRepeat[i] + " " + count[i] + "\n");
                }
            } finally {
                writer.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " is not found, make sure u have created it!");

        } catch (UnsupportedEncodingException e) {
            System.out.println("File " + fileName + " contains symbols that can't be read by the system!");

        } catch (IOException e) {
            System.out.println("Another string type was expected");
        }

    }

    // length of not-null String-type array
    static int len(String[] array) {

        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                return (i);
            }
        }
        return array.length;
    }

    public static void main(String[] args) {
        String[] array = readAndFindWords(args[0]);
        int ln = len(array);

        writeWords(array, args[1], ln);
    }
}