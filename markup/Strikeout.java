package markup;

import java.util.List;

public class Strikeout extends PlainText {

    // private final String spec = "~";

    public Strikeout(String s) {
        super(s, "~");
    }

    public Strikeout(List<PlainText> lst) {
        super(lst, "~");
    }

    public StringBuilder getSb() {
        return this.sb;
    }

}
