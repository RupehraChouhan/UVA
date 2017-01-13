import java.util.ArrayList;
import java.util.Scanner;

public class CountSwaps11330 {

    private static int countSwaps(int[] arr, ArrayList<Integer> list) {
        int swaps = 0;
        for(int i=0; i<arr.length-1; i++) {
            if(list.get(i)!=list.get(i+1)) {
                int elementi = list.get(i);
                int elementiplus1 = list.get(i+1);
                int indexi = list.lastIndexOf(elementi);
                list.set(i+1, elementi ) ;
                list.set(indexi, elementiplus1);
                swaps++;
                i++;
            } else
                i++;
        }
        return swaps;
    }

    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[][] allInput = new int[t][];
        int[] input;
        ArrayList<ArrayList<Integer>> allInputList = new ArrayList<>();
        ArrayList<Integer> inputList;
        for(int i=0; i<t; i++){
            int numOfPairs = sc.nextInt();
            input = new int[numOfPairs*2];
            inputList = new ArrayList<>();
            for(int j=0; j<numOfPairs*2; j++) {
                input[j] = sc.nextInt();
                inputList.add(input[j]);
            }
            allInput[i] = input;
            allInputList.add(inputList);
        }
        for(int i=0; i<t; i++) {
            int numOfSwaps = countSwaps(allInput[i],allInputList.get(i));
            System.out.println(numOfSwaps);
        }
    }
}
