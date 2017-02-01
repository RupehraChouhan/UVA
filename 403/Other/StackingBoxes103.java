import java.util.*;

public class StackingBoxes103 {

    private static void calculateLongestIncreasingSequence(int[][] arr) {
        ArrayList<int[]> list = new ArrayList<>();
        for(int i=0; i< arr.length; i++) {
            list.add(arr[i]);
        }
        arr = sortBoxes(arr);
        int[] t = new int[arr.length];
        ArrayList<ArrayList<Integer>> indicesForAll = new ArrayList<>();
        ArrayList<Integer> indices = new ArrayList<>();
        for(int i=0; i<t.length; i++) {
            indicesForAll.add(new ArrayList<>());
        }
        indicesForAll.get(0).add(list.indexOf(arr[0])+1);
        Arrays.fill(t,1);
        int[] originalT = t.clone();
        for(int i=1; i<t.length; i++) {
            for(int j=0; j<i;j++) {
                boolean ans = compareTwoBoxes(arr[j],arr[i]);
                t[i] = ans == true? Math.max(t[j]+originalT[i], t[i]) : t[i] ;
                if (ans ==true && indicesForAll.get(j).size()>indicesForAll.get(i).size()) {
                    indices = (ArrayList<Integer>) indicesForAll.get(j).clone();
                    indicesForAll.set(i,indices);
                }
            }
            indicesForAll.get(i).add(list.indexOf(arr[i])+1);
        }
        int max = t[0];
        int index =0;
        for(int i=1; i<t.length; i++) {
            if(t[i]>max) {
                max = t[i];
                index=i;
            }
        }
        System.out.println(max);
        indices = indicesForAll.get(index);
        for(int i=0; i<indices.size(); i++) {
            if(i!=indices.size()-1)
                System.out.print(indices.get(i)+ " ");
            else
                System.out.print(indices.get(i));
        }
        System.out.println();
    }

    private static boolean compareTwoBoxes(int[] arrOne, int[] arrTwo) {
        for(int i=0; i<arrOne.length; i++) {
            if(arrOne[i] >= arrTwo[i])
                return false;
        }
        return true;
    }

    private static int[][] sortBoxes(int[][] arr) {
        for(int i=0; i<arr.length; i++) {  //sort individual boxes
            Arrays.sort(arr[i]);
        }
        for(int i=0; i<arr.length-1; i++) {
            int[] arrA = arr[i];
            int[] arrB = arr[i+1];
            boolean allPrevValuesAreEqual= arrA[0]==arrB[0]? true: false;
            boolean allPrevValuesAreGreater=arrA[0]>arrB[0]? true: false;
            for(int j=0; j<arrA.length; j++) {
                if(arrA[j]>arrB[j]) {
                    if (allPrevValuesAreEqual == true || allPrevValuesAreGreater==true) {
                        allPrevValuesAreEqual = false;
                        arr[i] = arr[i + 1];
                        arr[i + 1] = arrA;
                        for (int k = i; k > 0; k--) {
                            arrA = arr[k];
                            arrB = arr[k - 1];
                            for (int l = 0; l < arrA.length; l++) {
                                if (arrA[l] < arrB[l]) {
                                    arr[k] = arr[k - 1];
                                    arr[k - 1] = arrA;
                                    break;
                                } else{
                                    if(arrA[l]>arrB[l])
                                        break;
                                }
                            }
                        }
                        break;
                    }
                    break;
                }else {
                    if(arrA[j]<arrB[j])
                        allPrevValuesAreEqual = false;
                }
            }
        }
        return arr;
    }

    public static void main(String []args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<int[][]> allInput = new ArrayList<>();
        while (sc.hasNext()) {
            int numOfBoxes = sc.nextInt();
            int sizeOfBox = sc.nextInt();
            int[][] allBoxes = new int[numOfBoxes][sizeOfBox];
            for (int i = 0; i < numOfBoxes; i++) {
                for(int j=0; j< sizeOfBox; j++)
                    allBoxes[i][j] = sc.nextInt();
            }
            allInput.add(allBoxes);
        }
        for(int i=0; i<allInput.size(); i++) {
            calculateLongestIncreasingSequence(allInput.get(i));
        }
    }
}
/*
4 3
1 2 3
0 2 3
0 0 4
0 0 1

 */
