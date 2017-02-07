/* UVa problem: 374
 *
 * Topic: Number Theory
 *
 * Level: easy
 *
 * Brief problem description:
 *
 *  Calculate R := B^P mod M
 *
 * Solution Summary:
 *
 *  Java's built in function solves it easily
 *
 * Used Resources:
 *
 *   ...
 *
 * I hereby certify that I have produced the following solution myself
 * using only the resources listed above in accordance with the CMPUT
 * 403 collaboration policy.
 *
 *
 * --------------------- Rupehra Chouhan
 */

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class uva_374 {
    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        BigInteger b, p,m;
        ArrayList<BigInteger> answers = new ArrayList<BigInteger>();
        while (sc.hasNext()) {
            b = sc.nextBigInteger();
            p = sc.nextBigInteger();
            m = sc.nextBigInteger();
            answers.add(calculateModPow(b,p,m));
        }
       printAnswers(answers);
    }

    private static BigInteger calculateModPow(BigInteger b, BigInteger p, BigInteger m) {
        BigInteger answer = b.modPow(p,m);
        return answer;
    }

    private static void printAnswers(ArrayList<BigInteger> answers) {
        for(BigInteger b : answers) {
            System.out.println(b);
        }
    }
}
