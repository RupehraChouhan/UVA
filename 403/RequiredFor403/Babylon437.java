import java.util.*;

public class Babylon437 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int numOfDifferentBoxes = sc.nextInt();
        ArrayList<ArrayList<Integer>> allBoxes;
        ArrayList<Integer> box; int caseNum=1;
        while (numOfDifferentBoxes != 0) {
            allBoxes = new ArrayList<>();
            for (int i = 0; i < numOfDifferentBoxes; i++) {
                box = new ArrayList<>();
                box.add(sc.nextInt());
                box.add(sc.nextInt());
                box.add(sc.nextInt());
                Collections.sort(box, Collections.reverseOrder());
                allBoxes.add(box);
            }
            allBoxes = allPermutationsofBoxes(allBoxes);
            allBoxes = sortBoxes(allBoxes);
            int[] heightOfTower = new int[allBoxes.size()];
            for(int i=0; i<heightOfTower.length; i++) {
                heightOfTower[i] = allBoxes.get(i).get(2);
            }
            int[] originalHeights = heightOfTower.clone();
            for(int i=1; i<heightOfTower.length; i++) {
                for(int j=0; j<i; j++) {
                    boolean ans = compareTwoBoxes(allBoxes.get(j),allBoxes.get(i));
                    heightOfTower[i] = ans == true? Math.max(heightOfTower[j]+originalHeights[i], heightOfTower[i])
                            : heightOfTower[i] ;
                }
            }
            int max = heightOfTower[0];
            int index =0;
            for(int i=1; i<heightOfTower.length; i++) {
                if(heightOfTower[i]>max) {
                    max = heightOfTower[i];
                    index=i;
                }
            }
            System.out.println("Case "+caseNum+": maximum height = " +max);
            caseNum++;
            numOfDifferentBoxes = sc.nextInt();
        }
    }

    private static boolean compareTwoBoxes(ArrayList<Integer> boxOne, ArrayList<Integer> boxTwo) {
        for(int i=0; i<2; i++) {
            if(boxOne.get(i) >= boxTwo.get(i))
                return false;
        }
        return true;
    }

    private static ArrayList<ArrayList<Integer>> sortBoxes(ArrayList<ArrayList<Integer>> allBoxes) {
        ArrayList<ArrayList<Integer>> sortedBoxes = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int count=0;
        for(int i=0; i<allBoxes.size(); i++) {
            int area = allBoxes.get(i).get(0)* allBoxes.get(i).get(1);
            map.put(count++, area);
        }
        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        List<Map.Entry<Integer, Integer>> list = new LinkedList<Map.Entry<Integer,Integer>>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {

            @Override
            public int compare(Map.Entry<Integer, Integer> coordinate1,
                               Map.Entry<Integer, Integer> coordinate2) {
                return coordinate1.getValue().compareTo(coordinate2.getValue());
            }
        });
        // Storing the list into Linked HashMap to preserve the order of insertion.
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();
        for(Map.Entry<Integer, Integer> entry: list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        for (Integer key : sortedMap.keySet()) {
            sortedBoxes.add(allBoxes.get(key));
        }
        return sortedBoxes;
    }

    private static ArrayList<ArrayList<Integer>> allPermutationsofBoxes(ArrayList<ArrayList<Integer>> boxes) {
        ArrayList<ArrayList<Integer>> perms = (ArrayList<ArrayList<Integer>>) boxes.clone();
        ArrayList<Integer> box;
        for (int i = 0; i < boxes.size(); i++) {
            ArrayList<Integer> currentBox = boxes.get(i);
            if(currentBox.get(0)!=currentBox.get(1)) {
                box=new ArrayList<>();
                box.add(currentBox.get(1));
                box.add(currentBox.get(2));
                box.add(currentBox.get(0));
                perms.add(box);
            }
            if(currentBox.get(1)!=currentBox.get(2)) {
                box = new ArrayList<>();
                box.add(currentBox.get(0));
                box.add(currentBox.get(2));
                box.add(currentBox.get(1));
                perms.add(box);
            }
        }
        return perms;
    }
}
