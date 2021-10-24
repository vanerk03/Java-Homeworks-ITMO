package markup;

import java.util.List;

public class Paragraph {
    
    public StringBuilder sb;

    public Paragraph(List<PlainText> lst) {
    
        this.sb = new StringBuilder();
        
        for (PlainText elem: lst) {
            sb.append(elem.getSb());
        }
    }
    
    public void toMarkdown(StringBuilder sb_param) {
        sb_param.append(sb);
    }
    
    @Override
    public String toString() {
        return sb.toString();
    }
}