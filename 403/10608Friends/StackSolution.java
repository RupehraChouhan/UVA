import java.util.*;
//import java.util.Scanner;

//This code doesn't work yet
public class StackSolution{

   public static void main(String []args){
       Scanner  sc = new Scanner(System.in);
       int t = 1;
       int allBees[] = new int[t];
       allBees[0] = 3;
       int allAes[] = new int[t];
       allAes[0] = 4;
       ArrayList<Integer> input = new ArrayList<Integer>();
       input.add(1);input.add(2);input.add(3);input.add(4);input.add(3);input.add(1);
       //getting the input
       //for(int i=0; i<t; i++) {
           //allAes[i] = sc.nextInt();
           //allBees[i] = sc.nextInt();
           //for(int j=0; j<allBees[i]*2; j++) {
           //    input.add(sc.nextInt());
           //}
       //}
       //Sending the first and last index and an array of input to the function
       int firstIndex = 0;
       int lastIndex, maxNumOfPeople;
       for(int i=0; i<t; i++) {
           lastIndex = firstIndex + allBees[i]*2;
           //want to send number of citizens
           maxNumOfPeople = calculateNumOfPeople(firstIndex, lastIndex, input, allAes[i] );
           // System.out.println(maxNumOfPeople);
           firstIndex += lastIndex;
       }
   }

   static int calculateNumOfPeople(int startIndex, int endIndex, ArrayList<Integer> input, int numOfCitizens) {
       int a,b;
       //assigning value of 0 to all citizens, meaning that no citizen knows other citizens
       int citizenConnectionValues[] = new int[numOfCitizens+1];
       int listNumber = 1; //which list number do citizens belong to
       Stack stack  = new Stack(); //stack of one group of friends
       int indexOfA, indexOfB;
       Stack stackOfA = new Stack();
       Stack stackOfB = new Stack();
       ArrayList<Stack> allStacks = new ArrayList<Stack>(); //stack of all groups
       for (int i=startIndex; i<endIndex; i+=2) {
           a = input.get(i);
           b = input.get(i+1);
           if (citizenConnectionValues[a] ==0 && citizenConnectionValues[b] ==0) {
               stack = new Stack();
               stack.push(a);
               stack.push(b);
               allStacks.add(stack);
               citizenConnectionValues[a] = listNumber;
               citizenConnectionValues[b] = listNumber;
               listNumber++;
           }
           else if (citizenConnectionValues[a] !=0 && citizenConnectionValues[b] !=0) {
               indexOfA = citizenConnectionValues[a] -1;
               indexOfB = citizenConnectionValues[b] -1;
               System.out.println("Index of "+ a+" = " + indexOfA);
               System.out.println("Index of "+ b+" = " + indexOfB);
               stackOfA = allStacks.get(indexOfA);
               stackOfB = allStacks.get(indexOfB);
               //merge two stacks
               //if indexOfA < indexOfB, merge B onto A, then update value of B
               if (indexOfA < indexOfB) {
                   allStacks.set(indexOfA,merge(stackA, stackB, indexOfA));
               }


               //if indexOfB < indexOfA, merge A onto B, then update value of A
           }
           else {
               continue;
           }
       }
       return 0;
   }

}
