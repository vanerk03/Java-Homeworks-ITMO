package markup;

import java.util.List;

public class Paragraph extends AbstractText {

    public Paragraph(List<AbstractText> lst) {
        super(lst, "");
    }
    
    public void toMarkdown(StringBuilder sb_param) {
        sb_param.append(getSb());
    }
    
    @Override
    public String toString() {
        return getSb().toString();
    }
}