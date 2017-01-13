import java.util.HashMap;
import java.util.Scanner;

public class AirlineComparison869 {

    private static int[][] runMarchallAlgorithm(int[][] matrix) {
        int length = matrix.length;
        int infinity = Integer.MAX_VALUE;
        for(int i=0; i<length; i++) {
            for(int row=0; row<length; row++) {
                if(row!=i){
                    if(matrix[row][i]==1) {
                        for(int col=0;col<length;col++) {
                            if(col!=i) {
                                if(matrix[i][col]==1)
                                    matrix[row][col]=1;
                            }
                        }
                    }
                }
            }
        }
        return matrix;
    }

    private static boolean compareBothMatrices(int[][] cityAFlight, int[][] cityBFlight) {
        boolean areEqual = true;
        for(int i=0;i<cityAFlight.length;i++) {
            for (int j=0; j<cityAFlight.length;j++) {
                if(cityAFlight[i][j] != cityBFlight[i][j]) {
                    areEqual = false;
                    break;
                }
            }
        }
        return areEqual;
    }

    private static HashMap<String,Integer> mapCitiesToNumbers(HashMap<String, Integer> hm) {
        hm.put("A",0);hm.put("B",1);hm.put("C",2);hm.put("D",3);hm.put("E",4);hm.put("F",5);
        hm.put("G",6);hm.put("H",7);hm.put("I",8);hm.put("J",9);hm.put("K",10);hm.put("L",11);
        hm.put("M",12);hm.put("N",13);hm.put("O",14);hm.put("P",15);hm.put("Q",16);hm.put("R",17);
        hm.put("S",18);hm.put("T",19);hm.put("U",20);hm.put("V",21);hm.put("W",22);hm.put("X",23);
        hm.put("Y",24);hm.put("Z",25);
        return hm;
    }

    public static void main(String []args) {
        HashMap<String,Integer> hm = new HashMap<>();
        hm = mapCitiesToNumbers(hm);
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for(int i=0; i<t; i++) {
            int[][] companyA = new int[26][26];
            int[][] companyB = new int[26][26];
            int numOfFlightsForCompanyOne = sc.nextInt();
            for(int j=0; j< numOfFlightsForCompanyOne; j++) {
                String cityA = sc.next();
                String cityB = sc.next();
                companyA[hm.get(cityA)][hm.get(cityB)] = 1;
            }
            int numOfFlightForCompanyTwo = sc.nextInt();
            for(int j=0; j<numOfFlightForCompanyTwo; j++) {
                String cityA = sc.next();
                String cityB = sc.next();
                companyB[hm.get(cityA)][hm.get(cityB)] = 1;
            }
            companyA = runMarchallAlgorithm(companyA);
            companyB = runMarchallAlgorithm(companyB);
            boolean areEqual = compareBothMatrices(companyA, companyB);
            if(areEqual==true)
                System.out.println("YES");
            else
                System.out.println("NO");
            if(i!=t-1)
                System.out.println();
        }
    }
}
