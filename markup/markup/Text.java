package markup;

import java.util.List;

public class Text extends PlainText {    

    public Text(String s) {
        super(s, "");
    }

    public Text(List<PlainText> lst) {
        super(lst, "");
    }

    public StringBuilder getSb() {
        return this.sb;
    }

}

// Emphasis, Strong, Strikeout
