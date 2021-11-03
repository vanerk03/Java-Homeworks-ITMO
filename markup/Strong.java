package markup;

import java.util.List;

public class Strong extends AbstractText {

    public Strong(List<AbstractText> lst) {
        super(lst, "<strong>", "</strong>", "__");
    }
}