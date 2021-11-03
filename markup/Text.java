package markup;

public class Text extends AbstractText {    

    public Text(String str) {
        super(str);
    }

    @Override
    public void toMarkdown(StringBuilder sb) {
        sb.append(this.s);
    }

    @Override
    public void toHtml(StringBuilder sb) {
        sb.append(this.s);
    }
}

