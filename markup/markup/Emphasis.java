package markup;

import java.util.List;

public class Emphasis extends PlainText {
    // private StringBuilder sb = new StringBuilder();
    public String spec = "*";

    public Emphasis(String s) {
        super(s, "*");
    }

    public Emphasis(List<PlainText> lst) {
        super(lst, "*");
    }

    public StringBuilder getSb() {
        return this.sb;
    }

}
