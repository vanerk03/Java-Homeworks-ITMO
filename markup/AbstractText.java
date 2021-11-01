package markup;

import java.util.List;

public abstract class AbstractText {
    private StringBuilder sb;

    public AbstractText(String s, String sign) {
        this.sb = new StringBuilder(sign);
        sb.append(s);
        sb.append(sign);
    }

    public AbstractText(List<AbstractText> lst, String sign) {
        this.sb = new StringBuilder(sign);
        for (AbstractText elem : lst) {
            sb.append(elem.getSb());
        }
        sb.append(sign);
    }

    public StringBuilder getSb() {
        return sb;
    }
}
