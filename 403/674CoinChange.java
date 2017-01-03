import java.util.ArrayList;
import java.util.Scanner;

public class 674CoinChange {
    public static void main(String []args) {
        Scanner sc = new Scanner((System.in));
        ArrayList<Integer> input = new ArrayList<Integer>();
        while(sc.hasNext())
            input.add(sc.nextInt());
        for (int i = 0; i < input.size(); i++) {
            System.out.println(numberOfWays(input.get(i)));
        }
    }

    private static Integer numberOfWays(Integer total) {
        int[][] arr = new int[5][total+1];
        for (int i = 0; i <total+1 ; i++)
            arr[0][i] = 1;
        for (int i = 0; i <5 ; i++)
            arr[i][0] = 1;
        int[] coins = {1,5,10,25,50};
        for(int i=1; i<5; i++) {
            int coin = coins[i];
            for(int j=1; j<total+1; j++) {
                if(j<coin)
                    arr[i][j] = arr[i-1][j];
                else
                    arr[i][j] = arr[i-1][j] + arr[i][j-coin];
            }
        }
        return arr[4][total];
    }
}
