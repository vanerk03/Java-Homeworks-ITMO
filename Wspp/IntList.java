public class IntList {

    public int size;
    public int[] array;
    public int[] pos;

    public IntList() {
        this.size = 0;
        this.array = new int[10];
        this.pos = new int[10];
    }

    public void append(int x, int ps) {
        if (this.size == array.length) {
            extend();
            
        }
        this.pos[size] = ps;    
        this.array[size++] = x;
    }

    public int getSize() {
        return size;
    }

    public void extend() {

        int n = array.length;
        int[] newArray = new int[n * 2];
        int[] newPos = new int[n * 2];

        for (int i = 0; i < array.length; i++) {
            newArray[i] = array[i];
            newPos[i] = pos[i];
        }
        this.pos = newPos;
        this.array = newArray;
    }

    public int[] getArray() {
        return this.array;
    }

    public int[] getPos() {
        return this.pos;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0;  i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) {
                sb.append(" ");
            } else {
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
