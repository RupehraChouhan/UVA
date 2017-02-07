/* UVa problem: 459
 *
 * Topic: Graph Theory
 *
 * Level: challenging
 *
 * Brief problem description:
 *
 *  Write a program to determine the number of maximal connected subgraphs
 *  of a given graph. A connected subgraph is maximal if there are no nodes and edges in
 *  the original graph that could be added to the subgraph and still leave
 *  it connected.
 *
 * Solution Summary:
 *
 *  This problem is solved by running depth-first search to count the number
 *  of connected components.
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
import java.util.*;

class uva_459 {

    static HashMap<Character, ArrayList<Character>> graph;
    static int[] visitedDfs;
    static int cc;

    private static void createGraph(Character[] node) {

        if(graph.containsKey(node[0])) {
            ArrayList<Character> neighbours = graph.get(node[0]);
            neighbours.add(node[1]);
        } else {
            ArrayList<Character> neighbours = new ArrayList<>();
            neighbours.add(node[1]);
            graph.put(node[0],neighbours);
        }
    }

    private static void runDFS(Character u) {
        visitedDfs[u-65] = 1;
        for(int i=0; i<graph.get(u).size(); i++) {
            Character n = graph.get(u).get(i);
            if(visitedDfs[n-65]==0)
                runDFS(n);
        }
    }

    public static void main(String []args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        sc.nextLine(); sc.nextLine();
        String str;
        for(int i=0; i<t; i++) {
            str = sc.nextLine();
            graph = new HashMap<>();
            visitedDfs = new int[str.charAt(0)-64];
            cc=0;
            Character[] chars;
            for(char letter ='A'; letter<=str.charAt(0); letter++) {
                chars = new Character[2];
                chars[0] = letter;chars[1] = letter;
                createGraph(chars);
            }
            String[] node;

            while(sc.hasNextLine()) {
                str = sc.nextLine();
                if(str.equals("")) {
                    break;
                }
                else {
                    node = str.split("");
                    Character a = node[0].charAt(0); Character b = node[1].charAt(0);
                    chars = new Character[2];
                    chars[0] = a; chars[1] = b;
                    createGraph(chars);
                    chars[0] = b;chars[1] = a;
                    createGraph(chars);
                }

            }

            for (Map.Entry<Character, ArrayList<Character>> vertex : graph.entrySet()) {
                if(visitedDfs[vertex.getKey()-65]==0) {
                    cc++;
                    runDFS(vertex.getKey());
                }
            }
            System.out.println(cc);
            if(i!=t-1)
                System.out.println();
        }
    }
}
