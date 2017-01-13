import java.util.ArrayList;
import java.util.Scanner;

public class CoinChange674 {
  public static void main(String []args) {
      Scanner sc = new Scanner((System.in));
      ArrayList<Integer> input = new ArrayList<Integer>();
      while(sc.hasNext())
          input.add(sc.nextInt());
      int[][] arr = numberOfWays();
      for (int i = 0; i < input.size(); i++) {
          System.out.println(arr[4][input.get(i)]);
      }
  }

  private static int[][] numberOfWays() {
      int[][] arr = new int[5][7490];
      for (int i = 0; i <7490 ; i++)
          arr[0][i] = 1;
      for (int i = 0; i <5 ; i++)
          arr[i][0] = 1;
      int[] coins = {1,5,10,25,50};
      for(int i=1; i<5; i++) {
          int coin = coins[i];
          for(int j=1; j<7490; j++) {
              if(j<coin)
                  arr[i][j] = arr[i-1][j];
              else
                  arr[i][j] = arr[i-1][j] + arr[i][j-coin];
          }
      }
      return arr;
  }
}
