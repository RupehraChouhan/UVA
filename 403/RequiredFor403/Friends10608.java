import java.util.*;
import java.util.Scanner;

class Friends10608{
    public static void main(String []args){
        Scanner  sc = new Scanner(System.in);
        int t = sc.nextInt();
        int allBees[] = new int[t];
        int allAes[] = new int [t];
        ArrayList<Integer> input = new ArrayList<Integer>();
        for(int i=0; i<t; i++) {          //getting the input
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
            firstIndex = lastIndex;
        }
    }

    static int calculateNumOfPeople(int startIndex, int endIndex, ArrayList<Integer> input, int numOfCitizens) {
        int a,b;
        //assigning value of 0 to all citizens, meaning that no citizen knows other citizens
        int citizenConnectionValues[] = new int[numOfCitizens+1];
        int listNumber = 1; //which list number do citizens belong to
        Stack<Integer> stack  = new Stack<Integer>(); //stack of one group of friends
        int indexOfA, indexOfB;
        Stack<Integer> stackOfA = new Stack<Integer>();
        Stack<Integer> stackOfB = new Stack<Integer>();
        ArrayList<Stack<Integer>> allStacks = new ArrayList<Stack<Integer>>(); //stack of all groups
        for (int i=startIndex; i<endIndex; i+=2) {
            a = input.get(i);
            b = input.get(i+1);
            indexOfA = citizenConnectionValues[a];
            indexOfB = citizenConnectionValues[b];
            if (indexOfA ==0 && indexOfB ==0) {
                stack = new Stack<Integer>();
                stack.push(a);
                stack.push(b);
                allStacks.add(stack);
                citizenConnectionValues[a] = allStacks.indexOf(stack)+1;
                citizenConnectionValues[b] = citizenConnectionValues[a];
                listNumber = allStacks.size()+1;
            }
            else if ((indexOfA !=0 && indexOfB !=0) &&  (indexOfB != indexOfA)) {
                stackOfA = allStacks.get(indexOfA-1);
                stackOfB = allStacks.get(indexOfB-1);
                //merge two stacks
                //if indexOfA < indexOfB, merge B onto A, then update value of B
                if (indexOfA < indexOfB) {
                    citizenConnectionValues = updateIndices(stackOfB,citizenConnectionValues, indexOfA);
                    stackOfA = merge(stackOfA, stackOfB);
                    allStacks.remove(indexOfB-1);
                    for (int j=indexOfB-1; j<allStacks.size(); j++) {
                        Stack<Integer> st1 = allStacks.get(j);
                        for (Integer num : st1) {
                            citizenConnectionValues[num] = citizenConnectionValues[num] -1;
                        }
                    }
                }
                //if indexOfB < indexOfA, merge A onto B, then update value of A
                else {
                    citizenConnectionValues = updateIndices(stackOfA,citizenConnectionValues, indexOfB);
                    stackOfB = merge(stackOfB, stackOfA);
                    allStacks.remove(indexOfA-1);
                    for (int j=indexOfA-1; j<allStacks.size(); j++) {
                        Stack<Integer> st1 = allStacks.get(j);
                        for (Integer num : st1) {
                            citizenConnectionValues[num] = citizenConnectionValues[num] - 1;
                        }
                    }
                }
            }
            else if(indexOfA == indexOfB) {
                continue;
            }
            else {
                if (citizenConnectionValues[a] == 0){ //add A to B's stack
                    citizenConnectionValues[a] = indexOfB;
                    allStacks.set(indexOfB-1, addNum(allStacks.get(indexOfB-1), a));
                }
                else { //add B to A's stack
                    citizenConnectionValues[b] = indexOfA;
                    indexOfB = indexOfA;
                    allStacks.set(indexOfA-1, addNum(allStacks.get(indexOfA-1), b));
                }
            }
        }
        int count = -1;
        for (int i=0; i<allStacks.size(); i++) {
            if ( count < allStacks.get(i).size()) {
                count = allStacks.get(i).size();
            }
        }
        if (count == -1)
            count = 1;
        return count;
    }

    static Stack<Integer> merge(Stack<Integer> mergeIn, Stack<Integer> mergeOut) {
        while(!mergeOut.empty()) {
            if (mergeIn.search(mergeOut.peek()) == -1) {
                mergeIn.push(mergeOut.pop());
            } else {
                mergeOut.pop();
            }
        }
        return mergeIn;
    }

    static Stack<Integer> addNum(Stack<Integer> stack, int num) {
        if (stack.search(num) == -1) {
            stack.push(num);
        }
        return stack;
    }

    static int[] updateIndices(Stack<Integer> st, int[] arr, int index) {
        for(int i=0; i<st.size(); i++) {
            arr[st.get(i)] = index;
        }
        return arr;
    }

}
