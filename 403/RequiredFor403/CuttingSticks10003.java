import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CuttingSticks10003 {
    static int[][] memo;
    static ArrayList<Integer> cuts;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int len = sc.nextInt();
        while (len != 0) {
            int numOfCuts = sc.nextInt();
            cuts = new ArrayList<>();
            cuts.add(0);
            for (int i = 0; i < numOfCuts; i++) {
                cuts.add(sc.nextInt());
            }
            cuts.add(len);
            System.out.println("The minimum cutting is "+calculateCost(len, cuts)+".");
            len = sc.nextInt();
        }
    }

    private static int calculateCost(int len, ArrayList<Integer> cuts) {
        memo = new int[len+1][len+1];
        for( int i=0; i<memo.length; i++) {
            int[] row = memo[i];
            Arrays.fill(row, -1);
        }
        return rundp(0, len);
    }

    private static int rundp(int left, int right) {
        if (right - left<=1) return memo[left][right]=0;
        if(memo[left][right] != -1) return memo[left][right];
        int maxCut =  Integer.MAX_VALUE;
        for (int i = 0; i < cuts.size(); i++) {
            if(cuts.get(i) > left && cuts.get(i) < right) {
                maxCut = Math.min(maxCut, (right - left) +
                        rundp(left, cuts.get(i)) + rundp(cuts.get(i), right));
                memo[left][right] =maxCut;
            }
        }

        return memo[left][right]==-1? 0 : memo[left][right];
    }
}
