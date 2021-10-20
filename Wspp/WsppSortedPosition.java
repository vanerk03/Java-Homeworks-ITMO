import java.util.*;
import java.io.*;

public class WsppSortedPosition {

    static BufferedWriter wrapWriter(String fileName) throws FileNotFoundException, UnsupportedEncodingException {

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf8"));
        return writer;
    }

    public static void main(String[] args) {

        try {

            int now = 1;
            int num = 1;
            Scanner sc = new Scanner(args[0], "utf8");
            
            try (BufferedWriter writer = wrapWriter(args[1])) {
                try {

                    TreeMap<String, IntList> treeMap = new TreeMap<>();

                    while (true) {
                        now = 1;
                        String line = sc.nextLine();
                        if (line == null) {
                            break;
                        }

                        Scanner sc1 = new Scanner(line);
                        String str = sc1.nextWord();

                        while (true) {

                            if (str == null) {
                                break;
                            }

                            if (!treeMap.containsKey(str)) {
                                IntList lst = new IntList();
                                lst.append(now++, num);
                                treeMap.put(str, lst);
                            } else {
                                treeMap.get(str).append(now++, num);
                            }
                            str = sc1.nextWord();
                        }
                        num++;
                    }
                    
                    for (String key : treeMap.keySet()) {
                        
                        IntList lst = treeMap.get(key);
                        int sz = lst.getSize();
                        
                        writer.write(key + " " + sz + " ");
                        int[] temp = lst.getArray();
                        int[] pos = lst.getPos();

                        for (int i = 0; i < sz; i++) {
        
                            if (i != sz - 1) {
                                writer.write(pos[i] + ":" + temp[i] + " ");
                            } else {
                                writer.write(pos[i] + ":" + temp[i] + "\n");
                            }
                        }

                    }
                } catch (IOException e) {
                    System.out.println("IOExpression occured");
                    System.out.println(e.getMessage());
                }
            } catch (FileNotFoundException e) {
                System.out.println("file " + args[1] + " is not found");
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("this encoding is not support");
        } catch (FileNotFoundException e) {
            System.out.println("file " + args[0] + " is not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
