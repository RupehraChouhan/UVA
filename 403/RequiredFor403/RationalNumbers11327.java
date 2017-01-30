import java.math.BigInteger;
import java.util.*;

public class RationalNumbers11327 {
    static int[] eulerphi = new int[200001];

    public static void main(String []args) {
        //from competitive programming
        for(int i=1; i<=200000; i++) eulerphi[i] = i;
        for(int i=2; i<=200000; i++){
            if(eulerphi[i] == i) {
                for(int j=i; j<=200000; j+=i)
                    eulerphi[j] = (eulerphi[j] / i) * (i-1);
            }
        }
        eulerphi[1] = 2;
        Scanner sc = new Scanner(System.in);
        BigInteger n = sc.nextBigInteger();
        BigInteger den =BigInteger.ZERO;
        BigInteger prevTotal = den;
        int i;
        while(n.compareTo(BigInteger.ZERO)!= 0) {
            for(i=1; den.compareTo(n) == -1; i++) {
                prevTotal = den;
                den = den.add(BigInteger.valueOf(eulerphi[i]));
            }
            int denominator = --i;
            boolean found = false;
            for(BigInteger j = prevTotal; j.compareTo(n)== -1;) {
                if(!found) {
                    for (i = 0; i <=denominator; i++) {
                        if (GCD(denominator,i) == 1)
                            j = j.add(BigInteger.ONE);
                        if (j.compareTo(n) == 0) {
                            System.out.println(i + "/" + denominator);
                            found = true;
                            break;
                        }
                    }
                }
                else
                    break;
            }

            n = sc.nextBigInteger();
            den =BigInteger.ZERO;
        }
    }

    private static int GCD(int num, int i) {
        if(i==0 && num==1) return 1;
        if(i==0) return num;
        int temp;
        if(num<i){
            temp=num;
            num=i;
            i = temp;
        }
        if(num%i==0)
            return i;
        return GCD(num%i, i);
    }

}
