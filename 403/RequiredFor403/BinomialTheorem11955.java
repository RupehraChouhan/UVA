import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

public class BinomialTheorem11955 {

    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> input = new ArrayList<>();
        int t = sc.nextInt();
        for(int i=0; i<t;i++) {
            input.add(sc.next());
        }
        ArrayList<ArrayList<BigInteger>> triangle = new ArrayList<>();
        triangle = pascalsTriangle(50);
        for(int i=0; i<t; i++) {
            System.out.print("Case "+(i+1)+": ");
            calculateFormula(input.get(i),triangle);
        }
    }

    private static void calculateFormula(String str, ArrayList<ArrayList<BigInteger>> triangle) {
        String[] splitted = str.split("[(+)^]");
        String a = splitted[1];
        String b = splitted[2];
        int k = Integer.parseInt(splitted[4]);

        printFormula(a,b,k, triangle.get(k));
        System.out.println();
    }

    private static void printFormula(String a, String b, int k, ArrayList<BigInteger> coefficients) {
        int powerA=k;
        int powerB=0;
        for(int i=0; i<coefficients.size(); i++) {
            if(coefficients.get(i)!=BigInteger.ONE)
                System.out.print(coefficients.get(i)+"*");
            if(powerA>0) {
                if(powerA==1)
                    System.out.print(a);
                else
                    System.out.print(a + "^" + powerA);
            }
            if(powerA>0 && powerB>0)
                System.out.print("*");
            if(powerB > 0) {
                if(powerB==1)
                    System.out.print(b);
                else
                    System.out.print(b+"^"+powerB);
            }
            if(i!=coefficients.size()-1)
                System.out.print("+");
            powerA--;
            powerB++;
        }

    }

    static ArrayList<ArrayList<BigInteger>> pascalsTriangle(Integer max) {
        ArrayList<ArrayList<BigInteger>> triangle = new ArrayList<>();
        ArrayList<BigInteger> rows = new ArrayList<>();
        ArrayList<BigInteger> prevRow = new ArrayList<>();
        rows.add(BigInteger.ONE);
        triangle.add(rows);
        rows = new ArrayList<>();
        rows.add(BigInteger.ONE); rows.add(BigInteger.ONE);
        triangle.add(rows);
        for (Integer i=2; i<=max; i++) {
            prevRow = triangle.get(i-1);
            rows = new ArrayList<BigInteger>();
            rows.add(BigInteger.ONE);
            for(int j=1; j<=prevRow.size()-1; j++) {
                BigInteger b1 = prevRow.get(j-1);
                BigInteger b2 = prevRow.get(j);
                rows.add(b1.add(b2));
            }
            rows.add(BigInteger.ONE);
            triangle.add(rows);
        }
        return triangle;
    }
}
