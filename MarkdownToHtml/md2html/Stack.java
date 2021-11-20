package md2html;

public class Stack {

    private int[] st = new int[1000];
    private int[] start = new int[1000];
    private int now = 0;
    
    public boolean check(int x) {
        return st[now - 1] == x;
    }
    
    public int topStart() {
        return start[now - 1];
    }

    public void put(int x, int s) {
        st[now] = x;
        start[now++] = s;
    }

    public void pop() {
        --now;
    }

    public int len() {
        return now;
    }

    
}
