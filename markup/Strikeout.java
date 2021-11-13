package markup;

import java.util.List;

public class Strikeout extends AbstractText {

    public Strikeout(List<HtmlMarkdown> lst) {
        super(lst, "<s>", "</s>", "~");
    }
}
