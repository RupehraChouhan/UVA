import java.util.Scanner;

class Main{
    public static void main(String []args){
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        int[][][] input = new int[t][][];
        //getting the input
        for (int i=0; i<t; i++) {
            int jobs = sc.nextInt();
            int[][] testCase = new int[jobs][2];
            for(int j=0; j< jobs; j++) {
                testCase[j][0] = sc.nextInt();
                testCase[j][1] = sc.nextInt();
            }
            input[i] = testCase;
        }

        //send test cases one by one
        for(int i=0; i<t; i++) {
            int[] order = calculateOrder(input[i]);
            for(int j=0; j<order.length; j++) {
                if(j==(order.length-1)) {
                    System.out.println((order[j]+1));
                }else
                    System.out.print((order[j]+1) + " ");
            }
            if(i!=t-1)
                System.out.println();
        }

    }

    private static int[] calculateOrder(int[][] jobs) {
        int costA, costB, indexCheap, indexTemp;
        int[] done = new int[jobs.length];
        int[] order = new int[jobs.length];
        int orderIndex=0;
        for(int i=0; i<jobs.length; i++) {
            indexCheap= findFirstIndex(done);
            indexTemp=indexCheap;

            for(int j=0; indexTemp<jobs.length; j++) {
                if (done[j] == 1) {
                    if (j == indexTemp)
                        indexTemp +=1;
                    else
                        continue;
                }
                else {
                    costA = jobs[indexCheap][0] * jobs[indexTemp][1];
                    costB = jobs[indexTemp][0] * jobs[indexCheap][1];
                    if (costA <= costB) {
                        indexTemp += 1;
                    } else {
                        indexCheap = indexTemp;
                        indexTemp += 1;
                    }
                }
            }
            order[orderIndex] = indexCheap;
            orderIndex++;
            done[indexCheap] = 1;
        }
        return order;

    }

    private static int findFirstIndex(int[] done) {
        int i;
        for(i=0;i<done.length;i++) {
            if(done[i] == 0)
                break;
        }
        return i;
    }
}
