import java.util.*;
import static java.lang.System.*;

public class Freckles10034 {

    public static void main(String []args) {
        Scanner sc = new Scanner(in);
        ArrayList<ArrayList<Double[]>> allTestCases = new ArrayList<>();
        ArrayList<Double[]> allVertices;
        Double[] vertex;
        int t = sc.nextInt();
        for (int i = 0; i <t ; i++) {
            int v = sc.nextInt();
            allVertices = new ArrayList<>();
            for (int j = 0; j <v ; j++) {
               vertex = new Double[2];
               vertex[0] = sc.nextDouble();
               vertex[1] = sc.nextDouble();
               allVertices.add(vertex);
            }
            allTestCases.add(allVertices);
        }
        for (int i = 0; i <t ; i++) {
            minimumTotalLenth(allTestCases.get(i));
            if(i!=t-1)
                out.println();
        }
    }

    private static void minimumTotalLenth(ArrayList<Double[]> allVertices) {
        //Calculate the distance between all the points and store them in a map
        HashMap<Double[][], Double> map = new HashMap<>();
        for(int i=0; i< allVertices.size()-1; i++) {
            Double[] firstCoordinate = allVertices.get(i);
            for(int j=i+1; j<allVertices.size(); j++) {
                Double[] secondCoordinate = allVertices.get(j);
                Double[][] key = createKey(firstCoordinate, secondCoordinate);
                Double value = calculateDistanceBetweenTwoPoints(firstCoordinate, secondCoordinate);
                map.put(key, value);
            }
        }
        map = sortHashMapByValues(map); //Sort the edges in increasing order
        Double inkRequired=getMinimumLenghtEdges(map);
        out.println(String.format( "%.2f",inkRequired));
    }

    private static Double[][] createKey(Double[] firstCoordinate, Double[] secondCoordinate) {
        Double[][] key = new Double[2][2];
        key[0] = firstCoordinate;
        key[1] = secondCoordinate;
        return key;
    }

    private static Double calculateDistanceBetweenTwoPoints(Double[] firstCoordinate, Double[] secondCoordinate) {
        Double x2MinusX1Square = (secondCoordinate[0]-firstCoordinate[0]) * (secondCoordinate[0]-firstCoordinate[0]);
        Double y2MinusY1Square = (secondCoordinate[1]-firstCoordinate[1]) * (secondCoordinate[1]-firstCoordinate[1]);
        return (Math.sqrt(x2MinusX1Square+y2MinusY1Square));
    }

    private static Double getMinimumLenghtEdges(HashMap<Double[][], Double> map) {
        Set<Map.Entry<Double[][], Double>> set = map.entrySet();
        Iterator<Map.Entry<Double[][], Double>> it = set.iterator();
        Set<Double[]> vertices = new HashSet<>();
        ArrayList<Double> edges = new ArrayList<>();
        DisjointSet ds = new DisjointSet();
        while(it.hasNext()) {
            Map.Entry<Double[][], Double> entry = it.next();
            Double value = entry.getValue();
            Double[][] key = entry.getKey();
            Double[] coordinate1 = key[0];
            Double[] coordinate2 = key[1];
            if(vertices.contains(coordinate1)== false && vertices.contains(coordinate2) ==false) {
                ds.makeSet(coordinate1);
                ds.makeSet(coordinate2);
                ds.union(coordinate1, coordinate2);
                vertices.add(coordinate1); vertices.add(coordinate2);
                edges.add(value);
            } else if (vertices.contains(coordinate1)==true && vertices.contains(coordinate2)==false) {
                ds.makeSet(coordinate2);
                ds.union(coordinate1, coordinate2);
                vertices.add(coordinate2);
                edges.add(value);
            } else if(vertices.contains(coordinate1) == false && vertices.contains(coordinate2)==true) {
                    vertices.add(coordinate1);
                    ds.makeSet(coordinate1);
                    ds.union(coordinate2, coordinate1);
                    vertices.add(coordinate1);
                    edges.add(value);
            } else {
                if(ds.findSet(coordinate1) != ds.findSet(coordinate2)) {
                    ds.union(coordinate1, coordinate2);
                    edges.add(value);
                }
            }
        }
        Double minInkRequired= getMinimumInk(edges);
        return minInkRequired;
    }

    private static Double getMinimumInk(ArrayList<Double> edges) {
        Double ink=0.0;
        for (Double edge : edges) {
            ink += edge;
        }
        return ink;
    }

    private static HashMap<Double[][], Double> sortHashMapByValues(HashMap<Double[][], Double> map) {
        Set<Map.Entry<Double[][],Double>> entries = map.entrySet();
        List<Map.Entry<Double[][],Double>> list = new LinkedList<Map.Entry<Double[][],Double>>(entries);
        Collections.sort(list, new Comparator<Map.Entry<Double[][],Double>>() {

            @Override
            public int compare(Map.Entry<Double[][], Double> coordinate1,
                               Map.Entry<Double[][], Double> coordinate2) {
                return coordinate1.getValue().compareTo(coordinate2.getValue());
            }
        });
        // Storing the list into Linked HashMap to preserve the order of insertion.
        Map<Double[][],Double> aMap2 = new LinkedHashMap<>();
        for(Map.Entry<Double[][],Double> entry: list) {
            aMap2.put(entry.getKey(), entry.getValue());
        }
        return (HashMap<Double[][], Double>) aMap2;
    }
}

class DisjointSet {

    private Map<Double[], Node> map = new HashMap<>();

    class Node {
        Double[] data;
        Node parent;
        int rank;
    }

    public void makeSet(Double[] data) {
        Node node = new Node();
        node.data = data;
        node.parent = node;
        node.rank = 0;
        map.put(data, node);
    }

    public boolean union(Double[] point1, Double[] point2) {
        Node node1 = map.get(point1);
        Node node2 = map.get(point2);

        Node parent1 = findSet(node1);
        Node parent2 = findSet(node2);

        //if they are part of same set do nothing
        if (parent1.data == parent2.data) {
            return false;
        }

        //else whoever's rank is higher becomes parent of other
        if (parent1.rank >= parent2.rank) {
            //increment rank only if both sets have same rank
            parent1.rank = (parent1.rank == parent2.rank) ? parent1.rank + 1 : parent1.rank;
            parent2.parent = parent1;
        } else {
            parent1.parent = parent2;
        }
        return true;
    }

    public Double[] findSet(Double[] data) {
        return findSet(map.get(data)).data;
    }

    private Node findSet(Node node) {
        Node parent = node.parent;
        if (parent == node) {
            return parent;
        }
        node.parent = findSet(node.parent);
        return node.parent;
    }
}
