import java.util.ArrayList;
import java.util.Scanner;

class FlipSort10327{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> input = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> testCase = new ArrayList<Integer>();
        int num;
        while(sc.hasNext()) {
            num = sc.nextInt();
            testCase = new ArrayList<Integer>();
            for(int i=0;i<num; i++) {
                testCase.add(sc.nextInt());
            }
            input.add(testCase);
        }
        for(int i=0; i<input.size();i++) {
            int numOfFlips = flipSort(input.get(i));
            System.out.println("Minimum exchange operations : "+ numOfFlips);
        }
    }

    private static int flipSort(ArrayList<Integer> arr) {
        int flips=0;
        for(int i=0; i<arr.size()-1; i++) {
            if(arr.get(i) > arr.get(i+1)) {
                int temp = arr.get(i);
                arr.set(i, arr.get(i+1));
                arr.set(i+1,temp);
                flips++;
                for(int j=i; j>0;j--) {
                    if(arr.get(j) < arr.get(j-1)) {
                        temp = arr.get(j);
                        arr.set(j, arr.get(j-1));
                        arr.set(j-1, temp);
                        flips++;
                    }
                }
            }
        }
        return flips;
    }
}
