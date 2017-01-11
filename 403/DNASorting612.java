package com.company;

import java.util.*;

public class DNASorting612 {
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        ArrayList<String> currInput;
        for(int i=0; i<t; i++) {
            sc.nextInt();
            int numOfWords = sc.nextInt();
            currInput = new ArrayList<>();
            for(int j=0; j<numOfWords; j++) {
                currInput.add(sc.next());
            }
            input.add(currInput);
        }
        for(int i=0; i<input.size(); i++) {
            dnaSorting(input.get(i));
            if(i!= input.size()-1)
                System.out.println();
        }
    }

    private static void dnaSorting(ArrayList<String> list) {
        ArrayList<String> copyOfList = new ArrayList<>();
        copyOfList = (ArrayList<String>) list.clone();
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm =countInversions(copyOfList);

        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            System.out.println(list.get((Integer) pair.getKey()));
        }
    }

    private static HashMap<Integer, Integer> countInversions(ArrayList<String> copyOfList) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i=0; i<copyOfList.size(); i++) {
            hm.put(i,insertionSort(copyOfList.get(i)));
        }
        hm = sortHashMapByValues(hm);
        return hm;
    }

    private static HashMap<Integer, Integer> sortHashMapByValues(HashMap<Integer, Integer> map) {
        Set<Map.Entry<Integer,Integer>> entries = map.entrySet();
        List<Map.Entry<Integer,Integer>> list = new LinkedList<Map.Entry<Integer,Integer>>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Integer,Integer>>() {

            @Override
            public int compare(Map.Entry<Integer, Integer> coordinate1,
                               Map.Entry<Integer, Integer> coordinate2) {
                return coordinate1.getValue().compareTo(coordinate2.getValue());
            }
        });
        // Storing the list into Linked HashMap to preserve the order of insertion.
        Map<Integer,Integer> aMap2 = new LinkedHashMap<>();
        for(Map.Entry<Integer,Integer> entry: list) {
            aMap2.put(entry.getKey(), entry.getValue());
        }
        return (HashMap<Integer, Integer>) aMap2;
    }

    private static Integer insertionSort(String str) {
        int count =0;
        char[] charArray = str.toCharArray();
        for(int i=0; i<charArray.length-1; i++) {
            if(charArray[i] > charArray[i+1]) {
                char temp = charArray[i];
                charArray[i] = charArray[i+1];
                charArray[i+1] =temp;
                count++;
                for(int j=i; j>0; j--) {
                    if(charArray[j] < charArray[j-1]) {
                        temp = charArray[j];
                        charArray[j] = charArray[j-1];
                        charArray[j-1] = temp;
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
