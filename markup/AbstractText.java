package markup;

import java.util.List;

public abstract class AbstractText {

    private String start = "";
    private String end = "";
    private String sign = "";

    protected String s;
    protected List<AbstractText> lst;

    public AbstractText(String str) {
        this.s = str;
    }

    public AbstractText(List<AbstractText> lst, String start, String end, String sign) {
        this.lst = lst;
        this.start = start;
        this.end = end;
        this.sign = sign;
    }

    public void toMarkdown(StringBuilder sb) {
        sb.append(sign);
        for (AbstractText elem : lst) {
            elem.toMarkdown(sb);
        }
        sb.append(sign);
    }

    public void toHtml(StringBuilder sb) {
        sb.append(start);
        for (AbstractText elem : lst) {
            elem.toHtml(sb);
            
        }
        sb.append(end);
    }
}
