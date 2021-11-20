package md2html;

import java.io.*;

public class Parser {

    private String inputFileName;
    private String outputFileName;

    public Parser(String inputFileName, String outputFileName) {
        this.inputFileName = inputFileName;
        this.outputFileName = outputFileName;
    }

    private int checkHeader(String s) {

        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '#') {
                cnt++;
            } else {
                if (s.charAt(i) == ' ') {
                    break;
                } else {
                    cnt = 0;
                    break;
                }
            }
        }
        return cnt;
    }

    private String specialSymbol(char chr) {

        char[] spec = { '<', '>', '&' };
        String[] value = { "&lt;", "&gt;", "&amp;" };

        for (int i = 0; i < 3; i++) {
            if (chr == spec[i]) {
                return value[i];
            }
        }
        return Character.toString(chr);
    }

    private boolean checkSymbol(String s, char symbol) {
        int cnt = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == symbol && i != 0 && s.charAt(i - 1) == '\\') {
                continue;
            }
            if (s.charAt(i) == symbol) {
                cnt++;
            }
        }
        return (cnt % 2 == 1);
    }

    private String parseString(String s, boolean canBeHeader, boolean underscore, boolean asterix) {

        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        if (canBeHeader) {

            int cnt = checkHeader(s);

            if (cnt > 0) {
                sb.append("<h" + cnt + ">" + parseString(s.substring(cnt + 1), false, underscore, asterix) + "</h" + cnt
                        + ">");
            } else {
                sb.append("<p>" + parseString(s, false, underscore, asterix) + "</p>");
            }
            return sb.toString();
        }

        String[] md = { "__", "**", "--", "''", "*", "`", "_"};
        String[] html = { "strong>", "strong>", "s>", "q>", "em>", "code>", "em>" };

        Stack st = new Stack();

        int i = 0;
        int n = s.length();

        while (i < n) {

            boolean flag = false;

            if (i != n - 1 && s.charAt(i) == '\\') {
                tmp.append(s.charAt(i + 1));
                i += 2;
                continue;
            }

            for (int t = 0; t < 7; t++) {

                boolean found = true;
                String elem = md[t];
                int ln = elem.length();

                if (ln == 2 && i == n - 1) {
                    continue;
                }

                for (int j = 0; j < ln; j++) {
                    if (s.charAt(i + j) != elem.charAt(j)) {
                        found = false;
                        break;
                    }
                }

                if (found) {
                    if (t == 4 && asterix || t == 6 && underscore) {
                        flag = false;
                        break;
                    }
                    flag = true;
                    i += ln;

                    if (st.len() > 0) {
                        if (st.check(t)) {
                            tmp = new StringBuilder("<" + html[t]
                                    + parseString(s.substring(st.topStart(), i - ln), false, underscore, asterix) + "</"
                                    + html[t]);
                            st.pop();
                        } else {
                            st.put(t, i);
                        }
                    } else {
                        st.put(t, i);
                        sb.append(tmp);
                        tmp.setLength(0);
                    }
                    break;
                }
            }

            if (!flag) {
                tmp.append(specialSymbol(s.charAt(i)));
                i++;
            }
        }

        if (!tmp.isEmpty()) {
            sb.append(tmp);
        }

        return sb.toString();
    }

    public void toHtml() {

        StringBuilder sb = new StringBuilder();

        try {

            Scanner sc = new Scanner(inputFileName, "utf8");
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(new FileOutputStream(outputFileName), "utf8"));

            String str = "";

            while ((str = sc.nextLine()) != null) {

                if (str.isEmpty()) {
                    if (sb.length() != 0) {
                        sb.deleteCharAt(sb.length() - 1);
                        String par = sb.toString();
                        writer.write(parseString(par, true, checkSymbol(par, '*'), checkSymbol(par, '_')) + "\n");
                        sb.setLength(0);
                    }

                } else {
                    sb.append(str + '\n');
                }
            }

            if (!sb.isEmpty()) {
                sb.deleteCharAt(sb.length() - 1);
                String s = sb.toString();

                writer.write(parseString(s, true, checkSymbol(s, '*'), checkSymbol(s, '_')));
            }

            writer.close();
            sc.closeSc();

        } catch (IOException e) {
            System.out.println("error occured");
            System.out.println(e.getMessage());
        }
    }
}