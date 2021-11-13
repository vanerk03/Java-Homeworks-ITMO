package markup;

import java.util.List;

public class Emphasis extends AbstractText {
    
    public Emphasis(List<HtmlMarkdown> lst) {
        super(lst, "<em>", "</em>", "*");
    }
}
