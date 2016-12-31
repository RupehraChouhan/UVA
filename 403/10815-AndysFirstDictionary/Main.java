import java.util.*;
import java.util.Scanner;

class Main{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        Set<String> set = new HashSet<String>();
        String str;
        while(sc.hasNext()) {
            str = sc.next().toLowerCase();
            str = str.replaceAll("[^a-z]", " ");
            for (String string: str.split(" ")) {
                if(!string.equals(" "))
                    set.add(string);
            }
        }
        TreeSet sortedSet = new TreeSet<String>(set);
        sortedSet.remove("");
        Iterator it = sortedSet.iterator();
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
