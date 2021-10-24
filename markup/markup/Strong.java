package markup;

import java.util.List;

public class Strong extends PlainText {
    // private final String spec = "__";

    public Strong(String s) {
        super(s, "__");
    }

    public Strong(List<PlainText> lst) {
        super(lst, "__");
    }

    public StringBuilder getSb() {
        return this.sb;
    }

}