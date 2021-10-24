package markup;

import java.util.List;

public class PlainText {
    public StringBuilder sb;

    public PlainText(String s, String sign) {
        this.sb = new StringBuilder(sign);
        sb.append(s);
        sb.append(sign);
    }

    public PlainText(List<PlainText> lst, String sign) {
        this.sb = new StringBuilder(sign);
        for (PlainText elem : lst) {
            sb.append(elem.getSb());
        }
        sb.append(sign);
    }

    public StringBuilder getSb() {
        return sb;
    }
}
