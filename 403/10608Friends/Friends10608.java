import java.util.*;
import java.util.Scanner;
public class Friends10608{

   public static void main(String []args){
       Scanner  sc = new Scanner(System.in);
       int t = sc.nextInt();
       int allBees[] = new int[t];
       int allAes[] = new int [t];
       ArrayList<Integer> input = new ArrayList<Integer>();
       //getting the input
       for(int i=0; i<t; i++) {
           allAes[i] = sc.nextInt();
           allBees[i] = sc.nextInt();
           for(int j=0; j<allBees[i]*2; j++) {
               input.add(sc.nextInt());
           }
       }
       //Sending the first and last index and an array of input to the function
       int firstIndex = 0;
       int lastIndex, maxNumOfPeople;
       for(int i=0; i<t; i++) {
           lastIndex = firstIndex + allBees[i]*2;
           //want to send number of citizens
           maxNumOfPeople = calculateNumOfPeople(firstIndex, lastIndex, input, allAes[i] );
           System.out.println(maxNumOfPeople);
           firstIndex += lastIndex;
       }
   }

   static int calculateNumOfPeople(int startIndex, int endIndex, ArrayList<Integer> input, int numOfCitizens) {
       int a,b;
       //assigning value of 0 to all citizens, meaning that no citizen knows other citizens
       int citizenConnectionValues[] = new int[numOfCitizens+1];
       int listNumber = 1; //which list number do citizens belong to
       for (int i=startIndex; i<endIndex; i+=2) {
           a = input.get(i);
           b = input.get(i+1);
           if (citizenConnectionValues[a] ==0 && citizenConnectionValues[b] ==0) {
               citizenConnectionValues[a] = listNumber;
               citizenConnectionValues[b] = listNumber;
               listNumber++;
           }
           else if (citizenConnectionValues[a] !=0 && citizenConnectionValues[b] ==0) {
               citizenConnectionValues[b] = citizenConnectionValues[a];
           }
           else if (citizenConnectionValues[b] !=0 && citizenConnectionValues[a] ==0) {
               citizenConnectionValues[a] = citizenConnectionValues[b];
           }
           else {
               continue;
           }
       }
       int count[] = new int[numOfCitizens+1];
       for(int i=0; i<numOfCitizens+1; i++) {
           count[citizenConnectionValues[i]]+= 1 ;
       }
       count[0] = 0;
       int max = 0;
       for(int i=0; i<numOfCitizens+1; i++) {
           if (count[i]>max)
               max = count[i];
       }
       return max;
   }

}
