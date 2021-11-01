package markup;

import java.util.List;

public class Strikeout extends AbstractText {
       
    public Strikeout(List<AbstractText> lst) {
        super(lst, "~");
    }
}
