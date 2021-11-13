package markup;

import java.util.List;

public class Strong extends AbstractText {

    public Strong(List<HtmlMarkdown> lst) {
        super(lst, "<strong>", "</strong>", "__");
    }
}