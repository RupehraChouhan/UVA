import java.math.BigInteger;
import java.util.*;

/**
 * Created by Rupehra on 2017-08-08.
 */
public class uva_787 {
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        BigInteger n;
        BigInteger prod;
        BigInteger maxProd;
        while(sc.hasNextBigInteger()) {
            ArrayList<BigInteger> arr = new ArrayList<>();
            n = sc.nextBigInteger();
            arr.add(n);
            maxProd = n;
            while(sc.hasNext()) {
                n = sc.nextBigInteger();

                if (n.equals(BigInteger.valueOf(-999999))) {
                    break;
                }
                if(n.compareTo(maxProd) == 1)
                    maxProd = n;
                arr.add(n);
            }
            int size = arr.size();
            BigInteger newProd;
            for(int i = 0; i < size; i++) {
                prod = arr.get(i);
                for(int j = i+1; j < size; j++) {
                    newProd = prod.multiply(arr.get(j));
                    arr.add(newProd);
                    prod = newProd;
                    if(prod.compareTo(maxProd) == 1)
                        maxProd = prod;
                }
            }
            System.out.println(maxProd);
        }
    }
}
