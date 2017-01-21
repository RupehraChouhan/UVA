import java.util.*;

public class UglyNumber136 {
    static HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
    static int[] muliplyingWith = new int[3];
    static int[] afterMultiplying = new int[3];

    public static void main(String []args) {
        int count=0;
        hm.put(count++,1); int j=0; int k=0; int l=0;
        for(int i=1; hm.size()<=1500; i++) {
            muliplyingWith[0] = hm.get(j);
            muliplyingWith[1] = hm.get(k);
            muliplyingWith[2] = hm.get(l);
            afterMultiplying[0] = 2*muliplyingWith[0];
            afterMultiplying[1] = 3*muliplyingWith[1];
            afterMultiplying[2] = 5*muliplyingWith[2];
            int index=0; int min = afterMultiplying[index];
            for(int m=1; m<afterMultiplying.length; m++) {
                if(afterMultiplying[m] < min){
                    min = afterMultiplying[m];
                    index = m;
                }
            }
            if(!hm.containsValue(min))
                hm.put(count++, min);
            if(index==0)
                j++;
            else if(index== 1)
                k++;
            else
                l++;
        }
        System.out.println("The 1500'th ugly number is "+hm.get(1499)+".");
    }
}
