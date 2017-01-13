import java.util.*;

import static java.lang.System.*;

public class PhoneNumbers755 {
    public static void main(String []args) {
        Scanner sc = new Scanner(in);
        ArrayList<String> nums ;
        HashMap<Character, Integer> hm = new HashMap<>();
        hm.put('1', 1); hm.put('0', 0);
        hm.put('A', 2); hm.put('B', 2); hm.put('C', 2); hm.put('2', 2);
        hm.put('D', 3); hm.put('E', 3); hm.put('F', 3); hm.put('3', 3);
        hm.put('G', 4); hm.put('H', 4); hm.put('I', 4); hm.put('4', 4);
        hm.put('J', 5); hm.put('K', 5); hm.put('L', 5); hm.put('5', 5);
        hm.put('M', 6); hm.put('N', 6); hm.put('O', 6); hm.put('6', 6);
        hm.put('P', 7); hm.put('R', 7); hm.put('S', 7); hm.put('7', 7);
        hm.put('T', 8); hm.put('U', 8); hm.put('V', 8); hm.put('8', 8);
        hm.put('W', 9); hm.put('X', 9); hm.put('Y', 9); hm.put('9', 9);
        int t = sc.nextInt();
        for(int i=0; i<t; i++) {
            int numOfDatasets = sc.nextInt();
            nums = new ArrayList<>();
            for(int j=0; j<numOfDatasets; j++) {
                nums.add(sc.next());
            }
            if (duplicates(nums, hm)==false)
                out.println("No duplicates.");
            if(i<t-1)
                out.println();
        }
    }

    private static boolean duplicates(ArrayList<String> nums, HashMap<Character, Integer> hm) {
        HashMap<String, Integer> directory = new HashMap<>();
        for(int i=0; i<nums.size(); i++) {
            String currNum = nums.get(i);
            StringBuilder str = new StringBuilder();
            int dashIndex=0;
            for(int j=0; j<currNum.length(); j++) {
                if(currNum.charAt(j)=='-')
                    continue;
                else
                    str.append(hm.get(currNum.charAt(j)));
                dashIndex++;
                if(dashIndex==3)
                    str.append("-");
            }
            String numString = str.toString();
            if(directory.containsKey(numString)) {
                int count = directory.get(numString);
                directory.put(numString,++count);
            } else {
                directory.put(numString, 1);
            }
        }
        Map<String, Integer> map = new TreeMap<>(directory);
        int countOfRepeatingNums =0;
        for(Map.Entry<String,Integer> entry : map.entrySet()) {
            if(entry.getValue() > 1) {
                countOfRepeatingNums++;
                System.out.println(entry.getKey()+ " "+ entry.getValue());
            }
        }
        return map.size() == map.size()-countOfRepeatingNums? false : true;
    }
}
