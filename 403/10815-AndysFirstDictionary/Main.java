import java.util.*;
import java.util.Scanner;

class Main{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        Set<String> set = new HashSet<String>();
        String str;
        while(sc.hasNext()) {
            str = sc.next().toLowerCase();
            str = str.replaceAll("[^a-z]", "\n");
            for (String string: str.split("\n")) {
                set.add(string);
            }
        }
        TreeSet sortedSet = new TreeSet<String>(set);
        Iterator it = sortedSet.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
