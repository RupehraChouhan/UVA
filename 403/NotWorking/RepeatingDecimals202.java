import java.util.*;

public class RepeatingDecimals202{

    private static void repeatingDecimals(ArrayList<Integer> input) {
        int num = input.get(0);
        int den = input.get(1);
        System.out.print(num+"/"+den+" = ");
        ArrayList<Integer> quotientsBeforeDecimal = new ArrayList<>();
        if(num > den) {
            quotientsBeforeDecimal = numbersBeforeDecimal(num, den);
            num = quotientsBeforeDecimal.get(quotientsBeforeDecimal.size()-1);
            printDigitsBeforeDecimal(quotientsBeforeDecimal);
        } else
            System.out.print("0.");
        quotesAfterDecimal(num,den);
    }

    private static void printDigitsBeforeDecimal(ArrayList<Integer> quotientsBeforeDecimal) {
        for(int i=0; i< quotientsBeforeDecimal.size()-1; i++)
            System.out.print(quotientsBeforeDecimal.get(i));
        System.out.print(".");
    }

    private static void quotesAfterDecimal(int num, int den) {
        int value =0;
        String quotient= "";
        Map<Integer, Integer> map = new HashMap<>();
        while (!map.containsKey(num)){
            map.put(num, value++);
            num *= 10;
            int quotientDigit = num/den;
            quotient += quotientDigit;
            int remainder = num-(quotientDigit * den);
            num = remainder;
        }
        int cycleLength = quotient.length()-map.get(num);
        int startIndex = quotient.length()-cycleLength;
        printDigitsAfterDecimal(quotient,startIndex, cycleLength);
    }

    private static void printDigitsAfterDecimal(String quotient, int startIndex, int numOfRepeatingDecimals) {
        int i;
        for(i=0; i<quotient.length(); i++) {
            if(i==startIndex)
                System.out.print("(");
            System.out.print(quotient.charAt(i));
            if(i==49){
                if (quotient.length()>50)
                    System.out.println("...)");
                break;
            }
        }
        if( quotient.length()<=50)
            System.out.println(")");
        System.out.println("   "+numOfRepeatingDecimals+" = number of digits in repeating cycle");
    }

    private static ArrayList<Integer> numbersBeforeDecimal(int num, int den) {
        ArrayList<Integer> quotients = new ArrayList<>();
        while(num > den) {
            quotients.add(num/den);
            num %=den;
        }
        quotients.add(num);
        return quotients;
    }

    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList<Integer>> input = new ArrayList<>();
        ArrayList<Integer> testCase;
        while(sc.hasNext()) {
            testCase = new ArrayList<>();
            testCase.add(sc.nextInt());
            testCase.add(sc.nextInt());
            input.add(testCase);
        }
        for(int i=0; i<input.size();i++) {
            repeatingDecimals(input.get(i));
            System.out.println();
        }
    }
}
